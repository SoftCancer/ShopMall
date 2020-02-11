package com.dongl.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @Description: JSON 转化工具
 * @author: YaoGuangXun
 * @date: 2020/2/11 20:26
 * @Version: 1.0
 */
public class JsonUtils {

    /**
     *  把Obj 转化为 JSONObject
     * @Author: YaoGuangXun
     * @Date: 2020/2/11 20:34
     **/
    public static JSONObject objToJSONObject(Object object) {
        String objString = JSONObject.toJSONString(object);
        JSONObject jsonObject = JSON.parseObject(objString);
        return jsonObject;
    }
}
