package com.test4.p10;

import org.junit.Test;

public class ThreadTest {
    @Test
    public void t1() {
        //定义一个班对象，作为子线程的锁对象
        Object lock = new Object();
        SubThread t1 = new SubThread(lock);
        SubThread t2 = new SubThread(lock);
        SubThread t3 = new SubThread(lock);
        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");
        t1.start();
        t2.start();
        t3.start();

        //2秒后唤醒子线程
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock) {
            //lock.notify();  //调用一次notify只能唤醒其中一个线程
            lock.notifyAll();   //唤醒所有线程
        }
    }

    class SubThread extends Thread {
        private Object lock;

        public SubThread(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "同步开始");
                try {
                    System.out.println(Thread.currentThread().getName() + "阻塞");
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "--interrupt");
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "同步结束");
            }
        }
    }
}
