package com.dongl.pay.service.impl;

import com.dongl.base.BaseApiService;
import com.dongl.base.BaseResponse;
import com.dongl.core.bean.BeanConversionUtils;
import com.dongl.core.token.GenerateToken;
import com.dongl.pay.mapper.PaymentTransactionMapper;
import com.dongl.pay.mapper.entity.PaymentTransactionEntity;
import com.dongl.pay.out.dto.PayMentTransacDTO;
import com.dongl.pay.service.IPayMentTransacInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 第二步：根据Token 查询预支付信息，
 * @author: YaoGuangXun
 * @date: 2020/2/18 12:23
 * @Version: 1.0
 */
@RestController
public class PayMentTransacInfoServiceImpl extends BaseApiService implements IPayMentTransacInfoService {

    @Autowired
    private GenerateToken generateToken;

    @Autowired
    private PaymentTransactionMapper paymentTransactionMapper;

    @Override
    public BaseResponse<PayMentTransacDTO> tokenByPayMentTransac(String token) {
        // 1.验证token是否为空
        if (StringUtils.isEmpty(token)) {
            return setResultError("token参数不能空!");
        }
        // 2.使用token查询redisPayMentTransacID
        String value = generateToken.getToken(token);
        if (StringUtils.isEmpty(value)) {
            return setResultError("该Token可能已经失效或者已经过期");
        }

        // 3.转换为整数类型
        Long transactionId = Long.parseLong(value);
        // 4.使用transactionId查询支付信息
        PaymentTransactionEntity paymentTransaction = paymentTransactionMapper.selectById(transactionId);
        if (paymentTransaction == null) {
            return setResultError("未查询到该支付信息");
        }
        return setResultSuccess(BeanConversionUtils.doToDto(paymentTransaction, PayMentTransacDTO.class));
    }
}
