package com.dongl.zuul.filter;

import com.dongl.sign.SignUtil;
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
import java.util.Map;

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
        HttpServletRequest request = requestContext.getRequest();
        String ipAddress = NetworkUtils.getIpAddr(request);
        // 根据ip地址查询数据库中是否设置了黑名单，可以把黑名单存入Redis或Apollo中
        MeiteBlacklist meiteBlacklist = blacklistMapper.findBlacklist(ipAddress);
        // 不等于空说明被拉入了黑名单，进入判断返回。
        if (meiteBlacklist != null) {
            log.info(ipAddress + "Access restricted ");
            resultInsufficientAuthority(requestContext, "Access restricted");
        }

        Map map = SignUtil.toVerifyMap(request.getParameterMap(), false);
        if (!SignUtil.verify(map)){
            log.info(map + "sign file ");
        }
//        HttpServletResponse response = requestContext.getResponse();
        return null;
    }

    /**
     * @Description: 该方法用于开启网关，true ：开启， false ：不开启
     * @Author: YaoGuangXun
     * @Date: 2020/2/21 17:55
     **/
    @Override
    public boolean shouldFilter() {
        return true;  // 是否执行该过滤器，此处为true，说明需要过滤
    }

    /**
     * @Description:  // 优先级为0，数字越大，优先级越低
     * @Author: YaoGuangXun
     * @Date: 2020/2/21 18:14
     **/
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * @Description: 设置过滤器类型：pre:前置过滤器
     * @Author: YaoGuangXun
     * @Date: 2020/2/21 18:14
     **/
    @Override
    public String filterType() {
        return "pre";
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
