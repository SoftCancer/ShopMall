package com.dongl.pay.feign;

import com.dongl.pay.service.IPaymentChannelService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-pay")
public interface PaymentChannelFeign extends IPaymentChannelService {

}
