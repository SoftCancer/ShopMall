package com.dongl.zuul.filter;

import com.dongl.sign.SignUtil;
import com.dongl.web.utils.NetworkUtils;
import com.dongl.zuul.builder.GatewayDirectorBuild;
import com.dongl.zuul.builder.impl.VerificationBuild;
import com.dongl.zuul.mapper.BlacklistMapper;
import com.dongl.zuul.mapper.entity.MeiteBlacklist;
import com.dongl.zuul.responsibility.ResponsibilityClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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
    private GatewayDirectorBuild gatewayDirectorBuild;

    @Autowired
    private ResponsibilityClient responsibilityClient;
    @Override
    public Object run() throws ZuulException {
        // 1.获取请求对象
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();

        // 2.获取真实ip
        String ipAddress = NetworkUtils.getIpAddr(request);

        /**
         *   // 3. 通过建造者模式整合 ip拦截，参数验签，xss等
         *   gatewayDirectorBuild.director(requestContext,ipAddress,request);
         **/
        // 3. 把上边的建造者模式修改为 责任链模式
        responsibilityClient.responsibility(context,ipAddress,request,response);

        // 4. 过滤请求参数、防止XSS攻击
        Map<String, List<String>> filterParameters = filterParameters(request,context);
        context.setRequestQueryParams(filterParameters);
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

    /**
     * 过滤参数
     */
    private Map<String, List<String>> filterParameters(HttpServletRequest request, RequestContext ctx) {
        Map<String, List<String>> requestQueryParams = ctx.getRequestQueryParams();
        if (requestQueryParams == null) {
            requestQueryParams = new HashMap<>();
        }
        Enumeration em = request.getParameterNames();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            String value = request.getParameter(name);
            ArrayList<String> arrayList = new ArrayList<>();
            // 将参数转化为html参数 防止xss攻击 < 转为&lt;
            arrayList.add(StringEscapeUtils.escapeHtml(value));
            requestQueryParams.put(name, arrayList);
        }
        return requestQueryParams;
    }


}
