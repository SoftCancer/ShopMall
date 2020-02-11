package com.xxl.sso.server.figen;

import com.dongl.member.service.IMemberService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/914:41
 * @Version: 1.0
 */
@FeignClient("app-member")
public interface IMemberServiceFeign extends IMemberService {

}
