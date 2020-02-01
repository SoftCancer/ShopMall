package com.dongl.member.service.impl;

import com.dongl.entity.AppEntity;
import com.dongl.member.feign.IWeiXinServiceFeign;
import com.dongl.member.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 实现 会员接口，
 * 需要把会员接口的jar 引入到模块
 * @author: YaoGuangXun
 * @date: 2020/2/1 15:06
 * @Version: 1.0
 */
@RestController
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private IWeiXinServiceFeign weiXinServiceFeign;

    // SpringCloud 的服务通讯 ：rest，feign
    @Override
    public AppEntity memberToWeiXin() {
        return weiXinServiceFeign.getApp();
    }
}
