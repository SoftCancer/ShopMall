package com.dongl.weixin.service;

import com.alibaba.fastjson.JSONObject;
import com.dongl.core.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 微信服务注册码验证服务
 * @author: YaoGuangXun
 * @date: 2020/2/516:27
 * @Version: 1.0
 */

@Api(tags = "注册码验证接口")
public interface IWeiXinVerificationService {


    /**
     *  根据手机号和注册码 用于判断注册码是否正确和过期
     * @Author: YaoGuangXun
     * @Date: 2020/2/6 14:58
     **/
    @ApiOperation(value = "根据手机号码验证token 是否正确")
    @PostMapping("/verificationCode")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "phone",dataType = "String",required = true,value = "用户手机号"),
            @ApiImplicitParam(paramType = "query",name = "weixinCode",dataType = "String",required = true,value = "微信注册码")
    })
    public BaseResponse<JSONObject> verificationCode(@RequestParam("phone") String phone, @RequestParam("weixinCode") String weixinCode);
}

