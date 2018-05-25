package com.tan.accountservice;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tan.dubbo.demo.DemoService;

public class ConsumerApp {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"config/spring-dubbo-demo-consumer.xml"});
        context.start();
        // obtain proxy object for remote invocation
        DemoService demoService = (DemoService) context.getBean("demoService");
        // execute remote invocation
        String hello = demoService.queryAccountId("tzl");
        // show the result
        System.out.println(hello);
    }
}
