package com.test.p7;

import org.junit.Test;

public class ThreadTest {
    @Test
    public void test1() throws InterruptedException {
        //开启一个子线程，计算累加
        SubThread1 t1 = new SubThread1();
        t1.start();

        //在main线程计算累加
        long begin = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < 10000000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("main用时：" + (end - begin));

        Thread.sleep(5000);
    }
}
