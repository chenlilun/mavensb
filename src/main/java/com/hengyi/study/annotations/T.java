package com.hengyi.study.annotations;

import java.util.Arrays;

public class T {
    public static void main(String[] args) {
        Hint hint = Person.class.getAnnotation(Hint.class);
        System.out.println(hint);                   // null

        Hints hints1 = Person.class.getAnnotation(Hints.class);
        System.out.println(hints1.value());  // 2

        Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2[0].value());          // 2
    }
}
