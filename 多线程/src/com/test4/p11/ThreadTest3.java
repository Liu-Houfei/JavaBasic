package com.test4.p11;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadTest3 {
    @Test
    public void test1() {
        MyStack myStack = new MyStack();
        //定义生产者线程1
        Thread producer1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    myStack.push();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        producer1.setName("producer-1");
        producer1.start();
        //定义生产者线程2
        Thread producer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    myStack.push();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        producer2.setName("producer-2");
        producer2.start();

        //定义一个消费者线程1
        Thread costumer1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Object obj = myStack.pop();
                    System.out.println(Thread.currentThread().getName() + "---" + obj.toString());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        costumer1.setName("costumer-1");
        costumer1.start();

        //定义一个消费者线程2
        Thread costumer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Object obj = myStack.pop();
                    System.out.println(Thread.currentThread().getName() + "---" + obj.toString());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        costumer2.setName("costumer-2");
        costumer2.start();

        //每3秒输出栈的情况
        while (true) {
            myStack.printMyStack();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyStack {
        private List<String> stack = new ArrayList(); //栈体
        private static final int MAX = 5; //栈最大容量

        //模拟入栈
        public synchronized void push() {
            //如果栈满就等待
            while (stack.size() >= MAX) {
                try {
                    System.out.println("当前栈满，等待消费者处理");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int rand = new Random().nextInt();
            stack.add(rand + "");
            System.out.println(Thread.currentThread().getName() + "---" + rand);
            notifyAll();
        }

        //模拟出栈
        public synchronized Object pop() {
            //如果栈空就等待
            while (stack.size() <= 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Object obj = stack.remove(0);
            notifyAll();
            return obj;
        }

        //输出当前栈的情况
        public void printMyStack() {
            System.out.println(stack.toString());
        }
    }
}
