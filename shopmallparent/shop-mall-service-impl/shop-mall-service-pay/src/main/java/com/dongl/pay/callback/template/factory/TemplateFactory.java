package com.dongl.pay.callback.template.factory;


import com.dongl.core.utils.SpringContextUtil;
import com.dongl.pay.callback.template.AbstractPayCallbackTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 获取具体实现的模版工厂方案
 * 
 * 
 * @description:
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
public class TemplateFactory {

	public static AbstractPayCallbackTemplate getPayCallbackTemplate(String beanId) {
		return (AbstractPayCallbackTemplate) SpringContextUtil.getBean(beanId);
	}

	public static ThreadPoolTaskExecutor getThreadPoolTaskExecutor(String beanId) {
		return (ThreadPoolTaskExecutor) SpringContextUtil.getBean(beanId);
	}

}
