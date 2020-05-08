package com.hengyi.study.proxy;

public class AdminServiceImpl implements AdminService {
    public void update() {
        System.out.println("修改管理系统数据");
    }

    public Object find() {
        System.out.println("查看管理系统数据");
        return new Object();
    }

    @Override
    public Object find(String aa) {
        System.out.println("新增带参数的find头");
        return aa;
    }
}
