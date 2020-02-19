package com.dongl.pay.feign;

import com.dongl.pay.service.IPayContextService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-pay")
public interface PayContextFeign extends IPayContextService {

}
