package com.dongl.zuul.responsibility.impl;

import com.dongl.zuul.responsibility.IGatewayResponsibility;
import com.dongl.zuul.responsibility.base.BaseHandler;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: API 接口验证签名
 * @author: YaoGuangXun
 * @date: 2020/2/22 13:02
 * @Version: 1.0
 */
@Component
@Slf4j
public class ApiAuthResponsibility extends BaseHandler implements IGatewayResponsibility {

    @Override
    public void service(RequestContext ctx, String ipAddres, HttpServletRequest req, HttpServletResponse response) {
        this.nextGatewayResponsibility.service(ctx, ipAddres, req, response);
    }
}
