package com.test2.p2;

import org.junit.Test;

import java.util.Random;

/**
 * 测试线程可见性
 */
public class ThreadTest {

    @Test
    public void test1() throws InterruptedException {
        MyTask myTask1 = new MyTask();
        new Thread(myTask1).start();    //启动子线程
        Thread.sleep(5000);
        //主线程休眠5秒和调用取消的方法
        /**
         * 可能会出现：
         *   在main线程调用task.cancel()方法，把task对象的toCancle变量修改为true,
         *   可能存在子线程看不到main线程对toCancel做的修改，在子线程中toCancel一直为false
         * 导致子线程看不到main线程对toCancel变量更新的原因，可能：
         *   1）JTT即时编译器可能会对run方法中的while循环优化，
         *
         *   while (!toCancel) {
         *      if (doSome()) {
         *
         *      }
         *   }
         *   JTT优化后：
         *   if(!toCancel){
         *      while(true){
         *          if(doSome){
         *
         *          }
         *      }
         *   }//导致死循环
         *
         *   2）可能与计算机的存储系统有关，假设有两个cpu内核运行main线程与子线程，
         *   一个cpu内核无法立即读取另一个cpu内核中的数据。
         */
        myTask1.cancel();

        Thread.sleep(10000);
    }

    static class MyTask implements Runnable {
        private boolean toCancel = false;
        @Override
        public void run() {
            while (!toCancel) {
                if (doSome()) {

                }
            }
            if (toCancel) {
                System.out.println("任务被取消");
            } else {
                System.out.println("任务正常结束");
            }
        }
        private boolean doSome() {
            System.out.println("执行某个任务");
            try {
                Thread.sleep(1000);  //模拟执行任务时间
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        public void cancel() {
            System.out.println("收到取消线程的消息");
            toCancel = true;
        }
    }
}
