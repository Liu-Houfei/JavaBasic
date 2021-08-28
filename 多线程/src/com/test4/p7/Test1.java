package com.test4.p7;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class Test1 {
    //创建一个AtomicReference对象
    static AtomicReference<String> atomicReference = new AtomicReference<>("abc");
    public static void main(String[] args) throws InterruptedException {
        //创建100个线程修改字符串
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (atomicReference.compareAndSet("abc", "def")) {
                        System.out.println(Thread.currentThread().getName() + "---" + "把字符串更改为def");
                    }
                }
            }).start();
        }
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (atomicReference.compareAndSet("def", "abc")) {
                        System.out.println(Thread.currentThread().getName() + "---" + "把字符串还原成abc");
                    }
                }
            }).start();
        }
        Thread.sleep(10000);
    }
}
