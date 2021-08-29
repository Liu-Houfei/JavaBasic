package com.test4.p11;

import org.junit.Test;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class ThreadTest4 {
    /**
     * 使用PipedInputStream和PipedOutputStream管道字节流在线程间传递数据
     */
    @Test
    public void test1() {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();
        //在输入管道流和输出管道流之间建立连接
        try {
            in.connect(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建线程向管道中写入数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"---写管道开始");
                writeData(out);
                System.out.println(Thread.currentThread().getName()+"---写管道结束");
            }
        }).start();
        //创建线程从管道中读取数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"---读管道开始");
                readData(in);
                System.out.println(Thread.currentThread().getName()+"---读管道结束");
            }
        }).start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //定义方法向管道流写入数据
    public static void writeData(PipedOutputStream out) {
        //将0-100个数写入管道流
        for (int i = 0; i < 100; i++) {
            try {
                out.write((i + " ").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //定义方法从管道中读取数据
    public static void readData(PipedInputStream in) {
        //定义字节数组
        byte[] bytes = new byte[30];
        int len = 0;
        try {
            while ((len = in.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len));
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
