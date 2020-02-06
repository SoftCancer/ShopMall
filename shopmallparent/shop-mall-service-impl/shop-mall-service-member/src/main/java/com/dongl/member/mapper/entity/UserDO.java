package com.dongl.member.mapper.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/1 2:09
 * @Version: 1.0
 */
@Data
public class UserDO {
    /**
     * userid
     */
    private Long userid;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名称
     */
    private String user_name;
    /**
     * 性别 0 男 1女
     */
    private char sex;
    /**
     * 年龄
     */
    private Long age;
    /**
     * 注册时间
     */
    private Date createTime;
    /**
     * 修改时间
     *
     */
    private Date updateTime;
    /**
     * 账号是否可以用 1 正常 0冻结
     */
    private char is_avalible;
    /**
     * 用户头像
     */
    private String pic_img;
    /**
     * 用户关联 QQ 开放ID
     */
    private Date qq_openid;
    /**
     * 用户关联 微信 开放ID
     */
    private Date WX_OPENID;
}
