package com.test.p3;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("main方法当前线程：" + Thread.currentThread().getName());
        //创建子线程，调用子线程构造方法，此时的线程是main
        SubThread1 subThread1 = new SubThread1();
        //启动子线程，调用run方法
        subThread1.start();
        //在main线程中调用run方法
        subThread1.run();
    }
}
