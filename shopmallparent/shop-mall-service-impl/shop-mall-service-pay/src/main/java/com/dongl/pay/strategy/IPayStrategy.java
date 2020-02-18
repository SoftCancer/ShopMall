package com.dongl.pay.strategy;

import com.dongl.pay.mapper.entity.PaymentChannelEntity;
import com.dongl.pay.out.dto.PayMentTransacDTO;

/**
 * @Description: 聚合支付共同实现接口
 * @author: YaoGuangXun
 * @date: 2020/2/18 14:33
 * @Version: 1.0
 */
public interface IPayStrategy {

    public String  polymerizationPay(PaymentChannelEntity pymentChannel,PayMentTransacDTO payMentTransacDTO);
}
