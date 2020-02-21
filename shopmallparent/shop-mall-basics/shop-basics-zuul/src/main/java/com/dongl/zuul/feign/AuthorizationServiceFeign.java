package com.dongl.zuul.feign;

import com.dongl.auth.service.api.IAuthorizationService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-dongl-auth")
public interface AuthorizationServiceFeign extends IAuthorizationService {

}
