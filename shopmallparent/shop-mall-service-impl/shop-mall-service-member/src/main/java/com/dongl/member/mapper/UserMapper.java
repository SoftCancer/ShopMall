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

    @Insert("INSERT INTO `meite_user` VALUES (null,#{mobile}, #{email}, #{password}, #{userName}, null, null, null, '1', null, null, null);")
    int register(UserDO userDO);

    @Select("SELECT * FROM meite_user WHERE MOBILE=#{mobile};")
    UserDO existMobile(@Param("mobile") String mobile);
}
