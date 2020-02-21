package com.dongl.zuul.builder;

import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 建造者模式，用于封装网关过滤中的 ip拦截，请求参数验签，XSS攻击等功能。
 * @author: YaoGuangXun
 * @date: 2020/2/2119:20
 * @Version: 1.0
 */
public interface GatewayAuthorityBuilder {

    /**
     * @Description: 黑名单拦截
     * @Author: YaoGuangXun
     * @Date: 2020/2/21 19:27
     **/
    Boolean blackBlock(RequestContext context , String ipAddres);

    /**
     * @Description: 参数验证，验签防篡改
     * @Author: YaoGuangXun
     * @Date: 2020/2/21 19:27
     **/
    Boolean toVerifyMap(RequestContext context , String ipAddres , HttpServletRequest request);
}
