package com.dongl.spike.producer;

import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 *  秒杀生产者，把秒杀成功的用户信息发送到RabbitMQ 中。
 * 在正式的秒杀项目中，生产者和消费者应分为两个项目分别部署，
 * 以防止任何一个宕机，而互相影响。
 * @Author: YaoGuangXun
 * @Date: 2020/2/23 0:34
 **/
@Component
@Slf4j
public class SpikeCommodityProducer implements RabbitTemplate.ConfirmCallback {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Transactional
	public void send(JSONObject jsonObject) {

		String jsonString = jsonObject.toJSONString();
		System.out.println("jsonString:" + jsonString);
		String messAgeId = UUID.randomUUID().toString().replace("-", "");
		// 封装消息
		Message message = MessageBuilder.withBody(jsonString.getBytes())
				.setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8").setMessageId(messAgeId)
				.build();
		// 构建回调返回的数据（消息id）
		this.rabbitTemplate.setMandatory(true);
		this.rabbitTemplate.setConfirmCallback(this);
		CorrelationData correlationData = new CorrelationData(jsonString);
		rabbitTemplate.convertAndSend("modify_exchange_name", "modifyRoutingKey", message, correlationData);

	}

	// 生产消息确认机制 生产者往服务器端发送消息的时候，采用应答机制
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		String jsonString = correlationData.getId();
		System.out.println("消息id:" + correlationData.getId());
		if (ack) {
			log.info(">>>使用MQ消息确认机制确保消息一定要投递到MQ中成功");
			return;
		}
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		// 生产者消息投递失败的话，采用递归重试机制
		send(jsonObject);
		log.info(">>>使用MQ消息确认机制投递到MQ中失败");
	}
}
