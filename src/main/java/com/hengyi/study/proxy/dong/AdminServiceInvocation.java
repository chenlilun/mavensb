package com.hengyi.study.proxy.dong;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

public class AdminServiceInvocation implements InvocationHandler {

    private Object target;

    public AdminServiceInvocation(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Objects.nonNull(args)&&args.length>0){
            Arrays.stream(args).
                    filter(aa->{
                        return aa.getClass().equals(String.class) ;
                    }).forEach(new Consumer<Object>() {
                @Override
                public void accept(Object o) {
                    System.out.println(o+"ppppp");
                }
            });
        }


        String name = method.getName();
        boolean find = Objects.nonNull(name) && name.equals("find");
        if(find){
            System.out.println("只有find判断用户是否有权限进行操作");
        }

        Object obj = method.invoke(target,args);

        if(find){
            System.out.println("只有find记录用户执行操作的用户信息、更改内容和时间等");
        }

        return obj;
    }
}
