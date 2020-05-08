package com.hengyi.study.annotations;

import java.util.Arrays;
import java.util.Optional;

@Hints({@Hint("hint1"), @Hint("hint2")})
public class Person {
  public   String name;
   public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}
