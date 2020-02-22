package com.dongl.zuul.responsibility.factory;

import com.dongl.core.utils.SpringContextUtil;
import com.dongl.zuul.responsibility.IGatewayResponsibility;

import java.util.ArrayList;
import java.util.List;

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
        // 1.黑名单判断
        IGatewayResponsibility gatewayResponsibility_1= (IGatewayResponsibility) SpringContextUtil.getBean("blacklistResponsibility");
        // 2. api验证签名
        IGatewayResponsibility gatewayResponsibility_2 = (IGatewayResponsibility) SpringContextUtil.getBean("apiAuthResponsibility");
        gatewayResponsibility_1.setNextResponsibility(gatewayResponsibility_2);
        // 3. api 接口验证token
        IGatewayResponsibility gatewayResponsibility_3 = (IGatewayResponsibility) SpringContextUtil.getBean("apiCheckTokenResponsibility");
        gatewayResponsibility_2.setNextResponsibility(gatewayResponsibility_3);
        return gatewayResponsibility_1;
    }
}
