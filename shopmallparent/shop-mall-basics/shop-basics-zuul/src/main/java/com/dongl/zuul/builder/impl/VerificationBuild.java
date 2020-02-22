package com.dongl.zuul.builder.impl;

import com.dongl.base.BaseResponse;
import com.dongl.constants.Constants;
import com.dongl.sign.SignUtil;
import com.dongl.zuul.builder.GatewayAuthorityBuilder;
import com.dongl.zuul.feign.AuthorizationServiceFeign;
import com.dongl.zuul.mapper.BlacklistMapper;
import com.dongl.zuul.mapper.entity.MeiteBlacklist;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description: 实现建造者模式
 * @author: YaoGuangXun
 * @date: 2020/2/21 19:30
 * @Version: 1.0
 */
@Slf4j
@Component
public class VerificationBuild implements GatewayAuthorityBuilder {
    @Autowired
    private BlacklistMapper blacklistMapper;

    @Autowired
    private AuthorizationServiceFeign verificaCodeServiceFeign;

    @Override
    public Boolean blackBlock(RequestContext context, String ipAddres) {

        // 根据ip地址查询数据库中是否设置了黑名单，可以把黑名单存入Redis或Apollo中
        MeiteBlacklist meiteBlacklist = blacklistMapper.findBlacklist(ipAddres);
        // 不等于空说明被拉入了黑名单，进入判断返回 false。
        if (meiteBlacklist != null) {
            log.info(ipAddres + "Access restricted ");
            resultError(context, "Access restricted");
            return false;
        }
        return true;
    }

    @Override
    public Boolean toVerifyMap(RequestContext context, String ipAddres, HttpServletRequest request) {
        Map<String,String[]> parameterMap = request.getParameterMap();
        Map map = SignUtil.toVerifyMap(parameterMap, false);
        // 签名验证结果被篡改，返回：false
        if (!SignUtil.verify(map)){
            resultError(context, "ip:" + ipAddres + ",Sign fail");
            return false;
        }
        return true;
    }


    @Override
    public Boolean apiAuthority(RequestContext ctx, HttpServletRequest request) {
        // 1. 获取请求路径
        String path = request.getServletPath();
        log.info(">>>>>servletPath:" + path + ",servletPath.substring(0, 5):" + path.substring(0, 5));
        // 2. 截取路径中是否包含，/public 字符串，不包含说明为站内访问，直接放行。
        if (!path.substring(0, 7).equals("/public")) {
            return true;
        }

        // 3. 获取token
        String accessToken = request.getParameter("accessToken");
        log.info(">>>>>accessToken验证:" + accessToken);
        if (StringUtils.isEmpty(accessToken)){
            resultError(ctx,"accessToken cannot be empty!");
            return false;
        }

        // 4. 调用接口验证accessToken是否失效
        BaseResponse appInfo = verificaCodeServiceFeign.getAppInfo(accessToken);
        if (!isSuccess(appInfo)){
            resultError(ctx, appInfo.getMsg());
            return false;
        }
        return true;
    }

    private void resultError(RequestContext ctx, String errorMsg) {
        ctx.setResponseStatusCode(401);
        // 网关响应为false 不会转发服务
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody(errorMsg);
    }

    // 接口直接返回true 或者false
    public Boolean isSuccess(BaseResponse<?> baseResp) {
        if (baseResp == null) {
            return false;
        }
        if (!baseResp.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            return false;
        }
        return true;
    }
}
