package com.dongl.pay.factory;

import com.dongl.pay.strategy.IPayStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 初始化不同的策略行为，把各支付渠道放入工厂中
 * @author: YaoGuangXun
 * @date: 2020/2/18 15:01
 * @Version: 1.0
 */
public class StrategyFactory {
    /** 把实例bean 放入map 中，减少每次调用时进行创建 */
    private static Map<String, IPayStrategy> strategyMap = new ConcurrentHashMap<>();

    public static IPayStrategy getPayStrategy(String classAddres) {
        if (StringUtils.isEmpty(classAddres)) {
            return null;
        }
        try {
            IPayStrategy payStrategyBean = strategyMap.get(classAddres);
            if (null != payStrategyBean) {
                return payStrategyBean;
            }
            // 通过反射 获取实例
            Class<?> forName = Class.forName(classAddres);
            IPayStrategy payStrategy = (IPayStrategy) forName.newInstance();
            strategyMap.put(classAddres,payStrategy);
            return payStrategy;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
