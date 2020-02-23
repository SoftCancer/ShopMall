package com.dongl.zuul.responsibility.factory;

import com.dongl.core.utils.SpringContextUtil;
import com.dongl.zuul.responsibility.IGatewayResponsibility;


/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/22 13:17
 * @Version: 1.0
 */
public class FactoryHandler {

    /**
     * @Description: 责任链 启动方式 ：
     *  1. 讲每个责任链放到list进行遍历
     * @Author: YaoGuangXun
     * @Date: 2020/2/22 13:26
     **/
    public static IGatewayResponsibility getOneResponsibility(){

        // 1. 频繁操作限流
        IGatewayResponsibility seckillCurrentLimitResponsibility= (IGatewayResponsibility) SpringContextUtil.getBean("seckillCurrentLimitResponsibility");

        // 2.黑名单判断
        IGatewayResponsibility blacklistResponsibility= (IGatewayResponsibility) SpringContextUtil.getBean("blacklistResponsibility");
        seckillCurrentLimitResponsibility.setNextResponsibility(blacklistResponsibility);

        // 3. api验证签名
        IGatewayResponsibility apiAuthResponsibility = (IGatewayResponsibility) SpringContextUtil.getBean("apiAuthResponsibility");
        blacklistResponsibility.setNextResponsibility(apiAuthResponsibility);

        // 4. api 接口验证token
        IGatewayResponsibility apiCheckTokenResponsibility = (IGatewayResponsibility) SpringContextUtil.getBean("apiCheckTokenResponsibility");
        apiAuthResponsibility.setNextResponsibility(apiCheckTokenResponsibility);

        return seckillCurrentLimitResponsibility;
    }
}
