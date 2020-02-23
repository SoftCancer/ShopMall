package com.dongl.zuul.responsibility.impl;

import com.dongl.zuul.responsibility.IGatewayResponsibility;
import com.dongl.zuul.responsibility.base.BaseHandler;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 频繁操作限流
 * @author: YaoGuangXun
 * @date: 2020/2/23 16:32
 * @Version: 1.0
 */
@Component
@Slf4j
public class SeckillCurrentLimitResponsibility extends BaseHandler implements IGatewayResponsibility {

    // Google 的限流插件，每秒时间存入令牌桶中的Token 为 1个， 1 写到分布式配置中心中。
    private RateLimiter rateLimiter = RateLimiter.create(1);

    @Override
    public void service(RequestContext ctx, String ipAddres, HttpServletRequest req, HttpServletResponse response) {
        log.info(">>>>>>>>>> 第一关Api 接口的限流！ ");
        // 1.实现令牌桶限流，判断是否可以在指定的时间内从ratelimiter获得一个许可，未获得许可的话，立即返回false。
        Boolean tryAcquire = rateLimiter.tryAcquire(0,TimeUnit.SECONDS);
        if (!tryAcquire){
            resultError(500,ctx,"当前排队人数过多，请稍后重试...");
            return;
        }
        // 继续走下一个流程判断
        nextGatewayResponsibility.service(ctx,ipAddres,req,response);
    }
}

