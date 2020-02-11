package com.dongl.member.service.impl;

import com.dongl.base.BaseApiService;
import com.dongl.base.BaseResponse;
import com.dongl.core.bean.BeanConversionUtils;
import com.dongl.constants.Constants;
import com.dongl.core.token.GenerateToken;
import com.dongl.core.type.TypeCastHelper;
import com.dongl.core.utils.MD5Util;
import com.dongl.member.feign.IWeiXinServiceFeign;
import com.dongl.member.feign.IWeiXinVerificationServiceFeign;
import com.dongl.member.input.dto.UserInpDTO;
import com.dongl.member.input.dto.UserLoginInpDTO;
import com.dongl.member.mapper.UserMapper;
import com.dongl.member.mapper.entity.UserDO;
import com.dongl.member.output.dto.UserOutDTO;
import com.dongl.member.service.IMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @Description: 实现 会员接口，
 * 需要把会员接口的jar 引入到模块
 * @author: YaoGuangXun
 * @date: 2020/2/1 15:06
 * @Version: 1.0
 */
@RestController
public class MemberServiceImpl extends BaseApiService implements IMemberService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GenerateToken generateToken;

    @Autowired
    private IWeiXinVerificationServiceFeign weiXinVerificationServiceFeign;

    @Transactional
    @Override
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


    @Override
    public BaseResponse mobileExist(String mobile) {

        // 1. 判断手机号是否为空
        if (StringUtils.isBlank(mobile)){
            return setResultError("手机号不能为空");
        }
        // 2. 根据手机号查询，判断手机号是否存在
        UserDO userDO = userMapper.existMobile(mobile);
        if (userDO == null){
            return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_203,"用户信息不存在");
        }

        UserOutDTO userOutDTO = BeanConversionUtils.doToDto(userDO,UserOutDTO.class);
        //3. 返回实例
        return setResultSuccess(userOutDTO);
    }

    @Override
    public BaseResponse<UserOutDTO> getInfo(String token) {
        // 1.参数验证
        if (StringUtils.isEmpty(token)) {
            return setResultError("token不能为空!");
        }
        // 2.根据 token 从redis中查询userId
        String redisValue = generateToken.getToken(token);
        if (StringUtils.isEmpty(redisValue)) {
            return setResultError("token已经失效或者不正确");
        }
        Long userId = TypeCastHelper.toLong(redisValue);
        // 3.根据userId查询用户信息
        UserDO userDo = userMapper.findByUserId(userId);
        if (userDo == null) {
            return setResultError("用户信息不存在!");
        }
        // 4.将Do转换为Dto
        UserOutDTO doToDto = BeanConversionUtils.doToDto(userDo, UserOutDTO.class);
        return setResultSuccess(doToDto);
    }


    @Override
    public BaseResponse<UserOutDTO> ssoLogin(@RequestBody UserLoginInpDTO userLoginInpDTO) {
        // 1.验证参数
        String mobile = userLoginInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = userLoginInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        // 判断登陆类型
        String loginType = userLoginInpDTO.getLoginType();
        if (StringUtils.isEmpty(loginType)) {
            return setResultError("登陆类型不能为空!");
        }
        // 目的是限制范围
        if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID) || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
                || loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
            return setResultError("登陆类型出现错误!");
        }

        // 设备信息
        String deviceInfor = userLoginInpDTO.getDeviceInfor();
        if (StringUtils.isEmpty(deviceInfor)) {
            return setResultError("设备信息不能为空!");
        }
        // 2.对登陆密码实现加密
        String newPassWord = MD5Util.MD5(password);
        // 3.使用手机号码+密码查询数据库 ，判断用户是否存在
        UserDO userDo = userMapper.login(mobile, newPassWord);
        if (userDo == null) {
            return setResultError("用户名称或者密码错误!");
        }
        return setResultSuccess(BeanConversionUtils.doToDto(userDo, UserOutDTO.class));
    }
}
