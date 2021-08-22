package com.test.p8;

public class SubThread1 extends Thread {
    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < 1000000000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("1用时：" + (end - begin));
    }
}
