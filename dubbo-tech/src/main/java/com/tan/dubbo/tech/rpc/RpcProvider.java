package com.tan.dubbo.tech.rpc;

public class RpcProvider {
    /** 
     * RpcProvider 
     *  
     */  
      
        public static void main(String[] args) throws Exception {  
            HelloService service = new HelloServiceImpl();  
            RpcFramework.export(service, 1234);  
        }  
}
