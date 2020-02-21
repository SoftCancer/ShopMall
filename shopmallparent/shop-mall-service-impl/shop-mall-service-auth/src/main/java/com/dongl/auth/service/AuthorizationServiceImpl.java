package com.dongl.auth.service;

import com.alibaba.fastjson.JSONObject;
import com.dongl.auth.mapper.AppInfoMapper;
import com.dongl.auth.mapper.entity.MeiteAppInfo;
import com.dongl.auth.service.api.IAuthorizationService;
import com.dongl.auth.utils.Guid;
import com.dongl.base.BaseApiService;
import com.dongl.base.BaseResponse;
import com.dongl.core.token.GenerateToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/21 22:15
 * @Version: 1.0
 */
@RestController
public class AuthorizationServiceImpl extends BaseApiService implements IAuthorizationService {

    @Autowired
    private AppInfoMapper appInfoMapper;

    @Autowired
    private GenerateToken generateToken;

    /**
     * http://127.0.0.1:9500/applyAppInfo?appName=阿里巴巴
     *
     * @Author: YaoGuangXun
     * @Date: 2020/2/21 23:21
     **/
    @Override
    public BaseResponse<JSONObject> applyAppInfo(String appName) {
        /** 如何防止同一个账户多次生成 appId ？ **/
        // 1. 验证参数是否为空
        if (StringUtils.isEmpty(appName)) {
            return setResultError("机构名称不能为空！");
        }

        // 2. 生成appId 和 toDaoResult
        Guid guid = new Guid();
        String appId = guid.getAppId();
        String appScrect = guid.getAppScrect();
        // 3. 添加数据到数据库
        MeiteAppInfo meiteAppInfo = new MeiteAppInfo(appName, appId, appScrect);
        int appInfo = appInfoMapper.insertAppInfo(meiteAppInfo);
        if (!toDaoResult(appInfo)) {
            return setResultError("应用appId 申请失败！");
        }
        // 4. 封装返回参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appId", appId);
        jsonObject.put("appScrect", appScrect);
        jsonObject.put("appName", appName);
        return setResultSuccess(jsonObject);
    }

    /**
     * appId ：是不可变的。
     * appSecret ： 是可变的，主要防止密钥泄漏时，可以重新生成：appSecret，即可停止第三方登录。
     * 访问：http://127.0.0.1:9500/getAccessToken?appId=b50c47fb7e414be7bfcf1e78b56c3afa&appSecret=72AC3FE58255562886D00154C81D5343
     * 需要对 redis和mysql 进行事务处理。暂时没实现
     * @Description: 使用appId 和appSecret 获取AccessToken
     * @Author: YaoGuangXun
     * @Date: 2020/2/21 23:08
     **/
    @Override
    public BaseResponse<JSONObject> getAccessToken(String appId, String appSecret) {
        // 1. 判断参数是否为空
        if (StringUtils.isEmpty(appId)) {
            return setResultError("AppId 不能为空！");
        }
        if (StringUtils.isEmpty(appSecret)) {
            return setResultError("appSecret 不能为空！");
        }

        // 2. 根据 AppId,appSecret 查询是否存在
        MeiteAppInfo meiteAppInfo = appInfoMapper.selectByAppInfo(appId, appSecret);
        if (null == meiteAppInfo) {
            return setResultError("AppId 或者 appSecret 错误！");
        }


        // 3. 获取旧的accessToken ，判断是否为空，不为空从redis 中移除
        String old_accessToken = meiteAppInfo.getAccessToken();
        if (StringUtils.isNotBlank(old_accessToken)) {
            generateToken.removeToken(old_accessToken);
        }

        // 4. 新创建 AccessToken 并存放到 Redis 中。
        String dbAppId = meiteAppInfo.getAppId();
        String new_accessToken = generateToken.createToken("auth_", dbAppId);
        // 5. 新生成的 new_accessToken更新到数据库。
        meiteAppInfo.setAccessToken(new_accessToken);
        meiteAppInfo.setUpdatedTime(new Date());
        appInfoMapper.updateAppInfo(meiteAppInfo);

        // 5. 封装返回参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessToken", new_accessToken);
        return setResultSuccess(jsonObject);
    }

    @Override
    public BaseResponse<JSONObject> getAppInfo(String accessToken) {

        // 1. 判断参数是否为空
        if (StringUtils.isEmpty(accessToken)){
            return setResultError("accessToken 不能为空！");
        }

        // 2. 根据accessToken 从 Redis 中获取AppId
        String appId = generateToken.getToken(accessToken);
        if (StringUtils.isBlank(appId)){
            return setResultError("accessToken don't exist! ");
        }

        // 3.根据 appId 获取 认证信息
        MeiteAppInfo meiteAppInfo = appInfoMapper.findByAppInfo(appId);
        if (meiteAppInfo == null){
            return setResultError("accessToken is invalid ");
        }

        // 4.封装返回参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appName",meiteAppInfo.getAppName());
        jsonObject.put("appId",meiteAppInfo.getAppId());
        jsonObject.put("appSecret",meiteAppInfo.getAppSecret());
        jsonObject.put("accessToken",meiteAppInfo.getAccessToken());
        jsonObject.put("update",meiteAppInfo.getUpdatedTime());
        return setResultSuccess(jsonObject);
    }
}
