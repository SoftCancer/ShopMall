package com.dongl.pay.mapper;

import com.dongl.pay.mapper.entity.PaymentChannelEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: 支付渠道mapper类
 * @author: YaoGuangXun
 * @date: 2020/2/18 11:17
 * @Version: 1.0
 */
public interface PaymentChannelMapper {

    @Select("SELECT channel_Name  AS channelName , channel_Id AS channelId, merchant_Id AS merchantId,sync_Url AS syncUrl, asyn_Url AS asynUrl,public_Key AS publicKey, private_Key AS privateKey,channel_State AS channelState ,class_ADDRES as classAddres ,retry_beanid as retryBeanId     FROM payment_channel WHERE CHANNEL_STATE='0';")
    public List<PaymentChannelEntity> selectAll();

    @Select("SELECT channel_Name  AS channelName , channel_Id AS channelId, merchant_Id AS merchantId,sync_Url AS syncUrl, " +
            " asyn_Url AS asynUrl,public_Key AS publicKey, private_Key AS privateKey,channel_State AS channelState ," +
            " class_ADDRES as classAddres ,retry_beanid as retryBeanId     FROM payment_channel WHERE CHANNEL_STATE='0' and channel_Id = #{channelId};")
    PaymentChannelEntity selectPCByChannelId(String channelId);
}
