package com.dongl.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/11 21:56
 * @Version: 1.0
 */
@Controller
public class PayController {

    @GetMapping("/pay")
    public String payIndex(){
        return "index";
    }
}
