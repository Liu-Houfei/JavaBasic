package com.test4.p11;

import org.junit.Test;

import java.util.Random;

public class ThreadTest2 {

    /**
     * 一个生产者一个消费者
     */
    @Test
    public void test1() {
        ValueOP valueOP = new ValueOP();
        Thread setThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    valueOP.setValue("Jack-" + new Random().nextInt());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        setThread.setName("setThread");
        Thread getThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String val = valueOP.getValue();
                    System.out.println(val);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        getThread.setName("getThread");
        setThread.start();
        getThread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 一个生产者多个消费者
     */
    @Test
    public void test2() throws InterruptedException {
        ValueOP valueOP = new ValueOP();
        Thread getThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "---" + valueOP.getValue());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        getThread1.setName("getThread-1");
        Thread getThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "---" + valueOP.getValue());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        getThread2.setName("getThread-2");
        Thread setThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    valueOP.setValue(new Random().nextInt() + "");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        setThread1.setName("setThread-1");

        getThread1.start();
        getThread2.start();
        setThread1.start();
        Thread.sleep(100000);

    }

    static class ValueOP {
        private String value = "";

        public void setValue(String value) {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + ",开始");
                while (!this.value.equals("")) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ",字符串不为空，等待处理");
                        this.wait();
                        System.out.println(Thread.currentThread().getName() + ",结束等待");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //如果是空串，则设置值。并唤醒其他线程
                this.value = value;
                System.out.println(Thread.currentThread().getName() + ",结束");
                this.notifyAll();
            }
        }

        public String getValue() {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + ",开始");
                while (value.equals("")) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ",字符串为空，等待产生字符串");
                        this.wait();
                        System.out.println(Thread.currentThread().getName() + ",结束等待");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ",结束");
                String tmpVal = this.value;
                this.value = "";    //读完清空
                this.notifyAll();
                return tmpVal;

            }
        }
    }
}