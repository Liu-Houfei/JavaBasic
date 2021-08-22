package com.test.p7;

public class SubThread1 extends Thread {
    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < 10000000; i++) {
            sum += i;
            //每次累加后，子线程放弃CPU资源
            Thread.yield();
        }
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - begin));
    }
}
