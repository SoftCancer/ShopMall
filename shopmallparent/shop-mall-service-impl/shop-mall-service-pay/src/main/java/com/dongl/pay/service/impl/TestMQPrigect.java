package com.dongl.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dongl.pay.mq.producer.IntegralProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/20 21:17
 * @Version: 1.0
 */
@RestController
public class TestMQPrigect {

    @Autowired
    private IntegralProducer integralProducer;
/**
 * @Description:  积分生产者，测试用例
 * @Author: YaoGuangXun
 * @Date: 2020/2/21 0:36
 **/
    @GetMapping("/sent")
    public String sent(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("paymentId", System.currentTimeMillis());
        jsonObject.put("userId", 1);
        jsonObject.put("integral", 100);
        integralProducer.send(jsonObject);
        return "success";
    }
}
