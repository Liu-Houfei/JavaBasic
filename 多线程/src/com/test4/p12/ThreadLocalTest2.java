package com.test4.p12;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalTest2 {
    //private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    //添加ThreadLocal对象
    static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();
    static class ParseData implements Runnable {
        private int i;
        public ParseData(int i) {
            this.i = i;
        }
        @Override
        public void run() {
            try {
                String text = "2021年8月28日 08:28:" + i % 60;
                //Date date = sdf.parse(text);
                //判断当前线程是否有SimpleDateFormat对象，没有则创建
                if (threadLocal.get() == null) {
                    threadLocal.set(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"));
                }
                Date date = threadLocal.get().parse(text);
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 多线程环境下把字符串转换成日期对象
     */
    @Test
    public void test1() {
        //创建10个线程
        for (int i = 0; i < 10; i++) {
            new Thread(new ParseData(i)).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
