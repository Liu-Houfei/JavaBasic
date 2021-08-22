package com.test.p6;

import org.junit.Test;

public class ThreadTest {
    @Test
    public void test1() throws InterruptedException {
        System.out.println("current_thread:" + Thread.currentThread().getName());
        System.out.println("current_threadId:" + Thread.currentThread().getId());
        for (int i = 0; i < 5; i++) {
            new SubThread1().start();
        }
        Thread.sleep(5000);
    }
}
