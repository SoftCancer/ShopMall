package com.dongl.utils;

import com.dongl.sign.SignUtil;

import java.util.HashMap;
import java.util.Map;


public class TestUtils {

	public static void main(String[] args) {
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("payAmount", "300222");
		sParaTemp.put("orderId", "2019010203501502");
		sParaTemp.put("userId", "644064");
		String reuslt = HttpClientUtils.doPost("http://127.0.0.1/api-pay/cratePayToken", SignUtil.sign(sParaTemp));
		System.out.println("reusltL:" + reuslt);

		//payAmount=300222&orderId=2019010203501502&userId=644064
        // fcf34d2407e9697a780b3399da799a0f
	}
}
