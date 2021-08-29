package com.test4.p12;

import org.junit.Test;

import java.util.Date;

public class ThreadLocalTest3 {
    //ThreadLocal初始化
    static class MyThreadLocal extends ThreadLocal {
        @Override
        protected Object initialValue() {
            return new Date();
        }
    }

    @Test
    public void test1() {
        MyThreadLocal myThreadLocal = new MyThreadLocal();
        System.out.println(myThreadLocal.get());
        ThreadLocal threadLocal = new ThreadLocal();
        System.out.println(threadLocal.get());
    }


    static MyThreadLocal myThreadLocal = new MyThreadLocal();

    static class SubThread extends Thread {
        @Override
        public void run() {
            if (myThreadLocal.get() == null) {
                myThreadLocal.set(new Date());
            }
            System.out.println(Thread.currentThread().getName() + "---" + myThreadLocal.get());
        }
    }

    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            new SubThread().start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
