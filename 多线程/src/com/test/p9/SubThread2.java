package com.test.p9;

public class SubThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            //判断线程的中断标志,isInterrupted()可以返回线程的中断标志，true中断
            if (this.isInterrupted()) {
                System.out.println("当前线程的中断标志为true");
                //break;  //循环中断，run方法执行完毕，子线程执行完毕
                return; //直接结束run方法的执行，子线程执行完毕
            }
            System.out.println(Thread.currentThread().getName() + "===>" + i);
        }
    }
}
