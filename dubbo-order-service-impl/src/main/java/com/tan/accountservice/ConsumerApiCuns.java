package com.tan.accountservice;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.tan.dubbo.demo.DemoService;

public class ConsumerApiCuns {
 public static void main(String[] args) {
     ApplicationConfig application = new ApplicationConfig();
     application.setName("demo-consumer");
     application.setVersion("1.0");
      
     RegistryConfig registryConfig = new RegistryConfig();
     registryConfig.setAddress("multicast://224.5.6.7:1234");
      
     ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
     reference.setApplication(application);
     reference.setRegistry(registryConfig);
     reference.setInterface(DemoService.class);
      
     DemoService greetingsService = reference.get();
     String hiMessage = greetingsService.queryAccountId("baeldung");
     System.out.println(hiMessage);
}
}
