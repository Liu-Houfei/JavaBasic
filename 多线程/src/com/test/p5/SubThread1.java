package com.test.p5;

public class SubThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("currently executing thread:" + Thread.currentThread().getName()
                + ",begin=" + System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("currently executing thread:" + Thread.currentThread().getName()
                + ",end=" + System.currentTimeMillis());
    }
}
