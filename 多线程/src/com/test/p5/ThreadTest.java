package com.test.p5;

import org.junit.Test;

public class ThreadTest {

    @Test
    public void test1() throws InterruptedException {
        SubThread1 t1 = new SubThread1();
        System.out.println("main_begin = " + System.currentTimeMillis());
        t1.start();
        Thread.sleep(5000);
        System.out.println("main_end = " + System.currentTimeMillis());
    }

    @Test
    public void test2() throws InterruptedException {
        SubThread1 t1 = new SubThread1();
        System.out.println("main_begin = " + System.currentTimeMillis());
        t1.run();
        Thread.sleep(5000);
        System.out.println("main_end = " + System.currentTimeMillis());
    }
}
