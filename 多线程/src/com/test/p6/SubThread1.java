package com.test.p6;

public class SubThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("current_thread:" + Thread.currentThread().getName()
                + ",current_threadId:" + Thread.currentThread().getId());
    }
}
