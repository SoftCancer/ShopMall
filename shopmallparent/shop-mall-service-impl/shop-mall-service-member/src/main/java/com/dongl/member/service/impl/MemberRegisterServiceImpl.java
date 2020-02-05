package com.dongl.member.service.impl;

import com.dongl.core.base.BaseApiService;
import com.dongl.core.base.BaseResponse;
import com.dongl.core.utils.MD5Util;
import com.dongl.entity.UserEntity;
import com.dongl.member.mapper.UserMapper;
import com.dongl.member.service.IMemberRegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 注册接口
 * 测试需要启动，微信，会员，zuul 三个应用。
 * @author: YaoGuangXun
 * @date: 2020/2/5 22:51
 * @Version: 1.0
 */
@RestController
public class MemberRegisterServiceImpl extends BaseApiService implements IMemberRegisterService {

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private IWeiXinVerificationService weiXinVerificationService;

    @Transactional
    @Override
    public BaseResponse registeredUser(@RequestBody UserEntity userEntity, String registCode) {

        // 1. 判断参数是否为空
        String passward = userEntity.getPassword();
        if (StringUtils.isBlank(passward)){
            return setResultError("密码不能为空");
        }
        if (StringUtils.isBlank(userEntity.getUserName())){
            return setResultError("用户名不能为空");
        }
        if (StringUtils.isBlank(registCode)){
            return setResultError("注册码不能为空");
        }

        // 2. 判断注册码是否正确
//         BaseResponse resultVerification = weiXinVerificationService.verificationCode(userEntity.getMobile(),registCode);
//        if (!resultVerification.getCode().equals(Constants.HTTP_RES_CODE_200)){
//            return setResultError(resultVerification.getMsg());
//        }

        // 3.密码加密
        passward = MD5Util.MD5(passward);
        userEntity.setPassword(passward);
        // 4.保存注册数据
        Integer result = userMapper.register(userEntity);

        return result >0 ? setResultSuccess("账户注册成功!"):setResultError("账户注册失败!");

    }
}
