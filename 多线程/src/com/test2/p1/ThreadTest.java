package com.test2.p1;

import org.junit.Test;

public class ThreadTest {
    @Test
    public void test1() throws InterruptedException {
        //启动两个线程，不断调用getNum()方法
        MyInt myInt = new MyInt();
        myInt.setNum(1);
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println(Thread.currentThread().getName() + "-->" + myInt.getNum());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        Thread.sleep(50000);
    }

    @Test
    public void test2() throws InterruptedException {
        //启动两个线程，不断调用getNum()方法
        MyInt2 myInt2 = new MyInt2();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "-->" + myInt2.getNum());
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        Thread.sleep(50000);
    }
}
