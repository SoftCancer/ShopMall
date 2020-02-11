package com.xxl.sso.server.controller;

import com.dongl.base.BaseResponse;
import com.dongl.constants.Constants;
import com.dongl.member.input.dto.UserLoginInpDTO;
import com.dongl.member.output.dto.UserOutDTO;
import com.dongl.web.base.BaseWebController;
import com.xxl.sso.server.figen.IMemberServiceFeign;
import com.xxl.sso.server.service.UserService;
import core.conf.Conf;
import core.login.SsoWebLoginHelper;
import core.store.SsoLoginStore;
import core.store.SsoSessionIdHelper;
import core.user.XxlSsoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * sso server (for web)
 *
 * @author xuxueli 2017-08-01 21:39:47
 */
@Controller
public class WebController extends BaseWebController {

    @Autowired
    private UserService userService;

    @Autowired
    private IMemberServiceFeign memberServiceFeign;


    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {

        // login check
        XxlSsoUser xxlUser = SsoWebLoginHelper.loginCheck(request, response);

        if (xxlUser == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("xxlUser", xxlUser);
            return "index";
        }
    }

    /**
     * Login page
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(Conf.SSO_LOGIN)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response) {

        // login check
        XxlSsoUser xxlUser = SsoWebLoginHelper.loginCheck(request, response);

        if (xxlUser != null) {
            // success redirect
            String redirectUrl = request.getParameter(Conf.REDIRECT_URL);
            if (redirectUrl!=null && redirectUrl.trim().length()>0) {

                String sessionId = SsoWebLoginHelper.getSessionIdByCookie(request);
                String redirectUrlFinal = redirectUrl + "?" + Conf.SSO_SESSIONID + "=" + sessionId;;

                return "redirect:" + redirectUrlFinal;
            } else {
                return "redirect:/";
            }
        }

        model.addAttribute("errorMsg", request.getParameter("errorMsg"));
        model.addAttribute(Conf.REDIRECT_URL, request.getParameter(Conf.REDIRECT_URL));
        return "login";
    }

    /**
     * Login
     *
     * @param request
     * @param redirectAttributes
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request,
                        HttpServletResponse response,
                        RedirectAttributes redirectAttributes,
                        String username,
                        String password,
                        String ifRemember) {

        boolean ifRem = (ifRemember!=null&&"on".equals(ifRemember))?true:false;

        /*  接入自己项目的登录认证接口 start */
        UserLoginInpDTO userLoginInpDTO = new UserLoginInpDTO();
        userLoginInpDTO.setMobile(username);
        userLoginInpDTO.setPassword(password);
        String browserInfo = webBrowserInfo(request);
        userLoginInpDTO.setDeviceInfor(browserInfo);
        userLoginInpDTO.setLoginType(Constants.MEMBER_LOGIN_TYPE_PC);
        // 调用会员的SSO登录接口，登陆成功返回用户信息
        BaseResponse ssoLogin = memberServiceFeign.ssoLogin(userLoginInpDTO);

        if (!isSuccess(ssoLogin)){
            redirectAttributes.addAttribute("errorMsg", ssoLogin.getMsg());
            redirectAttributes.addAttribute(Conf.REDIRECT_URL, request.getParameter(Conf.REDIRECT_URL));
            return "redirect:/login";
        }

        UserOutDTO user = (UserOutDTO) ssoLogin.getData();
        if (null == user){
            redirectAttributes.addAttribute("errorMsg", "用户信息不存在");
            redirectAttributes.addAttribute(Conf.REDIRECT_URL, request.getParameter(Conf.REDIRECT_URL));
            return "redirect:/login";
        }
        /*  接入自己项目的登录认证接口 end  */

        // 1、make xxl-sso user
        XxlSsoUser xxlUser = new XxlSsoUser();
        xxlUser.setUserid(String.valueOf(user.getUserId()));
        xxlUser.setUsername(user.getUserName());
        xxlUser.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        xxlUser.setExpireMinute(SsoLoginStore.getRedisExpireMinute());
        xxlUser.setExpireFreshTime(System.currentTimeMillis());


        // 2、make session id
        String sessionId = SsoSessionIdHelper.makeSessionId(xxlUser);

        // 3、login, store storeKey + cookie sessionId
        SsoWebLoginHelper.login(response, sessionId, xxlUser, ifRem);

        // 4、return, redirect sessionId
        String redirectUrl = request.getParameter(Conf.REDIRECT_URL);
        if (redirectUrl!=null && redirectUrl.trim().length()>0) {
            String redirectUrlFinal = redirectUrl + "?" + Conf.SSO_SESSIONID + "=" + sessionId;
            return "redirect:" + redirectUrlFinal;
        } else {
            return "redirect:/";
        }

    }

    /**
     * Logout
     *
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(Conf.SSO_LOGOUT)
    public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {

        // logout
        SsoWebLoginHelper.logout(request, response);

        redirectAttributes.addAttribute(Conf.REDIRECT_URL, request.getParameter(Conf.REDIRECT_URL));
        return "redirect:/login";
    }


}
