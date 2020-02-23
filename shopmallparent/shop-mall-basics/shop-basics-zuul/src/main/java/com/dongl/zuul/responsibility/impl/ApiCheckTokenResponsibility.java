package com.dongl.zuul.responsibility.impl;

import com.dongl.zuul.responsibility.IGatewayResponsibility;
import com.dongl.zuul.responsibility.base.BaseHandler;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 第三方接入验证 token
 * @author: YaoGuangXun
 * @date: 2020/2/22 13:04
 * @Version: 1.0
 */
@Component
@Slf4j
public class ApiCheckTokenResponsibility extends BaseHandler implements IGatewayResponsibility {

    @Override
    public void service(RequestContext ctx, String ipAddres , HttpServletRequest req, HttpServletResponse response) {
        log.info(">>>>>>>>>> 第三关 ：API 验证签名token！ ");
        nextGatewayResponsibility.service(ctx, ipAddres, req, response);
    }
}
