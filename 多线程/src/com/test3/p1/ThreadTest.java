package com.test3.p1;

import org.junit.Test;

public class ThreadTest {

    @Test
    public void test1() throws InterruptedException {
        //创建两个线程，分别调用mm方法
        //先创建ThreadTest对象，通过对象名调用mm方法
        ThreadTest obj = new ThreadTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.mm();   //使用的锁对象是obj对象
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.mm();   //使用的锁对象也是obj对象
            }
        }).start();

        Thread.sleep(5000);
    }

    @Test
    public void test2() throws InterruptedException {
        //创建两个线程，分别调用mm方法
        //先创建ThreadTest对象，通过对象名调用mm方法
        ThreadTest obj1 = new ThreadTest();
        ThreadTest obj2 = new ThreadTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj1.mm();   //使用的锁对象是obj1对象
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj2.mm();   //使用的锁对象也是obj2对象
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sm();
            }
        }).start();

        Thread.sleep(5000);
    }


    @Test
    public void test3() throws InterruptedException {
        ThreadTest obj = new ThreadTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.mm();   //使用的锁对象是obj对象
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.mm2();   //使用的锁对象也是obj对象
            }
        }).start();

        Thread.sleep(5000);
    }

    //打印100行字符串
    public void mm() {
        synchronized (this) {   //经常使用this当前对象作为锁对象
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "---->" + i);
            }
        }
    }

    //使用synchronized修饰实例方法，同步实例方法，默认this作为锁对象
    public synchronized void mm2() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "---->" + i);
        }
    }

    public static final Object OBJ = new Object();

    public static void sm() {
        synchronized (OBJ) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "---->" + i);
            }
        }
    }

    public void m1() {
        //使用当前类的运行时类对象作为锁对象（把ThreadTest类的字节码作为锁对象）
        synchronized (ThreadTest.class) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "---->" + i);
            }
        }
    }

    //使用synchronized修饰静态方法，默认运行时类作为锁对象
    public synchronized static void m2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "---->" + i);
        }
    }

    @Test
    public void test4() throws InterruptedException {
        ThreadTest obj = new ThreadTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.m1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadTest.m2();
            }
        }).start();

        Thread.sleep(100000);
    }

}
