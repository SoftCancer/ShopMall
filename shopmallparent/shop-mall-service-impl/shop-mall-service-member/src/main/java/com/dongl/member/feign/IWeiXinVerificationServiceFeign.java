package com.dongl.member.feign;

import com.dongl.weixin.service.IWeiXinVerificationService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/615:00
 * @Version: 1.0
 */
@FeignClient("app-weixin")
public interface IWeiXinVerificationServiceFeign extends IWeiXinVerificationService {
}
