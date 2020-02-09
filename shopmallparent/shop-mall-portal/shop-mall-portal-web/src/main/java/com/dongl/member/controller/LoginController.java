package com.dongl.member.controller;

import com.alibaba.fastjson.JSONObject;
import com.dongl.base.BaseResponse;
import com.dongl.constants.Constants;
import com.dongl.core.bean.BeanConversionUtils;
import com.dongl.member.controller.registervo.LoginVo;
import com.dongl.member.feign.IMemberLoginServiceFeign;
import com.dongl.member.input.dto.UserLoginInpDTO;
import com.dongl.web.base.BaseWebController;
import com.dongl.web.constants.WebConstants;
import com.dongl.web.utils.CookieUtils;
import com.dongl.web.utils.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/7 23:44
 * @Version: 1.0
 */
@Controller
public class LoginController extends BaseWebController {

    /* 跳转到登陆页面*/
    private static final String MB_LOGIN_FTL = "member/login";
    @Autowired
    private IMemberLoginServiceFeign memberLoginServiceFeign;
    /**
     * 重定向到首页
     */
    private static final String REDIRECT_INDEX = "redirect:/index";
    /**
     * @Description: 跳转到登陆页面
     * @Author: YaoGuangXun
     * @Date: 2020/2/7 23:53
     **/
    @GetMapping("/login")
    public String getLogin(){
        return MB_LOGIN_FTL;
    }

    /**
     * @Description: 接受参数
     * @Author: YaoGuangXun
     * @Date: 2020/2/7 23:53
     **/
    @PostMapping("/login")
    public String postLogin(@ModelAttribute("loginVo") LoginVo loginVo, Model model, HttpServletRequest request,
                            HttpServletResponse response, HttpSession httpSession) {
        // 1.图形验证码判断
        String graphicCode = loginVo.getGraphicCode();
        if (!RandomValidateCodeUtil.checkVerify(graphicCode, httpSession)) {
            setErrorMsg(model, "验证码不正确!");
            return MB_LOGIN_FTL;
        }

        // 2.将vo转换dto调用会员登陆接口
        UserLoginInpDTO userLoginInpDTO = BeanConversionUtils.doToDto(loginVo, UserLoginInpDTO.class);
        // 登录类型
        userLoginInpDTO.setLoginType(Constants.MEMBER_LOGIN_TYPE_PC);
        // 获取浏览器信息
        String info = webBrowserInfo(request);
        userLoginInpDTO.setDeviceInfor(info);
        // 查询登录信息
        BaseResponse<JSONObject> login = memberLoginServiceFeign.login(userLoginInpDTO);
        if (!isSuccess(login)) {
            setErrorMsg(model, login.getMsg());
            return MB_LOGIN_FTL;
        }
        // 3.登陆成功之后如何处理 保持会话信息 将token存入到cookie 里面 首页读取cookietoken 查询用户信息返回到页面展示
        JSONObject data = login.getData();
        String token = data.getString("token");
        CookieUtils.setCookie(request, response, WebConstants.LOGIN_TOKEN_COOKIENAME, token);
        return REDIRECT_INDEX;
    }
}
