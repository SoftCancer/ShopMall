package com.dongl.member.service;

import com.dongl.core.base.BaseResponse;
import com.dongl.entity.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 会员服务接口
 * @author: YaoGuangXun
 * @date: 2020/2/1 15:01
 * @Version: 1.0
 */
@Api(tags = "会员接口")
public interface IMemberService {


    /*
     * 会员服务接口调用微信接口
     */
    @ApiOperation(value = "会员调用微信接口")
    @GetMapping("/memberToWeiXin")
    public BaseResponse memberToWeiXin();


    @ApiOperation(value = "根据手机号查询数据")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query",name = "mobile",dataType = "String",required = true,value = "用户手机号")})
    @PostMapping("mobileExist")
    BaseResponse mobileExist(@RequestParam("mobile") String mobile);

}
