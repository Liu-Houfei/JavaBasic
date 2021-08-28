package com.test4.p9;

import org.junit.Test;

public class ThreadTest {
    @Test
    public void t1() {
        SubThread t1 = new SubThread();
        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //唤醒t1子线程
        t1.interrupt();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //定义一个常量作为锁对象
    private static final Object LOCK = new Object();

    static class SubThread extends Thread {
        @Override
        public void run() {
            synchronized (LOCK) {
                try {
                    System.out.println("开始等待");
                    LOCK.wait();
                    System.out.println("结束等待");
                } catch (InterruptedException e) {
                    System.out.println("wait等待被中断");
                }
            }
        }
    }
}


