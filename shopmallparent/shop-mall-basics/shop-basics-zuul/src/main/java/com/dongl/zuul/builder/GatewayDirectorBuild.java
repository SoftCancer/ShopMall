package com.dongl.zuul.builder;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 组装（链接）建造者接口
 * @author: YaoGuangXun
 * @date: 2020/2/21 19:38
 * @Version: 1.0
 */
@Slf4j
@Component
public class GatewayDirectorBuild {

    @Resource(name = "verificationBuild")
    private GatewayAuthorityBuilder gatewayBuild;

    public void director(RequestContext context, String ipAddres, HttpServletRequest request){
        // 1. 拦截ip黑名单
       Boolean blackBlock =  gatewayBuild.blackBlock(context,ipAddres);
       // blackBlock ：false 说明已被加入黑名单，直接return。
       if (!blackBlock){
           log.info("地址： {}"+ ipAddres+ " 已被加入黑名单！如需允许访问，请移除黑名单！");
           return;
       }

       /*
       // 2.参数防篡改验证
       Boolean totoVerify = gatewayBuild.toVerifyMap(context,ipAddres,request);
       // 返回：false,说明参数被修改
       if (!totoVerify){
           log.info("地址：{}"+ ipAddres+ " 参数已被篡改！无法进行验签！");
           return;
       }
        */
       // 3. 第三方认证验证 ，测试该步骤时需要暂时注释掉 2. 参数防篡改，因为 2.请求时还没有参数认证。
       Boolean apiAuth = gatewayBuild.apiAuthority(context,request);
        if (!apiAuth){
            log.info("地址：{}"+ ipAddres+ " 暂无权限访问！");
            return;
        }
    }

}
