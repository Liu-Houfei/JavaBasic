package com.test.p9;

import org.junit.Test;

public class ThreadTest {
    @Test
    public void test1() throws InterruptedException {
        SubThread1 t1 = new SubThread1();
        t1.start();
        SubThread2 t2 = new SubThread2();
        t2.start();
        //当前线程是main线程
        for (int i = 0; i < 10; i++) {
            System.out.println("main===>" + i);
        }
        //中断子线程
        //仅仅是给子线程标记中断，子线程没有真正中断
        t1.interrupt();
        t2.interrupt();

        Thread.sleep(10);
    }
}
