package com.dongl.pay.service;


import com.dongl.pay.out.dto.PaymentChannelDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


public interface IPaymentChannelService {
	/**
	 * 查询所有支付渠道
	 * @return
	 */
	@GetMapping("/selectAll")
	public List<PaymentChannelDTO> selectAll();
}
