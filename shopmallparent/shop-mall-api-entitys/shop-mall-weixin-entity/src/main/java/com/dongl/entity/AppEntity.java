package com.dongl.entity;

import lombok.Data;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/1 2:09
 * @Version: 1.0
 */
@Data
public class AppEntity {

    private String appId;

    private String appName;

    public AppEntity() {
    }

    public AppEntity(String appId, String appName) {
        this.appId = appId;
        this.appName = appName;
    }
}
