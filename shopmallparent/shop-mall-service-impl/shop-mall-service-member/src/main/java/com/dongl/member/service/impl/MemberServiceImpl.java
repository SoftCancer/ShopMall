package com.dongl.member.service.impl;

import com.dongl.base.BaseApiService;
import com.dongl.base.BaseResponse;
import com.dongl.core.bean.BeanConversionUtils;
import com.dongl.constants.Constants;
import com.dongl.core.token.GenerateToken;
import com.dongl.core.type.TypeCastHelper;
import com.dongl.member.feign.IWeiXinServiceFeign;
import com.dongl.member.mapper.UserMapper;
import com.dongl.member.mapper.entity.UserDO;
import com.dongl.member.output.dto.UserOutDTO;
import com.dongl.member.service.IMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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

//    @Autowired
//    private IWeiXinServiceFeign weiXinServiceFeign;


    // SpringCloud 的服务通讯 ：rest，feign
//    @Override
//    public BaseResponse memberToWeiXin() {
//        return weiXinServiceFeign.getApp();
//    }


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

}
