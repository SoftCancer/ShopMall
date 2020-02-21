package com.dongl.zuul.common.listener;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description: 阿波罗分布式配置中心方法监听, 放开注释可开启Apollo 配置监听
 * 当阿波罗变化时都会执行该方法。
 * @author: YaoGuangXun
 * @date: 2020/2/4 16:10
 * @Version: 1.0

@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {

    @ApolloConfig
    private Config config;

    @Override
    public void run(String... args) throws Exception {
        log.info("### 每次启动应用时，执行一次！ ####");
        config.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent configChangeEvent) {
                log.info("###  阿波罗分布式配置中心监听，Apollo 配置中心发生变化 ###" + configChangeEvent.changedKeys().toString());
            }
        });
    }
}
 */