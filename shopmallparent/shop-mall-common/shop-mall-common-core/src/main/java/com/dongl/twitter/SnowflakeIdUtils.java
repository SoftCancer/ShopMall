package com.dongl.twitter;

/**
 * 使用雪花算法生成全局id
 * @Author: YaoGuangXun
 * @Date: 2020/2/18 12:41
 **/
public class SnowflakeIdUtils {
	private static SnowflakeIdWorker idWorker;
	static {
		// 使用静态代码块初始化 SnowflakeIdWorker
		idWorker = new SnowflakeIdWorker(1, 1);
	}

	public static String nextId() {
		return idWorker.nextId() + "";
	}

}