package com.tan.dubbo.tech.proxy.dynamic.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.tan.dubbo.tech.proxy.dynamic.jdk.JdkProxySample.BookApi;
import com.tan.dubbo.tech.proxy.dynamic.jdk.JdkProxySample.BookApiImpl;

public class CglibSample {

    
    public static void main(String[] args) { 
        /**
         * 该设置用于输出cglib动态代理产生的类
         */
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\class");  
        BookApiImpl progammer = new BookApiImpl();  
        LogMethodInterceptor logMethodInterceptor=new LogMethodInterceptor();
        //cglib 中加强器，用来创建动态代理  
        Enhancer enhancer = new Enhancer();    
                 //设置要创建动态代理的类  
        enhancer.setSuperclass(progammer.getClass());    
               // 设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦截  
                enhancer.setCallback(logMethodInterceptor);  
                BookApi proxy =(BookApi)enhancer.create();  
                proxy.sell("java");
    }  
    
    static class LogMethodInterceptor implements MethodInterceptor {  
        @Override  
        public Object intercept(Object obj, Method method, Object[] args,  
                MethodProxy proxy) throws Throwable {  
            System.out.println("**** I am a hacker,Let's see what the poor programmer is doing Now...");  
            proxy.invokeSuper(obj, args);  
            System.out.println("****  Oh,what a poor programmer.....");  
            return null;  
        }  
      
    }  

}
