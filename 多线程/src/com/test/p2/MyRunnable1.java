package com.test.p2;

/**
 * 当线程类已经有父类了，就不能使用继承Thread类的形式创建线程。
 * 可以使用实现Runnable接口的形式创建线程
 */
public class MyRunnable1 implements Runnable {
    //重写Runnable接口的抽象方法run方法
    //子线程的任务放在run中
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println("sub thread:" + i);
        }
    }
}
