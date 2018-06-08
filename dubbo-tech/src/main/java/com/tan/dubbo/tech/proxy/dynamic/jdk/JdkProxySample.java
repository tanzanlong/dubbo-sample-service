package com.tan.dubbo.tech.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxySample {
    
    public static void main(String[] args) {
        /**
         * 该设置用于输出jdk动态代理产生的类
         */
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        BookApi bookApi= JdkProxySample.createJdkDynamicProxy(new BookApiImpl());
        bookApi.sell("tzl");
    }
    
    private static BookApi createJdkDynamicProxy(final BookApi delegate) {
        BookApi jdkProxy =
                (BookApi) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                        new Class[] {BookApi.class}, new BookHandler(delegate));
        return jdkProxy;
    }

    private static class BookHandler implements InvocationHandler {
        final Object delegate;

        BookHandler(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
            // 添加代理逻辑<1>
            if (method.getName().equals("sell")) {
                System.out.print("...discount before...");
            }
           // return null;
             return method.invoke(delegate, objects);
        }


    }
    
   public  interface BookApi {
        public void sell(String name);
    }

    public static class BookApiImpl implements BookApi {
        public void sell(String name) {
            System.out.println("....target invoke sell method...");
        }
    }
}
