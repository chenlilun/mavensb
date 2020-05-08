package com.hengyi.study.flatmap.optional;

import org.junit.Test;

import java.util.Optional;

public class TestOptional {
    @Test
    public void test(){
        Outer outer = new Outer();
        Nested nested = new Nested() ;
        Inner inner = new Inner() ;
        inner.foo = null ;
        nested.inner = inner ;
        outer.nested = nested ;
        if (outer != null && outer.nested != null && outer.nested.inner != null) {
            System.out.println(outer.nested.inner.foo);
        }
        //第二种方式
        Optional.of(new Outer()).flatMap(outer1 -> Optional.ofNullable(outer1.nested))
                .flatMap(a->Optional.ofNullable(a.inner))
                .flatMap(b->Optional.ofNullable(b.foo)).ifPresent(System.out::println);
    }
}
