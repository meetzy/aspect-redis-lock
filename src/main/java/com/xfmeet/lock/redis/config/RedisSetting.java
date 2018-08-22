package com.xfmeet.lock.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xp_zy
 * @date 2018/8/22 16:02
 * @company codingApi
 * @description
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisSetting {
    private String host;

    private int port;

    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
