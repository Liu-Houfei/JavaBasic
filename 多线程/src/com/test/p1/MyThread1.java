package com.test.p1;

/**
 * 定义继承Thread的子类MyThread
 */
public class MyThread1 extends Thread {

    //重写Thread父类中的run()
    //run()方法中的代码体现的是子线程要执行的任务
    @Override
    public void run() {
        //super.run();
        System.out.println("这是子线程MyThread执行的run()");
    }
}
