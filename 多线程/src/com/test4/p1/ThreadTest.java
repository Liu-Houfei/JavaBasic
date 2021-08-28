package com.test4.p1;

import org.junit.Test;

public class ThreadTest {

    @Test
    public void test1() throws InterruptedException {
        //创建PrintString对象
        PrintString printString = new PrintString();
        printString.printStringMethod();

        //main线程睡眠1000毫秒
        Thread.sleep(1000);

        //在main线程修改打印标志
        printString.continuePrint = false;  //程序不会停止，一直处于死循环，不会执行到该语句。
        //解决方法：使用多线程技术


    }

    @Test
    public void test2() throws InterruptedException {
        PrintString printString = new PrintString();
        //开启子线程，在子线程中调用  printString.printStringMethod();
        new Thread(new Runnable() {
            @Override
            public void run() {
                printString.printStringMethod();
            }
        }).start();

        //main线程睡眠1000毫秒
        Thread.sleep(1000);

        //在main线程修改打印标志
        System.out.println("main线程修改标志");
        printString.continuePrint = false;  //程序不会停止，一直处于死循环，不会执行到该语句。
        //解决方法：使用多线程技术
        //程序运行后会出现死循环的情况
        //原因：main线程修改了标志后，子线程读不到
        //解决方法：使用volatile关键字修饰printString对象的打印标志，
        //volatile的作用是可以强制线程从公共内存中读取变量的值，而不是在工作内存。
        Thread.sleep(100000);
    }

    static class PrintString {
        private volatile boolean continuePrint = true;

        public PrintString setContinePrint(boolean continePrint) {
            this.continuePrint = continePrint;
            return this;
        }

        public void printStringMethod() {
            System.out.println(Thread.currentThread().getName() + "开始");
            while (continuePrint) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }

    }
}
