package com.test4.p4;

import org.junit.Test;

import java.util.Random;

/**
 * 模拟服务器的请求总数，处理成功数，处理失败数
 */
public class AtomicTest {
    @Test
    public void test1() throws InterruptedException {
        //通过线程模拟请求
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //每个线程就是一个请求
                    Indicator.getInstance().newRequestReceive();
                    int num = new Random().nextInt();
                    if (num % 2 == 0) {
                        Indicator.getInstance().requestProcessSuccess();
                    } else {
                        Indicator.getInstance().requesuProcessFailure();
                    }
                }
            }).start();
        }

        Thread.sleep(3000);
        System.out.println("请求数：" + Indicator.getInstance().getRequestCount());
        System.out.println("成功数：" + Indicator.getInstance().getSuccessCount());
        System.out.println("失败数：" + Indicator.getInstance().getFailureCount());

        Thread.sleep(10000);
    }
}
