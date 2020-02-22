package com.dongl.core.token;

import com.dongl.core.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class GenerateToken {

    @Autowired
	private RedisUtil redisUtil;


    /**
	 * 生成令牌
	 * 
	 * @param keyPrefix
	 *            令牌key前缀
	 * @param redisValue
	 *            redis存放的值
	 * @return 返回token
	 */
	public String createToken(String keyPrefix, String redisValue) {
		return createToken(keyPrefix, redisValue, null);
	}

	/**
	 * 生成令牌
	 * 
	 * @param keyPrefix
	 *            令牌key前缀
	 * @param redisValue
	 *            redis存放的值
	 * @param time
	 *            有效期
	 * @return 返回token
	 */
	public String createToken(String keyPrefix, String redisValue, Long time) {
		if (StringUtils.isEmpty(redisValue)) {
			new Exception("redisValue Not nul");
		}
		String token = keyPrefix + UUID.randomUUID().toString().replace("-", "");
		redisUtil.setString(token, redisValue, time);
		return token;
	}


	/**
	 * 根据token获取redis中的value值
	 * 
	 * @param token
	 * @return
	 */
	public String getToken(String token) {
		if (StringUtils.isEmpty(token)) {
			return null;
		}
		String value = redisUtil.getString(token);
		return value;
	}

	/**
	 * @Description: 向Redis中存放list 类型数据
	 * @Author: YaoGuangXun
	 * @Date: 2020/2/23 1:12
	 **/
    public void setListToken(String keyPrefix, String redisKey, Long tokenQuantity) {
        List<String> listToken = createListToken(keyPrefix,tokenQuantity);
        redisUtil.setList(redisKey,listToken);
    }


    /**
     * @Description: 生成指定数量的 token 并放入list中。
     * @Author: YaoGuangXun
     * @Date: 2020/2/23 1:12
     **/
    public List<String> createListToken(String keyPrefix , Long tokenQuantity){
        List<String> listToken = new ArrayList<>();
        for (int i = 0;i < tokenQuantity; i++){
            String token = keyPrefix + UUID.randomUUID().toString().replace("-","");
            listToken.add(token);
        }
        return listToken;
    }

    public String getListKeyToken(String key) {
        String value = redisUtil.getListString(key);
        return value;
    }


    /**
	 * 移除token
	 * 
	 * @param token
	 * @return
	 */
	public Boolean removeToken(String token) {
		if (StringUtils.isEmpty(token)) {
			return null;
		}
		return redisUtil.delKey(token);
	}

}