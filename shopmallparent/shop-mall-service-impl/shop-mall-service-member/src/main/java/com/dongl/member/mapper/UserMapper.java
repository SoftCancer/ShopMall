package com.dongl.member.mapper;

import com.dongl.member.mapper.entity.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/5 22:59
 * @Version: 1.0
 */
public interface UserMapper {

    /**
     *  用户注册
     * @Date: 2020/2/6 23:12
     **/
    @Insert("INSERT INTO `meite_user` VALUES (null,#{mobile}, #{email}, #{password}, #{userName}, null, null, null, '1', null, null, null);")
    int register(UserDO userDO);

    /**
     *  根据手机号查询
     * @Date: 2020/2/6 23:12
     **/
    @Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE,EMAIL AS EMAIL,PASSWORD AS PASSWORD, USER_NAME AS USER_NAME ,SEX AS SEX ,AGE AS AGE ,CREATE_TIME AS CREATETIME,IS_AVALIBLE AS ISAVALIBLE,PIC_IMG AS PICIMG,QQ_OPENID AS QQOPENID,WX_OPENID AS WXOPENID " +
            " FROM meite_user WHERE MOBILE=#{mobile};")
    UserDO existMobile(@Param("mobile") String mobile);

    /**
     *  根据手机号和密码查询
     * @Date: 2020/2/6 23:12
     **/
    @Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE,EMAIL AS EMAIL,PASSWORD AS PASSWORD, USER_NAME AS USER_NAME ,SEX AS SEX ,AGE AS AGE ,CREATE_TIME AS CREATETIME,IS_AVALIBLE AS ISAVALIBLE,PIC_IMG AS PICIMG,QQ_OPENID AS QQOPENID,WX_OPENID AS WXOPENID "
            + "  FROM meite_user  WHERE MOBILE=#{0} and password=#{1};")
    UserDO login(@Param("mobile") String mobile, @Param("password") String password);

    /**
     *  根据用户id查询
     * @Date: 2020/2/6 23:12
     **/
    @Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE,EMAIL AS EMAIL,PASSWORD AS PASSWORD, USER_NAME AS USER_NAME ,SEX AS SEX ,AGE AS AGE ,CREATE_TIME AS CREATETIME,IS_AVALIBLE AS ISAVALIBLE,PIC_IMG AS PICIMG,QQ_OPENID AS QQOPENID,WX_OPENID AS WXOPENID"
            + " FROM meite_user WHERE user_Id=#{userId}")
    UserDO findByUserId(@Param("userId") Long userId);
}
