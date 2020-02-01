package com.dongl.member.feign;

import com.dongl.entity.AppEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: 会员调微信服务
 * @author: YaoGuangXun
 * @date: 2020/2/1 15:20
 * @Version: 1.0
 */
// 通过feign 把微信的服务名注册进去。
@FeignClient("app-weixin")
public interface IWeiXinServiceFeign {

    /**
     *  获取应用接口
     **/
    @GetMapping("/getApp")
    public AppEntity getApp();
}
