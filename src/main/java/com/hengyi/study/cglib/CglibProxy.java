package com.hengyi.study.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {


    /**
     * 生成CGLIB代理对象
     * @param cls -Class类 需要被代理的真实对象
     * @return
     */
    public Object getProxy(Class cls) {
        //1.CGLIB enhancer增强类对象
        Enhancer en = new Enhancer();
        //2.设置增强类型
        en.setSuperclass(cls);
        //3.定义代理逻辑对象为当前对象，要求当前对象实现 MethodInterceptor 接口
        en.setCallback(this);
        //生成代理对象并返回
        Object proxy = en.create();
        return proxy;

    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用代理对象之前的逻辑~");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("调用代理对象之后的逻辑~");
        String name = method.getName();
        if(name.equals("handlePost")&&objects.length>0){
            System.out.println("handlePost");

        }
        return result;
    }
}
