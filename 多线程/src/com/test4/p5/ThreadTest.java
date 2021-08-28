package com.test4.p5;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class ThreadTest {

    @Test
    public void test1() throws InterruptedException {
        //定义一个线程数组
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new AtomicArrayTest.AddThread();
        }
        //开启子线程
        for (Thread thread : threads) {
            thread.start();
        }

        //将子线程合并到主线程
        for (Thread thread : threads) {
            thread.join();
        }

        //输出原子数组
        System.out.println(AtomicArrayTest.atomicIntegerArray);
    }
}

class AtomicArrayTest {
    //定义原子数组
    static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);

    //定义一个线程类，在线程类中修改原子数组
    static class AddThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ",start...");
            //把原子数组的每个元素自增1000次
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 10; j++) {
                    if (atomicIntegerArray.get(j) < 100) {
                        atomicIntegerArray.incrementAndGet(j);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + ",end...");
            System.out.println(atomicIntegerArray);
        }
    }
}

