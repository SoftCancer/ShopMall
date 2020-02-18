package com.dongl.pay.strategy.impl;

import com.dongl.pay.mapper.entity.PaymentChannelEntity;
import com.dongl.pay.out.dto.PayMentTransacDTO;
import com.dongl.pay.strategy.IPayStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 支付宝支付渠道
 * @author: YaoGuangXun
 * @date: 2020/2/18 14:57
 * @Version: 1.0
 */
@Slf4j
public class AliPayStrategy implements IPayStrategy {
    @Override
    public String polymerizationPay(PaymentChannelEntity pymentChannel, PayMentTransacDTO payMentTransacDTO) {
        log.info(">>>>>>>>> 支付宝渠道 >>>>>>>>");
        return "支付宝支付form 表单";
    }
}
