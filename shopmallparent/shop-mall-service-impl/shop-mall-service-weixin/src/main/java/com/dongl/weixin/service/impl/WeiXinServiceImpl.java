package com.dongl.weixin.service.impl;

import com.dongl.entity.AppEntity;
import com.dongl.weixin.service.IWeiXinService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/1 2:26
 * @Version: 1.0
 */
@RestController
public class WeiXinServiceImpl implements IWeiXinService {


    @Override
    public AppEntity getApp() {
        return new AppEntity("feign","iPhone XS Max");
    }
}
