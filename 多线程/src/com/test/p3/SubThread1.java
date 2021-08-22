package com.test.p3;

public class SubThread1 extends Thread {

    public SubThread1(){
        System.out.println("构造方法打印当前线程：" + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run方法打印当前线程：" + Thread.currentThread().getName());
    }
}
