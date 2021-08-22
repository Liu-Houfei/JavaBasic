package com.test.p3;

public class SubThread2 extends Thread {

    SubThread2() {
        System.out.println("构造方法中currentThread()：" + Thread.currentThread().getName());
        System.out.println("构造方法中this.getName()：" + this.getName());
    }

    @Override
    public void run() {
        System.out.println("SubThread2-Thread.currentThread()：" + Thread.currentThread().getName());
        System.out.println("SubThread2-this.getName()：" + this.getName());
    }
}
