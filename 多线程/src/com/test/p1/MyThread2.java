package com.test.p1;

public class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("sub thread:" + i);
            int time = (int) (Math.random() * 1000);
            try {
                Thread.sleep(time);  //线程休眠，单位毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
