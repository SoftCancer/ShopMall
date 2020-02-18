package com.dongl.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dongl.base.BaseApiService;
import com.dongl.base.BaseResponse;
import com.dongl.pay.factory.StrategyFactory;
import com.dongl.pay.mapper.PaymentChannelMapper;
import com.dongl.pay.mapper.entity.PaymentChannelEntity;
import com.dongl.pay.out.dto.PayMentTransacDTO;
import com.dongl.pay.service.IPayContextService;
import com.dongl.pay.service.IPayMentTransacInfoService;
import com.dongl.pay.strategy.IPayStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 聚合支付实现多渠道调用
 * @author: YaoGuangXun
 * @date: 2020/2/18 13:53
 * @Version: 1.0
 */
@RestController
public class PayContextServiceImpl extends BaseApiService implements IPayContextService {

    @Autowired
    private PaymentChannelMapper paymentChannelMapper;

    @Autowired
    private IPayMentTransacInfoService payMentTransacInfoService;

    @Override
    public BaseResponse<JSONObject> toPayHtml(String channelId, String payToken) {
        // 1.使用渠道id获取渠道信息 classAddres
        PaymentChannelEntity pymentChannel = paymentChannelMapper.selectPCByChannelId(channelId);
        if (pymentChannel == null) {
            return setResultError("没有查询到该渠道信息");
        }
        // 2.使用payToken获取支付参数
        BaseResponse<PayMentTransacDTO> tokenByPayMentTransac = payMentTransacInfoService
                .tokenByPayMentTransac(payToken);
        if (!isSuccess(tokenByPayMentTransac)) {
            return setResultError(tokenByPayMentTransac.getMsg());
        }
        PayMentTransacDTO payMentTransacDTO = tokenByPayMentTransac.getData();
        // 3.执行具体的支付渠道的算法获取html表单数据 策略设计模式 使用java反射机制 执行具体方法
        String classAddres = pymentChannel.getClassAddres();
        IPayStrategy payStrategy = StrategyFactory.getPayStrategy(classAddres);
        String payHtml = payStrategy.polymerizationPay(pymentChannel, payMentTransacDTO);
        // 4.直接返回html
        JSONObject data = new JSONObject();
        data.put("payHtml", payHtml);
        return setResultSuccess(data);
    }
}
