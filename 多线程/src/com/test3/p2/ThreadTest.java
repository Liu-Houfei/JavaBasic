package com.test3.p2;

import org.junit.Test;

public class ThreadTest {

    @Test
    public void test1() {
        ThreadTest obj = new ThreadTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.doLongTimeTask();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.doLongTimeTask();
            }
        }).start();
    }

    //使用同步代码块，锁的粒度细，执行效率高
    public void doLongTimeTask() {
        try {
            System.out.println("Task begin");
            Thread.sleep(3000);
            synchronized (this) {   //同步代码块
                System.out.println("开始同步");
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "--->" + (i + 1));
                }
            }
            System.out.println("Task end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //使用同步实例方法（多等待3秒），锁的粒度粗，执行效率低
    public synchronized void doLongTimeTask2() {
        try {
            System.out.println("Task begin");
            Thread.sleep(3000);
            System.out.println("开始同步");
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "--->" + (i + 1));
            }

            System.out.println("Task end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
