package com.test.p2;

public class MainTest {
    public static void main(String[] args) {
        //创建Runnable接口实现类对象
        MyRunnable1 myRunnable1 = new MyRunnable1();
        //创建线程对象
        Thread thread = new Thread(myRunnable1);
        //启动线程
        thread.run();

        //有时候调用Thread(Runnable)构造方法时，实参也会传递匿名内部类对象。
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    System.out.println("sub----thread:" + i);
                }
            }
        });
        thread2.start();

        //当前是main线程
        for (int i = 1; i <= 100; i++) {
            System.out.println("main thread:" + i);
        }
    }
}
