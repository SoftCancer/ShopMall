package com.dongl.member.service;

import com.alibaba.fastjson.JSONObject;
import com.dongl.base.BaseResponse;
import com.dongl.member.input.dto.UserLoginInpDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "用户登陆服务接口")
public interface IMemberLoginService {
	/**
	 * 用户登陆接口
	 * @return
	 */
	@PostMapping("/login")
	@ApiOperation(value = "会员用户登陆信息接口")
    BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO);

}

