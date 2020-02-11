package com.dongl.portal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.dongl.base.BaseResponse;
import com.dongl.member.feign.IMemberServiceFeign;
import com.dongl.member.output.dto.UserOutDTO;
import com.dongl.web.base.BaseWebController;
import com.dongl.web.constants.WebConstants;
import com.dongl.web.utils.CookieUtils;
import com.dongl.web.utils.JsonUtils;
import core.conf.Conf;
import core.util.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Controller和@RestController的区别 ：
 * 1.如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，
 * 配置的视图解析器InternalResourceViewResolver不起作用，返回的内容就是Return 后面的内容。
 * 2.如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
 * @author: YaoGuangXun
 * @date: 2020/2/7 23:35
 * @Version: 1.0
 */
@Controller
@Slf4j
public class IndexController extends BaseWebController {

    @Autowired
    private IMemberServiceFeign memberServiceFeign;

    /**
     * 跳转到index页面
     */
    private static final String INDEX_FTL = "index";

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {


        String xxl_sso_sessionid = Conf.SSO_SESSIONID;
        /* 第一种方法 ： */
        String sessionId = request.getParameter(xxl_sso_sessionid);


        /* 第二种方法 ：
            String s = CookieUtils.getCookieValue(request,xxl_sso_sessionid);
        */

        if (!"".equals(sessionId)) {
            String redisKey = CookieUtils.dealRedisKeyByCookie(xxl_sso_sessionid, sessionId);
            Object objStr = JedisUtil.getObjectValue(redisKey);
            JSONObject jsonObject = JsonUtils.objToJSONObject(objStr);

            UserOutDTO userOutDTO = JSON.toJavaObject(jsonObject, UserOutDTO.class);
            if (userOutDTO != null) {

                String mobile = userOutDTO.getMobile();
                if (!"".equals(mobile) && null != mobile) {
                    // 对手机号码实现脱敏
                    String desensMobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
                    model.addAttribute("desensMobile", desensMobile);
                } else {
                    String userName = userOutDTO.getUserName();
                    model.addAttribute("desensMobile", userName);
                }
            }
            return INDEX_FTL;
        }

        // 1.从cookie 中 获取 会员token
        String token = CookieUtils.getCookieValue(request, WebConstants.LOGIN_TOKEN_COOKIENAME, true);
        if (!StringUtils.isEmpty(token)) {
            // 2.调用会员服务接口,查询会员用户信息
            BaseResponse<UserOutDTO> userInfo = memberServiceFeign.getInfo(token);
            if (isSuccess(userInfo)) {
                UserOutDTO data = userInfo.getData();
                if (data != null) {
                    String mobile = data.getMobile();
                    // 对手机号码实现脱敏
                    String desensMobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
                    model.addAttribute("desensMobile", desensMobile);
                }

            }

        }
        return INDEX_FTL;
    }
}
