package com.tan.dubbo.tech.redis.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tan.dubbo.tech.proxy.common.JedisClient;
@Component
public class PubService {
    public static final String REGISTER = "register";

    public static final String UNREGISTER = "unregister";

    public static final String SUBSCRIBE = "subscribe";

    public static final String UNSUBSCRIBE = "unsubscribe";

    
    @Autowired
    JedisClient jedisClient;
    
    public void pub(String key,String value,String expire ){
        jedisClient.hset(key, value, expire);
        jedisClient.publishMsg(key, PubService.REGISTER);
    }
}
