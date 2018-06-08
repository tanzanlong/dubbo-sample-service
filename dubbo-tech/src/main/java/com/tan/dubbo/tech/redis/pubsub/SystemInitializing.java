package com.tan.dubbo.tech.redis.pubsub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tan.dubbo.tech.proxy.common.JedisClient;
import com.tan.dubbo.tech.redis.pubsub.SubService.Notifier;

//@Slf4j
@Component
public class SystemInitializing implements InitializingBean {

	static Logger logger = LoggerFactory.getLogger(SystemInitializing.class);
	
    @Autowired
    JedisClient jedisClient;
	
    public void afterPropertiesSet() throws Exception {
        doInit();
    }

    private void doInit() {
        String[] channelArray = new String[]{"DUBBO:CHANNEL:TEST"};
        Notifier notifier=new Notifier(jedisClient,channelArray);
        notifier.start();
    }

}
