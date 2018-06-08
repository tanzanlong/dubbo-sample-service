package com.tan.dubbo.tech.proxy.dynamic.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;

import com.tan.dubbo.tech.proxy.dynamic.jdk.JdkProxySample.BookApi;

public class JavassistSample {
    private static BookApi createJavassistBytecodeDynamicProxy() throws Exception {
        ClassPool mPool = new ClassPool(true);
        CtClass mCtc = mPool.makeClass(BookApi.class.getName() + "JavaassistProxy");
        mCtc.addInterface(mPool.get(BookApi.class.getName()));
        mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
        mCtc.addMethod(CtNewMethod.make(
                "public void sell() { System.out.print(\"\") ; }", mCtc));
        Class<?> pc = mCtc.toClass();
        BookApi bytecodeProxy = (BookApi) pc.newInstance();
        return bytecodeProxy;
    }
    
    public static void main(String[] args) throws Exception {
        BookApi bookApi=JavassistSample.createJavassistBytecodeDynamicProxy();
        bookApi.sell("perl");
    }
}
