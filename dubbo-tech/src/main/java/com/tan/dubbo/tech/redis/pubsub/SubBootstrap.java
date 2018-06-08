package com.tan.dubbo.tech.redis.pubsub;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.baibei.log4k8s.Log4k8s;
import com.restMvcFilter.MvcTraceFilter;

/**
 * Created by tan on 2017/3/9. clean deploy -Dmaven.test.skip=true
 */
@EnableAutoConfiguration
@ComponentScan("com.tan")
/*@EnableAspectJAutoProxy(proxyTargetClass=true)*/
@ImportResource({"classpath:config/spring-context.xml"})
public class SubBootstrap extends WebMvcConfigurerAdapter implements SchedulingConfigurer{

    static{
        Log4k8s.init("pub-service");
    }
    
    public static void main(String[] args) {
        SpringApplication.run(SubBootstrap.class);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }
    
    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(30);
    }

    @Bean
    public MvcTraceFilter traceFilterRegiesty() {     return new MvcTraceFilter(); }
}

