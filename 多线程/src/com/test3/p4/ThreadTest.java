package com.test3.p4;

import org.junit.Test;

public class ThreadTest {

    static class SubThread extends Thread {
        private static final Object lock1 = new Object();
        private static final Object lock2 = new Object();

        @Override
        public void run() {
            if ("a".equals(Thread.currentThread().getName())) {
                synchronized (lock1) {
                    System.out.println("a线程获得了lock1锁，还需要获得lock2锁");
                    synchronized (lock2) {
                        System.out.println("a线程获得lock1后，又获得lock2锁");
                    }
                }

            }

            if ("b".equals(Thread.currentThread().getName())) {
                synchronized (lock2) {
                    System.out.println("b线程获得了lock2锁，还需要获得lock1锁");
                    synchronized (lock1) {
                        System.out.println("b线程获得lock2后，又获得lock1锁");
                    }
                }
            }
        }
    }

    @Test
    public void test1() throws InterruptedException {
        SubThread t1 = new SubThread();
        t1.setName("a");
        t1.start();
        SubThread t2 = new SubThread();
        t2.setName("b");
        t2.start();

        Thread.sleep(10000);
    }
}
