package com.test.p4;

public class SubThread1 extends Thread {

    @Override
    public void run() {
        System.out.println("currentThread：" + Thread.currentThread().getName());
        System.out.println("当前对象：" + this.getName());
    }
}
