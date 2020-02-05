package com.dongl.weixin.service;

import com.alibaba.fastjson.JSONObject;
import com.dongl.core.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: 微信服务注册码验证服务
 * @author: YaoGuangXun
 * @date: 2020/2/516:27
 * @Version: 1.0
 */

@Api(tags = "注册码验证接口")
public interface IWeiXinVerificationService {

    @ApiOperation(value = "根据手机号码验证token 是否正确")
    @GetMapping("/verificationCode")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "phone",dataType = "String",required = true,value = "用户手机号"),
            @ApiImplicitParam(paramType = "query",name = "weixinCode",dataType = "String",required = true,value = "微信注册码")
    })
    public BaseResponse<JSONObject> verificationCode(String phone,String weixinCode);
}

