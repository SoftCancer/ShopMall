package com.dongl.zuul.responsibility.impl;

import com.dongl.zuul.mapper.BlacklistMapper;
import com.dongl.zuul.mapper.entity.MeiteBlacklist;
import com.dongl.zuul.responsibility.IGatewayResponsibility;
import com.dongl.zuul.responsibility.base.BaseHandler;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: ip黑名单拦截处理
 * @author: YaoGuangXun
 * @date: 2020/2/22 12:50
 * @Version: 1.0
 */
@Component
@Slf4j
public class BlacklistResponsibility extends BaseHandler implements IGatewayResponsibility {

    @Autowired
    private BlacklistMapper blacklistMapper;
    @Override
    public void service(RequestContext ctx,String ipAddres ,HttpServletRequest req, HttpServletResponse response) {
        // 根据ip地址查询数据库中是否设置了黑名单，可以把黑名单存入Redis或Apollo中
        MeiteBlacklist meiteBlacklist = blacklistMapper.findBlacklist(ipAddres);
        // 不等于空说明被拉入了黑名单，进入判断返回 false。
        if (meiteBlacklist != null) {
            log.info(ipAddres + "Access restricted ");
            resultError(400,ctx, "Access restricted");
            return;
        }

        nextGatewayResponsibility.service(ctx,ipAddres,req,response);
    }
}
