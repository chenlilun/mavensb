package com.hengyi.study.proxy;

import org.junit.Test;

public class JingProxyTest {
    @Test
    public void test(){
        AdminService adminService = new AdminServiceImpl();
        AdminServiceProxy adminServiceProxy = new AdminServiceProxy(adminService);
        adminServiceProxy.find();
        adminServiceProxy.update();
    }
}
