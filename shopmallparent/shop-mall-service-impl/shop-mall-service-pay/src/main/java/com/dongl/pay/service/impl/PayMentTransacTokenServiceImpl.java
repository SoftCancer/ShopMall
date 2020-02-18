package com.dongl.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dongl.base.BaseApiService;
import com.dongl.base.BaseResponse;
import com.dongl.core.token.GenerateToken;
import com.dongl.pay.input.dto.PayCratePayTokenDto;
import com.dongl.pay.mapper.PaymentTransactionMapper;
import com.dongl.pay.mapper.entity.PaymentTransactionEntity;
import com.dongl.pay.service.IPayMentTransacTokenService;
import com.dongl.twitter.SnowflakeIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 第一步 ：存储预支付记录，同时生成Token，并返回token，
 * 用于在 PayMentTransacTokenServiceImpl 中查询预支付信息
 * @author: YaoGuangXun
 * @date: 2020/2/18 12:31
 * @Version: 1.0
 */
@RestController
@Slf4j
public class PayMentTransacTokenServiceImpl extends BaseApiService implements IPayMentTransacTokenService {

    @Autowired
    private PaymentTransactionMapper paymentTransactionMapper;

    @Autowired
    private GenerateToken generateToken;
    @Override
    public BaseResponse<JSONObject> cratePayToken(PayCratePayTokenDto payCratePayTokenDto) {
//        payCratePayTokenDto.setUserId(1234L);
//        payCratePayTokenDto.setOrderId("300077d11");
//        payCratePayTokenDto.setPayAmount(1000L);
        String orderId = payCratePayTokenDto.getOrderId();
        if (StringUtils.isEmpty(orderId)) {
            return setResultError("订单号码不能为空!");
        }
        Long payAmount = payCratePayTokenDto.getPayAmount();
        if (payAmount == null) {
            return setResultError("金额不能为空!");
        }
        Long userId = payCratePayTokenDto.getUserId();
        if (userId == null) {
            return setResultError("userId不能为空!");
        }
//        log.info(">>>>>orderDes:{}", payCratePayTokenDto.getOrderDes());
        PaymentTransactionEntity paymentTransactionEntity = new PaymentTransactionEntity();
        paymentTransactionEntity.setUserId(userId);
        paymentTransactionEntity.setPayAmount(payAmount);
        paymentTransactionEntity.setOrderId(orderId);
        // 使用雪花算法 生成全局id
        paymentTransactionEntity.setPaymentId(SnowflakeIdUtils.nextId());
        int payment = paymentTransactionMapper.insertPaymentTransaction(paymentTransactionEntity);

        if (!toDaoResult(payment)) {
            return setResultError("系统错误!");
        }
        // 获取主键id
        Long payId = paymentTransactionEntity.getId();
        if (payId == null) {
            return setResultError("系统错误，无法获取主键！");
        }
        // 3.生成对应支付令牌
        String keyPrefix = "pay_";
        String token = generateToken.createToken(keyPrefix,payId.toString());
        JSONObject dataResult = new JSONObject();
        dataResult.put("token", token);
        return setResultSuccess(dataResult);
    }
}
