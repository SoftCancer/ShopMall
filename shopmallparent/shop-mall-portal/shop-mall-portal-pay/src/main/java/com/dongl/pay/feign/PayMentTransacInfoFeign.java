package com.dongl.pay.feign;

import com.dongl.pay.service.IPayMentTransacInfoService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-pay")
public interface PayMentTransacInfoFeign extends IPayMentTransacInfoService{

}
