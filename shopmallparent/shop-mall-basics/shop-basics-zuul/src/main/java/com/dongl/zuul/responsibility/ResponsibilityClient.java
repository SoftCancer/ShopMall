package com.dongl.zuul.responsibility;

import com.dongl.zuul.responsibility.factory.FactoryHandler;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description: 责任链调用，在网关过滤器(GatewayFilter.java)中调用该方法。
 * 该项目中暂未使用
 * @author: YaoGuangXun
 * @date: 2020/2/22 13:28
 * @Version: 1.0
 */
@Component
public class ResponsibilityClient {

    /**
     * @Description:
     * @Author: YaoGuangXun
     * @Date: 2020/2/22 13:29
     **/
    public void responsibility(RequestContext ctx,String ipAddres, HttpServletRequest req, HttpServletResponse response) {

        IGatewayResponsibility gatewayResponsibility = FactoryHandler.getOneResponsibility();
        gatewayResponsibility.service(ctx,ipAddres,req,response);
    }
}
