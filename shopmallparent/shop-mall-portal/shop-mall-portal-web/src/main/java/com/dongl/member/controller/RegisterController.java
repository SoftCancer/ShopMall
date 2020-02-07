package com.dongl.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description: 注册控制类
 * @author: YaoGuangXun
 * @date: 2020/2/7 23:54
 * @Version: 1.0
 */
@Controller
public class RegisterController {
    /* 跳转到注册页面 */
    private static final String MB_REGISTER_PAGE= "/member/register";

    /**
     *  注册方法
     * @Author: YaoGuangXun
     * @Date: 2020/2/7 23:59
     **/
    @GetMapping("/register.html")
    public String getRegister(){
        return MB_REGISTER_PAGE;
    }

    @PostMapping("/register.html")
    public String postRegister(){
        return MB_REGISTER_PAGE;
    }



}
