package com.hengyi.study.cglib;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestCglib {
    @Test
    public void  test(){
        CglibProxy cglibProxy = new CglibProxy() ;
        SayHello proxy = (SayHello) cglibProxy.getProxy(SayHello.class);
        proxy.say("你好啊");
        proxy.work();
        proxy.work("hahha");
        proxy.work("dadada");
    }
    @Test
    public void  tslambla(){
        List<String> strs = new ArrayList<>() ;
        strs.add("aa") ;
        strs.add("cc") ;
        strs.add("tta") ;
        strs.add("uu") ;
        strs.add("op") ;
        List<String> collect = strs.stream().filter(s -> s.length() == 2).sorted((a, b) -> a.compareTo(b)).filter(Objects::nonNull).map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s + "aaaa";
            }
        }).collect(Collectors.toList());
        System.out.println("collect"+collect);
        System.out.println(strs);
        Collections.sort(strs, (a,b)->{
            return a.compareTo(b) ;
        });
        System.out.println(strs);

        Function<Double, String> toInteger = String::valueOf;
        Function<Double, String> backToString = toInteger
                .andThen(String::valueOf);

        backToString.apply(1.2);     // "123"



    }
}
