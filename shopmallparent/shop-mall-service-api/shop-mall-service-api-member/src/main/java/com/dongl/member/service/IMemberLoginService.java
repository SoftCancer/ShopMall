package com.dongl.member.service;

import com.alibaba.fastjson.JSONObject;
import com.dongl.base.BaseResponse;
import com.dongl.member.input.dto.UserLoginInpDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "用户登陆服务接口")
public interface IMemberLoginService {
	/**
	 * 用户登陆接口
	 * @return
	 */
	@PostMapping("/login")
	@ApiOperation(value = "会员用户登陆信息接口")
    BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO);

	/**
	 * 用户登陆接口
	 * @return
	 */
	@GetMapping("/exit")
	@ApiOperation(value = "会员用户退出登录")
    BaseResponse<JSONObject> exit(@RequestParam("token") String token);




}

