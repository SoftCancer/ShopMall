package com.dongl.member.feign;

import com.dongl.member.service.IMemberRegisterService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: app-member
 * @author: YaoGuangXun
 * @date: 2020/2/8 18:52
 * @Version: 1.0
 */
//@FeignClient("app-member")
public interface IMemberRegisterServiceFeign extends IMemberRegisterService {
}
