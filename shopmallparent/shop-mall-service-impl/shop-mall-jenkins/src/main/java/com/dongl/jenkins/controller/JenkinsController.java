package com.dongl.jenkins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/24 12:43
 * @Version: 1.0
 */
@RestController
public class JenkinsController {

    @GetMapping("/jenkins")
    public String getJenkins(){
        return " 搭建 docker+Jenkins 环境实现自动化部署！";
    }
}
