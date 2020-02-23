package com.dongl.spike.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dongl.base.BaseApiService;
import com.dongl.base.BaseResponse;
import com.dongl.core.token.GenerateToken;
import com.dongl.core.utils.RedisUtil;
import com.dongl.spike.producer.SpikeCommodityProducer;
import com.dongl.spike.service.ISpikeCommodityService;
import com.dongl.spike.service.mapper.IOrderMapper;
import com.dongl.spike.service.mapper.ISeckillMapper;
import com.dongl.spike.service.mapper.entity.OrderEntity;
import com.dongl.spike.service.mapper.entity.SeckillEntity;
import com.dongl.web.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/22 18:10
 * @Version: 1.0
 */
@Slf4j
@RestController
public class SpikeCommodityServiceImpl extends BaseApiService implements ISpikeCommodityService {

    @Autowired
    private ISeckillMapper seckillMapper;

    @Autowired
    private IOrderMapper orderMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private GenerateToken generateToken;

    @Autowired
    private SpikeCommodityProducer spikeCommodityProducer;

    @Override
    public BaseResponse spike(String phone, Long seckillId) {
        // 1. 验证参数
        if (StringUtils.isEmpty(phone)){
            return setResultError("mobile number cannot be empty");
        }
        if (null == seckillId){
            return setResultError("seckillId  cannot be empty");
        }
        /**
         *  第一版：秒杀简单实现
        // 2. 限制用户频繁操作，通过向Redis存放用户信息，再次存放时返回用户是否已存在。
        Boolean bool = redisUtil.setNx(phone,seckillId+"",10L);
        if (!bool){
            return setResultError("The operation is too frequent. Try again in 10 seconds!");
        }
        // 方式一 start
        // 3.通过查询秒杀商品的版本，来控制秒杀超出库存
        SeckillEntity seckillEntity = seckillMapper.findBySeckillId(seckillId);
        if (seckillEntity == null){
            return setResultError("product sold out");
        }
        Long version = seckillEntity.getVersion();
        // 4.修改秒杀库存
        int seckill = seckillMapper.inventoryDeductionTwo(seckillId,version);

        // 方式一 end
         **/
        /**
         *  方式一和方式二区别：
         *  方式一 使用的是乐观锁，若有200 个请求 100个商品，则100个商品不会被全部销售。
         *  方式二： 使用的是数据库自带的 行级锁，属于悲观锁，若有200 个请求 100个商品，则会被全部销售。

        // 方式二 star
        // int seckill = seckillMapper.inventoryDeduction(seckillId);
        // 方式二 end

        if (!toDaoResult(seckill)){
            return setResultError("Please try again later");
        }

        // 5. 添加秒杀成功订单
        OrderEntity o = new OrderEntity();
        o.setSeckillId(seckillId);
        o.setUserPhone(phone);
        o.setState(0L);
        o.setCreateTime(DateUtils.getDateFormat());
        int order = orderMapper.insertOrder(o);
        if (!toDaoResult(order)){
            return setResultError("Please try again later");
        }
         **/

        /**
         *  第二版 ：通过预设置的Token 和RabbitMQ的异步处理实现秒杀
         **/
        // 2.从redis从获取对应的秒杀token
        String seckillToken = generateToken.getListKeyToken(seckillId + "");
        if (StringUtils.isEmpty(seckillToken)) {
            log.info(">>>seckillId:{}, 亲，该秒杀已经售空，请下次再来!", seckillId);
            return setResultError("亲，该秒杀已经售空，请下次再来!");
        }
        log.info(">>>seckillToken:{}, 已成功被领取!", seckillToken);

        // 3.获取到秒杀token之后，异步放入mq中实现修改商品的库存
        sendSeckillMsg(seckillId, phone);
        log.info("正在火速为你抢购中.......！");
        return setResultSuccess("正在排队中.......");
    }


    /**
     * 获取到秒杀token之后，异步放入 mq 中实现修改商品的库存
     */
    @Async
    private void sendSeckillMsg(Long seckillId, String phone) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("seckillId", seckillId);
        jsonObject.put("phone", phone);
        spikeCommodityProducer.send(jsonObject);
    }

    /**
     * seckillId: 秒杀商品id
     * tokenQuantity: 生成秒杀商品的token
     *  生成和秒杀商品数量相等的 token，作用：
     *  1. 当有10万或更多 用户秒杀商品时，让用户先取获取该token，
     *  2. 只有抢到token的 用户会秒杀成功，修改数据库，
     *  3. 没用抢到token的用户 则秒杀失败，就不需要访问数据库，从而降低数据的压力。
     * @Description: 生成和秒杀商品数量相等的 token；
     * @Author: YaoGuangXun
     * @Date: 2020/2/23 0:40
     **/
    @Override
    public BaseResponse<JSONObject> addSpikeToken(Long seckillId, Long tokenQuantity) {
        // 1.验证参数
        if (seckillId == null) {
            return setResultError("商品库存id不能为空!");
        }
        if (tokenQuantity == null) {
            return setResultError("token数量不能为空!");
        }
        SeckillEntity seckillEntity = seckillMapper.findBySeckillId(seckillId);
        if (seckillEntity == null) {
            return setResultError("商品信息不存在!");
        }
        Long size = generateToken.getListSize(seckillId+"");
        if (size.equals(tokenQuantity)){
            return setResultSuccess(size +" 个令牌已成功生成！");
        }
        if (size > 0){
            return setResultSuccess("目前还有 "+size +" 个令牌");
        }
        // 2.使用多线程异步生产令牌token,存放到Redis中。
        createSeckillToken(seckillId, tokenQuantity);
        return setResultSuccess("令牌正在生成中.....");
    }

    /**
     * @Description: 异步生成Token ，并存入到Redis中。
     * @Author: YaoGuangXun
     * @Date: 2020/2/23 1:15
     **/
    @Async
    private void createSeckillToken(Long seckillId, Long tokenQuantity) {
        generateToken.setListToken("seckill_", seckillId + "", tokenQuantity);
    }
}
