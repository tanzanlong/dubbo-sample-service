package com.tan.dubbo.tech.proxy.common;

import java.io.FileOutputStream;
import java.io.IOException;

import sun.misc.ProxyGenerator;

import com.tan.dubbo.tech.proxy.dynamic.jdk.JdkProxySample.BookApi;
import com.tan.dubbo.tech.proxy.dynamic.jdk.JdkProxySample.BookApiImpl;
public class ProxyUtils {
    /* 
     * 将根据类信息 动态生成的二进制字节码保存到硬盘中， 
     * 默认的是clazz目录下 
         * params :clazz 需要生成动态代理类的类 
         * proxyName : 为动态生成的代理类的名称 
         */  
    public static void generateClassFile(Class clazz,String proxyName)  
    {  
        //根据类信息和提供的代理类名称，生成字节码  
                byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());   
        String paths = clazz.getResource(".").getPath();  
        System.out.println(paths);  
        FileOutputStream out = null;    
          
        try {  
            //保留到硬盘中  
            out = new FileOutputStream(paths+proxyName+".class");    
            out.write(classFile);    
            out.flush();    
        } catch (Exception e) {    
            e.printStackTrace();    
        } finally {    
            try {    
                out.close();    
            } catch (IOException e) {    
                e.printStackTrace();    
            }    
        }    
    }  
    public static void main(String[] args) {
        BookApi book=new BookApiImpl();
        ProxyUtils.generateClassFile(book.getClass(), "BookApiImplProxy");
    }
    
    interface Book {
        public void name(String name);
    }

    static class JavaBook implements Book {
        public void name(String name) {
            System.out.println(name);
        }

    }
}
