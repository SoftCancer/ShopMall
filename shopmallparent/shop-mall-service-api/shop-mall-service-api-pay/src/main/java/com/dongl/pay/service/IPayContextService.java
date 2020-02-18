package com.dongl.pay.service;

import com.dongl.base.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description: 根据不同的渠道id(支付方式) 返回不同的支付提交表单
 * @Author: YaoGuangXun
 * @Date: 2020/2/17 23:47
 **/
public interface IPayContextService {
	@GetMapping("/toPayHtml")
	public BaseResponse<JSONObject> toPayHtml(@RequestParam("channelId") String channelId,
                                              @RequestParam("payToken") String payToken);

}
