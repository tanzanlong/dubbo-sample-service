package com.tan.dubbo.demo.provider;

import com.tan.dubbo.demo.DemoService;

public class DemoServiceImpl implements DemoService {

    @Override
    public String queryAccountId(String name) {
        return "201805250001"+name;
    }

}
