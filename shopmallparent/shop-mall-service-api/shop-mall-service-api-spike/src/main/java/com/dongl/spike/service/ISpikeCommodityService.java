package com.dongl.spike.service;

import com.alibaba.fastjson.JSONObject;
import com.dongl.base.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/22 18:06
 * @Version: 1.0
 */
public interface ISpikeCommodityService {

    /**
     *  用户秒杀接口 phone和userid都可以的
     * @Author: YaoGuangXun
     * @Date: 2020/2/22 19:38
     **/
    @RequestMapping("/spike")
    public BaseResponse spike(String  phone ,Long seckillId);

    /**
     * 新增对应商品库存令牌桶
     *
     * @seckillId 商品库存id
     */
    @RequestMapping("/addSpikeToken")
    public BaseResponse<JSONObject> addSpikeToken(Long seckillId, Long tokenQuantity);
}
