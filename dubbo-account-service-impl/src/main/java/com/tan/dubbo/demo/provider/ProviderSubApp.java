package com.tan.dubbo.demo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderSubApp {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"config/spring-dubbo-demo-provider.xml"});
        context.start();
        // press any key to exit
        System.in.read();
    }
}
