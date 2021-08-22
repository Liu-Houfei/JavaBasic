package com.test.p3;

import org.junit.Test;

public class ThreadTest {

    @Test
    public void test1() throws InterruptedException {
        SubThread2 t2 = new SubThread2();
        t2.setName("t2");
        t2.start();

        Thread.sleep(500);

        //public Thread(Runnable target)
        //class Thread implements Runnable
        //public class SubThread2 extends Thread
        //t2是Runnable接口实现类对象（Thread父类实现了Runnable接口）
        Thread t3 = new Thread(t2);
        t3.setName("t3");
        t3.start();

    }

    @Test
    public void test2(){
        SubThread2 t2 = new SubThread2();
        t2.setName("t2");
        Thread t3 = new Thread(t2);
        t3.setName("t3");
        //t3.start();
        t3.run();
    }
}
