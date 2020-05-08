package com.hengyi.study.cglib;

public class SayHello implements  WorkerInterface {
    public void say(String name) {
        System.out.println("您好，" + name);
    }

    @Override
    public void work() {
        System.out.println("work");
    }

    @Override
    public void work(String name) {
        System.out.println(name);
    }
}
