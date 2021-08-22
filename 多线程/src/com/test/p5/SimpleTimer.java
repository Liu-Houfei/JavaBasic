package com.test.p5;

/**
 * 使用线程休眠完成简易计时器
 */
public class SimpleTimer {
    public static void main(String[] args) {
        int remaining = 60;  //从60秒开始计时
        remaining = Integer.parseInt(args[0]);
        while (true) {
            System.out.println("Remaining:" + remaining);
            remaining--;
            if (remaining < 0) break;
            try {
                Thread.sleep(1000);  //线程休眠
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done!");
    }
}
