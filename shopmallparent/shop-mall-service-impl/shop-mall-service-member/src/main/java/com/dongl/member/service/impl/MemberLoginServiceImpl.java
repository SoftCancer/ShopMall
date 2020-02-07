package com.dongl.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dongl.base.BaseApiService;
import com.dongl.base.BaseResponse;
import com.dongl.constants.Constants;
import com.dongl.core.token.GenerateToken;
import com.dongl.core.utils.MD5Util;
import com.dongl.member.input.dto.UserLoginInpDTO;
import com.dongl.member.mapper.UserMapper;
import com.dongl.member.mapper.UserTokenMapper;
import com.dongl.member.mapper.entity.UserDO;
import com.dongl.member.mapper.entity.UserTokenDo;
import com.dongl.member.service.IMemberLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/6 23:56
 * @Version: 1.0
 */
@RestController
public class MemberLoginServiceImpl extends BaseApiService implements IMemberLoginService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GenerateToken generateToken;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO) {
        // 1.验证参数
        String mobile = userLoginInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = userLoginInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        String loginType = userLoginInpDTO.getLoginType();
        if (StringUtils.isEmpty(loginType)) {
            return setResultError("登陆类型不能为空!");
        }
        if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID) || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
                || loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
            return setResultError("登陆类型出现错误!");
        }

        // 设备信息
        String deviceInfor = userLoginInpDTO.getDeviceInfor();
        if (StringUtils.isEmpty(deviceInfor)) {
            return setResultError("设备信息不能为空!");
        }
        String newPassWord = MD5Util.MD5(password);
        // 2.用户名称与密码登陆
        UserDO userDo = userMapper.login(mobile, newPassWord);
        if (userDo == null) {
            return setResultError("用户名称与密码错误!");
        }
        // 3.查询之前是否有过登陆
        Long userId = userDo.getUserid();
        UserTokenDo userTokenDo = userTokenMapper.selectByUserIdAndLoginType(userId, loginType);
        if (userTokenDo != null) {
            // 4.清除之前的token
            String token = userTokenDo.getToken();
            Boolean removeToken = generateToken.removeToken(token);
            if (removeToken) {
                userTokenMapper.updateTokenAvailability(userId, loginType);
            }
        }
        // 5. 生成新的token
        String token = generateToken.createToken(Constants.MEMBER_TOKEN_KEYPREFIX, userId + "",
                Constants.MEMBRE_LOGIN_TOKEN_TIME);
        JSONObject tokenData = new JSONObject();
        tokenData.put("token", token);
        // 6.存入在数据库中
        UserTokenDo userToken = new UserTokenDo();
        userToken.setUserId(userId);
        userToken.setLoginType(userLoginInpDTO.getLoginType());
        userToken.setToken(token);
        userToken.setDeviceInfor(deviceInfor);
        userTokenMapper.insertUserToken(userToken);
        return setResultSuccess(userTokenDo);

    }
}
