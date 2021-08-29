package com.test4.p10;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest2 {

    /**
     * wait条件发生了变化
     * 定义一个线程向集合中添加数据，添加完数据后通知另一个线程从集合中读取数据
     * 定义一个线程从集合中读取数据，如果集合为空则等待
     */
    @Test
    public void test1() {
        Thread readThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                read();
            }
        });
        Thread readThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                read();
            }
        });
        Thread addThread = new Thread(new Runnable() {
            @Override
            public void run() {
                add();
            }
        });
        readThread1.setName("readThread-1");
        readThread1.start();
        readThread2.setName("readThread-2");
        readThread2.start();
        addThread.setName("addThread");
        addThread.start();


        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    List<String> list = new ArrayList<>();

    /**
     * 读取方法
     */
    public void read() {
        while (list.size() == 0) { //如果集合为空则等待
            synchronized (list) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 开始等待");
                    list.wait();
                    System.out.println(Thread.currentThread().getName() + " 结束等待");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Object obj = list.remove(0);
        System.out.println(Thread.currentThread().getName()
                + " 读取：" + obj.toString() + "，此时集合大小：" + list.size());
    }

    /**
     * 添加方法
     */
    public void add() {
        synchronized (list) {
            System.out.println(Thread.currentThread().getName() + " 开始添加");
            list.add("BBC");
            System.out.println(Thread.currentThread().getName() + " 结束添加");
            list.notifyAll();
        }
    }
}
