package com.kunlun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.logging.log4j2.SpringBootConfigurationFactory;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.eureka.server.EurekaServerConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.SpringModelMBean;

import java.util.HashMap;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2018-01-08 17:47
 */
@Configuration
@RefreshScope
public class EurekaConfig {

    @Value("${eureka.client.service-url.defaultZone}")
    private String clientUrl;
    @Value("${eureka.client.fetch-registry}")
    private boolean fetchRegistry;
    @Value("${eureka.client.register-with-eureka}")
    private boolean registerWithEureka;
    @Value("${eureka.server.enable-self-preservation}")
    private boolean enableSelfPreservation;
    @Value("${eureka.server.eviction-interval-timer-in-ms}")
    private long evictionIntervalTimerInMs;

    @Bean
    EurekaClientConfigBean eurekaClientConfigBean() {
        EurekaClientConfigBean eurekaClientConfigBean = new EurekaClientConfigBean();
        eurekaClientConfigBean.setFetchRegistry(fetchRegistry);
        eurekaClientConfigBean.setRegisterWithEureka(registerWithEureka);
        HashMap<String, String> clientMap = new HashMap<>(1);
        clientMap.put("defaultZone", clientUrl);
        eurekaClientConfigBean.setServiceUrl(clientMap);
        return eurekaClientConfigBean;
    }

    @Bean
    EurekaServerConfigBean eurekaServerConfigBean() {
        EurekaServerConfigBean bean = new EurekaServerConfigBean();
        bean.setEnableSelfPreservation(enableSelfPreservation);
        bean.setEvictionIntervalTimerInMs(evictionIntervalTimerInMs);
        return bean;
    }


}
