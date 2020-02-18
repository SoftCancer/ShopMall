package com.dongl.pay.service;

import com.dongl.base.BaseResponse;
import com.dongl.pay.out.dto.PayMentTransacDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Description: 服务接口
 * @Author: YaoGuangXun
 * @Date: 2020/2/17 23:46
 **/
public interface IPayMentTransacInfoService {
	@GetMapping("/tokenByPayMentTransac")
	public BaseResponse<PayMentTransacDTO> tokenByPayMentTransac(@RequestParam("token") String token);
}
