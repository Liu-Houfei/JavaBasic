package com.test4.p8;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest {

    //演示wait()/notify()方法需要放到同步代码块中
    //任何对象都可以调用wait()/notify()方法，因为这两个方法是从Object类继承来的
    @Test
    public void t1() {
        String test = "hello";
        try {
            test.wait();    //java.lang.IllegalMonitorStateException
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t2() {
        String test = "abcdefg";
        String other = "other";
        System.out.println("同步前");
        synchronized (test) {
            System.out.println("同步代码块开始....");
            try {
                test.wait();    //调用wait()方法后，当前线程进入等待状态；如果没有被唤醒将会一直等待。
                //other.wait();   //不是锁对象调用会产生异常：java.lang.IllegalMonitorStateException
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("wait后的代码");
        }
        System.out.println("同步代码块结束");
    }

    //通过notify()唤醒等待的线程
    @Test
    public void t3() throws InterruptedException {
        String lock = "abcd";   //定义一个字符串作为锁对象
        Thread t1 = new Thread(new Runnable() { //线程1调用wait()
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "开始等待，" + System.currentTimeMillis());
                    try {
                        lock.wait();    //会释放锁对象，当前线程进行blocked阻塞状态
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "结束等待，" + System.currentTimeMillis());
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "开始唤醒，" + System.currentTimeMillis());
                        lock.notify();  //使锁对象获得锁
                        System.out.println(Thread.currentThread().getName() + "结束唤醒，" + System.currentTimeMillis());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        Thread.sleep(3000);
        t2.start();
        Thread.sleep(10000);
    }

    //notify不会立即释放锁对象
    @Test
    public void t4() throws InterruptedException {
        List<String> list = new ArrayList<>();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list) {
                    System.out.println(Thread.currentThread().getName() + "开始同步");
                    if (list.size() < 5) { //如果list的大小小于5，则释放锁，使该线程处于阻塞状态
                        try {
                            System.out.println(Thread.currentThread().getName() + "进入阻塞状态");
                            list.wait();
                            System.out.println(Thread.currentThread().getName() + "结束阻塞状态");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println(Thread.currentThread().getName() + "结束同步");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list) {
                    System.out.println(Thread.currentThread().getName() + "开始同步");
                    for (int i = 0; i < 6; i++) {
                        list.add("data--" + i);
                        if (list.size() > 5) {
                            //如果list大小大于5则唤醒线程1
                            System.out.println("线程2唤醒线程1");
                            list.notify();  //不会立即释放锁对象，要执行完同步代码块之后才释放锁对象
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "添加第" + i + "个数据");
                    }
                    System.out.println(Thread.currentThread().getName() + "结束同步");
                }
            }
        });
        t1.setName("t1");        t2.setName("t2");
        t1.start();        Thread.sleep(3000);        t2.start();
        Thread.sleep(60000);

    }
}
