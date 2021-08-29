package com.test4.p12;

import org.junit.Test;

public class ThreadLocalTest {

    static ThreadLocal threadLocal = new ThreadLocal();

    @Test
    public void test1() {
        SubThread t1 = new SubThread();
        SubThread t2 = new SubThread();
        t1.start();
        t2.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class SubThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                threadLocal.set(Thread.currentThread().getName() + "-" + i);
                System.out.println(threadLocal.get());
            }
        }
    }
}
