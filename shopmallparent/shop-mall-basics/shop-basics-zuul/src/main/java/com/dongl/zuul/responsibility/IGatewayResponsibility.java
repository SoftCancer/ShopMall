package com.dongl.zuul.responsibility;

import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 责任链设计模式 ，处理ip黑名单，验签等
 * @author: YaoGuangXun
 * @date: 2020/2/22 12:45
 * @Version: 1.0
 */
public interface IGatewayResponsibility {

    public void service(RequestContext ctx,String ipAddres, HttpServletRequest req, HttpServletResponse response);

    /**
     * 执行下一个任务
     **/
    public void setNextResponsibility(IGatewayResponsibility gatewayResponsibility);
}
