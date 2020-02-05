package com.dongl.weixin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dongl.core.base.BaseApiService;
import com.dongl.core.base.BaseResponse;
import com.dongl.core.constants.Constants;
import com.dongl.core.utils.RedisUtil;
import com.dongl.core.utils.RegexUtils;
import com.dongl.weixin.service.IWeiXinVerificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/5 16:57
 * @Version: 1.0
 */
@RestController
public class WeiXinVerificationServiceImpl extends BaseApiService implements IWeiXinVerificationService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public BaseResponse<JSONObject> verificationCode(String phone, String weixinCode) {

        // 1. 判断参数是否为空
        if (StringUtils.isBlank(phone)){
            return setResultError("手机号不能为空！");
        }
        if (StringUtils.isBlank(weixinCode)){
            return setResultError("注册码不能为空！");
        }

        // 2.从Redis 中获取数据，判断是否为空
        String redisKey = Constants.WEIXINCODE_KEY+"_"+phone ;
        String codeValue = redisUtil.getString(redisKey);
        if (StringUtils.isBlank(codeValue)){
            return setResultError("注册码已过期！");
        }
        // 3. 判断参数和Redis中存储的是否相等
        if (!weixinCode.equals(codeValue)){
            return setResultError("注册码不正确！");
        }

        redisUtil.delKey(redisKey);
        // 4. 成功移除 参数
        return setResultSuccess();
    }
}
