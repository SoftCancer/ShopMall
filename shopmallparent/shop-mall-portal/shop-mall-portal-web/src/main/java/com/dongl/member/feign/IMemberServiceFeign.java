package com.dongl.member.feign;

import com.dongl.member.service.IMemberService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/914:41
 * @Version: 1.0
 */
@FeignClient(name = "app-member",contextId = "server")
public interface IMemberServiceFeign extends IMemberService {

}
