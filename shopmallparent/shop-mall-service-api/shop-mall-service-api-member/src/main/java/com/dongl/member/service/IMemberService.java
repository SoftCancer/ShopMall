package com.dongl.member.service;

import com.dongl.base.BaseResponse;
import com.dongl.member.input.dto.UserInpDTO;
import com.dongl.member.output.dto.UserOutDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
//    @ApiOperation(value = "会员调用微信接口")
//    @GetMapping("/memberToWeiXin")
//    public BaseResponse memberToWeiXin();

    /**
     * @Description: 用户注册方法
     * @Author: YaoGuangXun
     * @Date: 2020/2/6 1:00
     **/
    @PostMapping("/register")
    @ApiOperation(value = "会员用户注册信息接口")
    BaseResponse registeredUser(@RequestBody UserInpDTO userInpDTO, @RequestParam("registCode") String registCode);

    @ApiOperation(value = "根据手机号查询数据")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query",name = "mobile",dataType = "String",required = true,value = "用户手机号")})
    @PostMapping("mobileExist")
    BaseResponse mobileExist(@RequestParam("mobile") String mobile);


    /**
     * 根据token查询用户信息
     *
     * @return
     */
    @GetMapping("/getUserInfo")
    @ApiOperation(value = "/getUserInfo")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query",name = "token",dataType = "String",required = true,value = "用户token")})
    BaseResponse<UserOutDTO> getInfo(@RequestParam("token") String token);
}
