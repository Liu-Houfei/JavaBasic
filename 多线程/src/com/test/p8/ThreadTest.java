package com.test.p8;

import org.junit.Test;

public class ThreadTest {

    @Test
    public void test1() throws InterruptedException {
        SubThread1 t1 = new SubThread1();
        t1.setPriority(1);
        t1.start();
        SubThread2 t2 = new SubThread2();
        t2.setPriority(10);
        t2.start();
        Thread.sleep(5000);
    }
}
