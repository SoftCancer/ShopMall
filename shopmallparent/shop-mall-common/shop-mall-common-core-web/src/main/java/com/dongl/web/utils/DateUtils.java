package com.dongl.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/22 18:54
 * @Version: 1.0
 */
public class DateUtils {

    //Date指定格式：yyyy-MM-dd HH:mm:ss:SSS
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * @Description: 获取格式化日期
     * @Author: YaoGuangXun
     * @Date: 2020/2/22 19:02
     **/
    public static Date getDateFormat() {
        //调用parse()方法时 注意 传入的格式必须符合simpleDateFormat对象的格式，即"yyyy-MM-dd HH:mm:ss:SSS" 否则会报错！！
        String dateStr = getDateStrFormat();
        Date dateFormat = null ;
        try {
            dateFormat = simpleDateFormat.parse(dateStr);
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
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }
}
