package com.dongl.member.feign;

import com.dongl.member.service.IMemberLoginService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/913:39
 * @Version: 1.0
 */
@FeignClient(name = "app-member",contextId = "example")
public interface IMemberLoginServiceFeign extends IMemberLoginService {
}
