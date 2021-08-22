package com.test.p1;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("JVM启动main线程，main线程执行main方法");
        //创建子线程MyThread对象
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        //启动线程
        myThread2.start();
        myThread1.start();
        /*
        调用线程的start方法来启动线程，实质上是请求JVM运行相应的线程，
        这个线程具体在什么时候执行由线程调度器Scheduler决定

        注意：start方法调用结束并不意味着子线程开始运行，只是通知线程调度器；
             新开启的线程会执行run方法；
             如果开启多个线程，start调用的顺序并不一定就是线程启动的顺序；
             多线程运行结果与代码执行顺序或调用顺序无关
         */

        System.out.println("main线程后面的其他代码");
    }
}
