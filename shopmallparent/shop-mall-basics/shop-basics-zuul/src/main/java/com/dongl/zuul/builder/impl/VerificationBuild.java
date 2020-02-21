package com.dongl.zuul.builder.impl;

import com.dongl.sign.SignUtil;
import com.dongl.zuul.builder.GatewayAuthorityBuilder;
import com.dongl.zuul.mapper.BlacklistMapper;
import com.dongl.zuul.mapper.entity.MeiteBlacklist;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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



    private void resultError(RequestContext ctx, String errorMsg) {
        ctx.setResponseStatusCode(401);
        // 网关响应为false 不会转发服务
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody(errorMsg);
    }
}
