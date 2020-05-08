package com.hengyi.study.proxy.dong;

import com.hengyi.study.proxy.AdminService;
import com.hengyi.study.proxy.AdminServiceImpl;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestProxy {
    @Test
    public  void  test(){
        AdminService target = new AdminServiceImpl() ;
        AdminServiceInvocation invocationHandler = new AdminServiceInvocation(target)  ;
        AdminServiceDynamicProxy proxy = new AdminServiceDynamicProxy(target,invocationHandler) ;
        AdminService personProxy = (AdminService) proxy.getPersonProxy(); // 得到代理对象
        System.out.println("代理对象：" + personProxy.getClass());
        Object obj = personProxy.find();
        System.out.println("find 返回对象：" + obj.getClass());
        System.out.println("----------------方式二------------------");
        personProxy = (AdminService) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),invocationHandler);
        System.out.println("二"+personProxy.getClass());
        System.out.println("二"+personProxy.find().getClass());
        System.out.println("***************************************");
        personProxy.update();
        personProxy.find();
        personProxy.find("你好啊");


    }
}
