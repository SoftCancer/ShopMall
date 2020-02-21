package com.dongl.auth.service.api;

import com.dongl.base.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

/**
 *  用户授权接口,主要处理第三方平台调用本平台的接口保证平台不受攻击
 * @Author: YaoGuangXun
 * @Date: 2020/2/21 22:08
 **/
public interface IAuthorizationService {
	/**
	 * 机构申请 获取appid 和appsecret
	 * 
	 * @return
	 */
	@GetMapping("/applyAppInfo")
	public BaseResponse<JSONObject> applyAppInfo(@RequestParam("appName") String appName);

	/*
	 * 根据appid 和appsecret密钥 获取AccessToken
	 */
	@GetMapping("/getAccessToken")
	public BaseResponse<JSONObject> getAccessToken(@RequestParam("appId") String appId,
                                                   @RequestParam("appSecret") String appSecret);

	/*
	 * 验证Token是否失效
	 */
	@GetMapping("/getAppInfo")
	public BaseResponse<JSONObject> getAppInfo(@RequestParam("accessToken") String accessToken);

}
