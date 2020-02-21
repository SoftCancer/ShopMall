package com.dongl.auth.mapper;

import com.dongl.auth.mapper.entity.MeiteAppInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface AppInfoMapper {

	@Insert("INSERT INTO `meite_app_info` VALUES (null,#{appName}, #{appId}, #{appSecret}, '0', null, null, null, null, null);")
	public int insertAppInfo(MeiteAppInfo meiteAppInfo);

	@Select("SELECT ID AS ID ,app_id as appId, app_name AS appName ,app_secret as appSecret ,access_token as accessToken FROM meite_app_info where app_id=#{appId} and app_secret=#{appSecret}; ")
	public MeiteAppInfo selectByAppInfo(@Param("appId") String appId, @Param("appSecret") String appSecret);

	@Select("SELECT ID AS ID ,app_id as appId, app_name AS appName ,app_secret as appSecret  ,access_token as accessToken ,updated_time as updatedTime FROM meite_app_info where app_id=#{appId}  ")
	public MeiteAppInfo findByAppInfo(@Param("appId") String appId);

    @Update("UPDATE  `meite_app_info` SET access_token = #{accessToken} ,updated_by = #{updatedBy},updated_time = #{updatedTime, jdbcType=TIMESTAMP} where app_id=#{appId} and app_secret=#{appSecret};")
    public int updateAppInfo(MeiteAppInfo meiteAppInfo);
}
