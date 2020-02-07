package com.dongl.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/7 23:44
 * @Version: 1.0
 */
@Controller
public class LoginController {

    /* 跳转到登陆页面*/
    private static final String MB_LOGIN_FTL = "login";

    /**
     * @Description: 跳转到登陆页面
     * @Author: YaoGuangXun
     * @Date: 2020/2/7 23:53
     **/
    @GetMapping("/login")
    public String getLogin(){
        return MB_LOGIN_FTL;
    }

    /**
     * @Description: 接受参数
     * @Author: YaoGuangXun
     * @Date: 2020/2/7 23:53
     **/
    @PostMapping("/login")
    public String postLogin(){
        return MB_LOGIN_FTL;
    }
}
