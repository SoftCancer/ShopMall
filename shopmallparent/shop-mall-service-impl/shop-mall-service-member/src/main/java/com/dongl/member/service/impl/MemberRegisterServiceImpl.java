package com.dongl.member.service.impl;

import com.dongl.base.BaseApiService;
import com.dongl.base.BaseResponse;
import com.dongl.core.bean.BeanConversionUtils;
import com.dongl.constants.Constants;
import com.dongl.core.utils.MD5Util;
import com.dongl.member.feign.IWeiXinVerificationServiceFeign;
import com.dongl.member.input.dto.UserInpDTO;
import com.dongl.member.mapper.UserMapper;
import com.dongl.member.mapper.entity.UserDO;
import com.dongl.member.service.IMemberRegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @Description: 注册接口 废弃不再使用 转移到 IMemberService 接口中。
 * 测试需要启动，微信，会员，zuul 三个应用。
 * @author: YaoGuangXun
 * @date: 2020/2/5 22:51
 * @Version: 1.0
 */
@RestController
public class MemberRegisterServiceImpl extends BaseApiService  {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IWeiXinVerificationServiceFeign weiXinVerificationServiceFeign;

    @Transactional
    public BaseResponse registeredUser(@RequestBody UserInpDTO userInpDTO, String registCode) {

        // 1. 判断参数是否为空
        String passward = userInpDTO.getPassword();
        if (StringUtils.isBlank(passward)){
            return setResultError("密码不能为空");
        }
        int i = new Random().nextInt();
        userInpDTO.setUserName("yaom" + i);
        if (StringUtils.isBlank(userInpDTO.getUserName())){
            return setResultError("用户名不能为空");
        }
        if (StringUtils.isBlank(registCode)){
            return setResultError("注册码不能为空");
        }

        // 2. 判断注册码是否正确
         BaseResponse resultVerification = weiXinVerificationServiceFeign.verificationCode(userInpDTO.getMobile(),registCode);
        if (!resultVerification.getCode().equals(Constants.HTTP_RES_CODE_200)){
            return setResultError(resultVerification.getMsg());
        }

        // 3.密码加密
        passward = MD5Util.MD5(passward);
        userInpDTO.setPassword(passward);
        // 4.保存注册数据
        UserDO userDO = BeanConversionUtils.dtoToDo(userInpDTO,UserDO.class);
        Integer result = userMapper.register(userDO);

        return result >0 ? setResultSuccess("账户注册成功!"):setResultError("账户注册失败!");

    }
}
