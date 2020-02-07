package com.dongl.member.service;

import com.dongl.base.BaseResponse;
import com.dongl.member.input.dto.UserInpDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 用户注册
 * @author: YaoGuangXun
 * @date: 2020/2/5 22:25
 * @Version: 1.0
 */
@Api(tags = "会员注册接口")
public interface IMemberRegisterService {

    /**
     * @Description: 用户注册方法
     * @Author: YaoGuangXun
     * @Date: 2020/2/6 1:00
     **/
    @PostMapping("/register")
    @ApiOperation(value = "会员用户注册信息接口")
    BaseResponse registeredUser(@RequestBody UserInpDTO userInpDTO, @RequestParam("registCode") String registCode);
}
