package com.dongl.member.service.impl;

import com.dongl.core.base.BaseApiService;
import com.dongl.core.base.BaseResponse;
import com.dongl.core.constants.Constants;
import com.dongl.entity.AppEntity;
import com.dongl.entity.UserEntity;
import com.dongl.member.feign.IWeiXinServiceFeign;
import com.dongl.member.mapper.UserMapper;
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

    @Override
    public BaseResponse mobileExist(String mobile) {
        // 1. 判断手机号是否为空
        if (StringUtils.isBlank(mobile)){
            return setResultError("手机号不能为空");
        }
        // 2. 根据手机号查询，判断手机号是否存在
        UserEntity userEntity = userMapper.existMobile(mobile);
        if (userEntity == null){
            return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_203,"用户信息不存在");
        }

        //3. 返回实例
        userEntity.setPassword(null);
        return setResultSuccess(userEntity);
    }
}
