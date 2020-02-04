package com.dongl.zuul.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/4 15:11
 * @Version: 1.0
 */
public class FastJson {

    public static void main(String[] args) {
        String jsonString = "[{\"name\":\"app-mayikt-member\",\"location\":\"/app-mayikt-member/v2/api-docs\",\"version\":\"2.0\"},{\"name\":\"app-mayikt-weixin\",\"location\":\"/app-mayikt-weixin/v2/api-docs\",\"version\":\"2.0\"}]";
        JSONArray jsonArray = JSONArray.parseArray(jsonString);

        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            String name = jsonObject.getString("name");
            System.out.println(name);
        }
    }
}
