package ProviderSubApp;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.tan.dubbo.demo.DemoService;
import com.tan.dubbo.demo.provider.DemoServiceImpl;

public class ProviderApiSubApp {
 public static void main(String[] args) {
     ApplicationConfig application = new ApplicationConfig();
     application.setName("demo-provider");
     application.setVersion("1.0");
      
     RegistryConfig registryConfig = new RegistryConfig();
     registryConfig.setAddress("multicast://224.5.6.7:1234");
      
     ServiceConfig<DemoService> service = new ServiceConfig<>();
     service.setApplication(application);
     service.setRegistry(registryConfig);
     service.setInterface(DemoService.class);
     service.setRef(new DemoServiceImpl());
      
     service.export();
     try {
        Thread.sleep(80000);
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
}
