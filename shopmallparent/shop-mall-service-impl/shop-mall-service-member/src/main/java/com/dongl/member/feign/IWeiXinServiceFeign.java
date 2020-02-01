package com.dongl.member.feign;

import com.dongl.weixin.service.IWeiXinService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 会员调微信服务
 * @author: YaoGuangXun
 * @date: 2020/2/1 15:20
 * @Version: 1.0
 */
// 通过feign 把微信的服务名注册进去。
@FeignClient("app-weixin")
public interface IWeiXinServiceFeign extends IWeiXinService {

    /**
     *  获取应用接口 ，
     *  注释 ：在模块中引入 微信接口依赖 直接接口继承即可；
     **/
//    @GetMapping("/getApp")
//    public AppEntity getApp();
}
