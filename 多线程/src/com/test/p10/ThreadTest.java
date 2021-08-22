package com.test.p10;


import org.junit.Test;

public class ThreadTest {
    @Test
    public void test1() throws InterruptedException {
        SubThread1 t1 = new SubThread1();
        //设置线程为守护线程，销毁守护线程需要时间，会继续执行一段时间
        //在线程启动前设置成守护线程
        t1.setDaemon(true);
        t1.start();

        //当前线程为main
        for (int i = 0; i < 10; i++) {
            System.out.println("main== >" + i);
        }

        //当main线程结束，守护线程t1也被销毁

        Thread.sleep(10000);
    }
}
