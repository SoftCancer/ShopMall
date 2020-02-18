package com.dongl.pay.service.impl;

import com.dongl.base.BaseResponse;
import com.dongl.mapper.MapperUtils;
import com.dongl.pay.mapper.PaymentChannelMapper;
import com.dongl.pay.mapper.entity.PaymentChannelEntity;
import com.dongl.pay.out.dto.PaymentChannelDTO;
import com.dongl.pay.service.IPaymentChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 查询所有支付渠道
 * @author: YaoGuangXun
 * @date: 2020/2/18 11:23
 * @Version: 1.0
 */
@RestController
public class PaymentChannelServiceImpl extends BaseResponse implements IPaymentChannelService {

    @Autowired
    private PaymentChannelMapper paymentChannelMapper;

    @Override
    public List<PaymentChannelDTO> selectAll() {
        List<PaymentChannelEntity> paymentChannelDTOS = paymentChannelMapper.selectAll();
        return MapperUtils.mapAsList(paymentChannelDTOS,PaymentChannelDTO.class);
    }
}
