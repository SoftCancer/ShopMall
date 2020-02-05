package com.dongl.weixin.service.impl;

import com.dongl.core.base.BaseApiService;
import com.dongl.core.base.BaseResponse;
import com.dongl.entity.AppEntity;
import com.dongl.weixin.service.IWeiXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/1 2:26
 * @Version: 1.0
 */
@RestController
public class WeiXinServiceImpl implements IWeiXinService {

    @Autowired
    private BaseApiService baseApiService;

    @Override
    public BaseResponse getApp() {
        AppEntity appEntity = new AppEntity("feign","你的奖品是：iPhone XS Max");
        return baseApiService.setResultSuccess(appEntity);
    }
}
