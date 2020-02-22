package com.dongl.web.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/22 18:54
 * @Version: 1.0
 */
@Slf4j
public class DateUtils {

    //Date指定格式：yyyy-MM-dd HH:mm:ss:SSS
    private static final String format = "yyyy-MM-dd HH:mm:ss";

    //每一个线程
     private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    /**
     * SimpleDateFormat 非线程安全，再多线程使用时需解决安全问题。
     * @Description: 获取格式化日期
     * @Author: YaoGuangXun
     * @Date: 2020/2/22 19:02
     **/
    public static Date getDateFormat() {
        //
        String dateStr = getDateStrFormat();
        // 解决多线程访问时，报错。
        SimpleDateFormat sdf = null;
        sdf = threadLocal.get();
        if (sdf ==null){
            sdf = new SimpleDateFormat(format);
        }

        Date dateFormat = null ;
        try {
            dateFormat = sdf.parse(dateStr);
            log.info("日期工具类，生成格式化日期： {}", dateFormat);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dateFormat;
    }

    /**
     * @Description: 获取格式化日期字符串形式
     * @Author: YaoGuangXun
     * @Date: 2020/2/22 19:02
     **/
    public static String getDateStrFormat() {
        //创建一个date对象保存当前时间
        Date date = new Date();
        //format()方法将Date转换成指定格式的String
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String dateStr = simpleDateFormat.format(date);
        log.info("日期工具类，生成格式化日期： {}", dateStr);
        return dateStr;
    }

    public static void main(String[] args) {
        System.out.println(getDateFormat());
        System.out.println(getDateStrFormat());
    }
}
