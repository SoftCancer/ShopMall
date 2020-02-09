package com.dongl.member.controller;

import com.dongl.base.BaseResponse;
import com.dongl.core.bean.BeanConversionUtils;
import com.dongl.member.controller.registervo.RegisterVo;
import com.dongl.member.feign.IMemberRegisterServiceFeign;
import com.dongl.member.input.dto.UserInpDTO;
import com.dongl.web.base.BaseWebController;
import com.dongl.web.utils.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @Description: 注册控制类
 * @author: YaoGuangXun
 * @date: 2020/2/7 23:54
 * @Version: 1.0
 */
@Controller
public class RegisterController extends BaseWebController {
    /* 跳转到注册页面 */
    private static final String MB_REGISTER_FTL = "/member/register";

    /**
     * 跳转到登陆页面页面
     */
    private static final String MB_LOGIN_FTL = "member/login";

    @Autowired
    private IMemberRegisterServiceFeign memberRegisterServiceFeign;

    /**
     * 注册方法
     *
     * @Author: YaoGuangXun
     * @Date: 2020/2/7 23:59
     **/
    @GetMapping("/register")
    public String getRegister() {
        return MB_REGISTER_FTL;
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("registerVo") @Validated RegisterVo registerVo,
                               BindingResult bindingResult, HttpSession httpSession, Model model) {

        // 1. 参数验证
        if (bindingResult.hasErrors()) {
            // 2.获取参数错误
            String errotMsg = bindingResult.getFieldError().getDefaultMessage();
            setErrorMsg(model, errotMsg);
            return MB_REGISTER_FTL;
        }

        // 2. 验证验证码
        String graphicCode = registerVo.getGraphicCode();
        boolean bool = RandomValidateCodeUtil.checkVerify(graphicCode,httpSession);
        if (!bool){
            setErrorMsg(model,"验证码不正确！");
            return MB_REGISTER_FTL;
        }

        // 3. 调用会员接口注册用户信息到数据库
        UserInpDTO userInpDTO = BeanConversionUtils.doToDto(registerVo,UserInpDTO.class);
        String registCode = registerVo.getRegistCode();
        BaseResponse register = memberRegisterServiceFeign.registeredUser(userInpDTO,registCode);
        if (!isSuccess(register)){
            setErrorMsg(model,register.getMsg());
            return MB_REGISTER_FTL;
        }

        return MB_LOGIN_FTL;
    }


}
