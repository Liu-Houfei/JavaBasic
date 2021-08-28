package com.test4.p8;

import org.junit.Test;

public class ThreadTest2 {
    @Test
    public void t1() {
        final Object obj = new Object();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("开始同步");
                    try {
                        System.out.println("进入阻塞");
                        obj.wait(5000); //5000毫秒后自动唤醒
                        System.out.println("结束阻塞");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("结束同步");
                }
            }
        });
        t.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //notify通知过早
    @Test
    public void test2() {
        final Object lock = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("t1开始同步");
                    try {
                        System.out.println("t1进入阻塞");
                        lock.wait();
                        System.out.println("t1结束阻塞");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t1结束同步");
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("t2开始同步");
                     lock.notify();
                    System.out.println("t2结束同步");
                }
            }
        });
        t2.start();
        t1.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
