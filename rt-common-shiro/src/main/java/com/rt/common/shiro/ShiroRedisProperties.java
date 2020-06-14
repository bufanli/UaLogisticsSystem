package com.rt.common.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Protocol;

import java.io.Serializable;

@Component
@ConfigurationProperties(prefix = "spring.redis")
public class ShiroRedisProperties implements Serializable {
    private static final long serialVersionUID = 1L;
    private String host;
    private int port;
    // timeout for jedis try to connect to redis server, not expire time! In milliseconds
    private int timeout;

    private String password;

    private int database;

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

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        if("".equals(password)){
            return null;
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getHostAndPort(){
        return this.host+":"+this.port;
    }
}
