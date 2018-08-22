package com.xfmeet.lock.redis;


import com.xfmeet.lock.redis.config.RedisSetting;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author meet
 */
@ComponentScan
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RedisLockConfig {

    @Autowired
    private RedisSetting redisSetting;

    @Bean
    @ConditionalOnMissingBean
    RedissonClient redisSingle() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress("redis://" + redisSetting.getHost() + ":" + redisSetting.getPort());
        if (StringUtils.isNotBlank(redisSetting.getPassword())) {
            serverConfig.setPassword(redisSetting.getPassword());
        }
        return Redisson.create(config);
    }
}
