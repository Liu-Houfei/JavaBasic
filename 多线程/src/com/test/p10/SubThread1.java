package com.test.p10;

public class SubThread1 extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("sub thread ...");
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
