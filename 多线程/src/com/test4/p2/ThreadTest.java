package com.test4.p2;

import org.junit.Test;

public class ThreadTest {

    @Test
    public void test1() throws InterruptedException {
        //在main线程创建10个子线程
        for (int i = 0; i < 100; i++) {
            new MyThread().start();
        }

        Thread.sleep(50000);
    }

    static class MyThread extends Thread {
        //volatile关键字仅仅表示所有线程从主内存读取count变量的值
/*        volatile public static int count;

        public static void addCount() {
            for (int i = 0; i < 1000; i++) {
                //count++不是原子操作
                count++;
            }
            System.out.println(Thread.currentThread().getName() + "count==>" + count);
        }*/

        public static int count;
        public synchronized static void addCount() {
            for (int i = 0; i < 1000; i++) {
                //count++不是原子操作
                count++;
            }
            System.out.println(Thread.currentThread().getName() + "count==>" + count);
        }
        @Override
        public void run() {
            addCount();
        }
    }
}
