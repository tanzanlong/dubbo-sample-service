package com.tan.dubbo.tech.redis.pubsub;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.baibei.log4k8s.Log4k8s;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SubBootstrap.class)
@WebAppConfiguration
public class PubServiceTest {
    static{
        Log4k8s.init("discrepancy-money-service");
    }
    @Autowired
    private PubService pubService;
    
    @Test
    public void testGetData4AssemblyMessage() {
        pubService.pub("DUBBO:CHANNEL:TEST", "test", "123456");
    }

}
