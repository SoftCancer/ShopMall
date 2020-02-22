package com.dongl.core.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @description: redis工具类
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@Component
public class RedisUtil {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 存放string类型
	 * 
	 * @param key
	 *            key
	 * @param data
	 *            数据
	 * @param timeout
	 *            超时间
	 */
	public void setString(String key, String data, Long timeout) {
        try {
            stringRedisTemplate.opsForValue().set(key, data);
            if (timeout != null) {
                stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * 存放string类型
	 * 
	 * @param key
	 *            key
	 * @param data
	 *            数据
	 */
	public void setString(String key, String data) {
		setString(key, data, null);
	}


    /**
     * 存放string类型,如果key 存在返回：false，不存在返回 :true
     * @param key
     *            key
     * @param value
     *            数据
     * @param timeout
     *            超时间
     */
    public Boolean setNx(String key, String value, Long timeout) {
        boolean bool = stringRedisTemplate.opsForValue().setIfAbsent(key, value);
        try {
            if (timeout != null) {
                stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }

    /**
     * 存放 List 类型数据
     * @Description:
     * @Author: YaoGuangXun
     * @Date: 2020/2/23 1:11
     **/
    public void setList(String key , List<String> list){
        stringRedisTemplate.opsForList().leftPushAll(key,list);
    }

	/**
	 * 根据key查询string类型
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		String value = stringRedisTemplate.opsForValue().get(key);
		return value;
	}

	/**
	 *  移除key 集合中最左侧元素并返回移除元素。
	 * @Author: YaoGuangXun
	 * @Date: 2020/2/23 1:44
	 **/
	public String getListString(String key){
        String value = stringRedisTemplate.opsForList().leftPop(key);
        return value;
    }


	/**
	 * 根据对应的key删除key
	 * 
	 * @param key
	 */
	public Boolean delKey(String key) {
		return stringRedisTemplate.delete(key);
	}


    /**
     * 开启Redis 事务
     */
    public void begin() {
        // 开启Redis 事务权限
        stringRedisTemplate.setEnableTransactionSupport(true);
        // 开启事务
        stringRedisTemplate.multi();

    }
    /**
     * 提交事务
     */
    public void exec() {
        // 成功提交事务
        stringRedisTemplate.exec();
    }

    /**
     * 回滚Redis 事务
     */
    public void discard() {
        stringRedisTemplate.discard();
    }
}
