package com.dongl.zuul.filter;

import com.dongl.web.utils.NetworkUtils;
import com.dongl.zuul.mapper.BlacklistMapper;
import com.dongl.zuul.mapper.entity.MeiteBlacklist;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 通过网关实现 IP黑名单拦截，（防止恶意请求攻击），ip限流。
 * @author: YaoGuangXun
 * @date: 2020/2/21 13:45
 * @Version: 1.0
 */
@Component
@Slf4j
public class GatewayFilter extends ZuulFilter {

    @Autowired
    private BlacklistMapper blacklistMapper;

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request =  requestContext.getRequest();
        String ipAddress = NetworkUtils.getIpAddr(request);
        // 根据ip地址查询数据库中是否设置了黑名单，可以把黑名单存入Redis或Apollo中
        MeiteBlacklist meiteBlacklist = blacklistMapper.findBlacklist(ipAddress);
        // 不等于空说明被拉入了黑名单，进入判断返回。
        if (meiteBlacklist != null){
            log.info(ipAddress + "Access restricted ");
            resultInsufficientAuthority(requestContext,"Access restricted");
        }

//        HttpServletResponse response = requestContext.getResponse();
        return null;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public String filterType() {
        return null;
    }

    // ip地址存在一个问题
    private void resultInsufficientAuthority(RequestContext ctx, String errorMsg) {
        baseResultErrorBase(ctx, 401, errorMsg);
    }

    private void resultError(RequestContext ctx, String errorMsg) {
        baseResultErrorBase(ctx, 500, errorMsg);
    }

    private void baseResultErrorBase(RequestContext ctx, int code, String errorMsg) {
        ctx.setResponseStatusCode(500);
        // 网关响应为false 不会转发服务
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody(errorMsg);
    }

}
