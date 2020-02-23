package com.dongl.spike.consumer;

import java.io.IOException;
import java.util.Map;

import com.dongl.spike.service.mapper.IOrderMapper;
import com.dongl.spike.service.mapper.ISeckillMapper;
import com.dongl.spike.service.mapper.entity.OrderEntity;
import com.dongl.spike.service.mapper.entity.SeckillEntity;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

/**
 * 在正式的秒杀项目中，生产者和消费者应分为两个项目分别部署，
 * 以防止任何一个宕机，而互相影响。
 * @Description: 库存消费者
 * @Author: YaoGuangXun
 * @Date: 2020/2/23 13:14
 **/
@Component
@Slf4j
public class StockConsumer {
	@Autowired
	private ISeckillMapper seckillMapper;
	@Autowired
	private IOrderMapper orderMapper;

	@RabbitListener(queues = "modify_inventory_queue")
	@Transactional
	public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
		String messageId = message.getMessageProperties().getMessageId();
		String msg = new String(message.getBody(), "UTF-8");
		log.info(">>>messageId:{},msg:{}", messageId, msg);
		JSONObject jsonObject = JSONObject.parseObject(msg);
		// 1.获取秒杀id
		Long seckillId = jsonObject.getLong("seckillId");
		SeckillEntity seckillEntity = seckillMapper.findBySeckillId(seckillId);
		if (seckillEntity == null) {
			log.warn("seckillId:{},商品信息不存在!", seckillId);
			return;
		}
		Long version = seckillEntity.getVersion();
		int inventoryDeduction = seckillMapper.inventoryDeductionTwo(seckillId, version);
		if (!toDaoResult(inventoryDeduction)) {
			log.info(">>>seckillId:{}修改库存失败>>>>inventoryDeduction返回为{} 秒杀失败！", seckillId, inventoryDeduction);
			return;
		}
		// 2.添加秒杀订单
		OrderEntity orderEntity = new OrderEntity();
		String phone = jsonObject.getString("phone");
		orderEntity.setUserPhone(phone);
		orderEntity.setSeckillId(seckillId);
		orderEntity.setState(1L);
		int insertOrder = orderMapper.insertOrder(orderEntity);
		if (!toDaoResult(insertOrder)) {
			return;
		}
		log.info(">>>已成功卖出商品  seckillId:{}>>>> inventoryDeduction返回为{} 秒杀成功", seckillId, inventoryDeduction);
	}

	// 调用数据库层判断
	public Boolean toDaoResult(int result) {
		return result > 0 ? true : false;
	}

}
