package com.dongl.weixin.service;

import com.dongl.entity.AppEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/12:22
 * @Version: 1.0
 */
public interface IWeiXinService {

    @GetMapping("/getApp")
    public AppEntity getApp();
}
