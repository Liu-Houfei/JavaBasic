package com.test.p4;

import org.junit.Test;

public class ThreadTest {

    @Test
    public void test1() {
        SubThread1 t1 = new SubThread1();
        System.out.println("currentThread：" + Thread.currentThread().getName());
        System.out.println("begin==" + t1.isAlive());
        t1.start();
        System.out.println("currentThread：" + Thread.currentThread().getName());
        System.out.println("end==" + t1.isAlive());

    }
}
