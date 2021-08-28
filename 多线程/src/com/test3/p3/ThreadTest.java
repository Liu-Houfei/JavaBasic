package com.test3.p3;

import org.junit.Test;

public class ThreadTest {

    static class PublicValue {
        private String name = "wkcto";
        private String pwd = "666";

        public synchronized void getVal() {
            System.out.println(Thread.currentThread().getName() +
                    ",getter --name:" + name + "---pwd:" + pwd);
        }

        public synchronized void setVal(String name, String pwd) {
            this.name = name;
            try {
                Thread.sleep(1000); //模拟操作name属性需要一定时间
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.pwd = pwd;
            System.out.println(Thread.currentThread().getName() +
                    ",setter --name:" + name + "---pwd:" + pwd);
        }
    }

    static class SubThread extends Thread {
        private PublicValue publicValue;

        public SubThread(PublicValue publicValue) {
            this.publicValue = publicValue;
        }

        @Override
        public void run() {
            publicValue.setVal("rrrr", "1234");
        }
    }

    @Test
    public void test1() throws InterruptedException {
        //开启子线程设置用户名和密码
        PublicValue publicValue = new PublicValue();
        SubThread t1 = new SubThread(publicValue);
        t1.start();
        //为了确定设置成功
        Thread.sleep(100);
        //在main线程中读取用户名和密码
        publicValue.getVal();

        Thread.sleep(10000);
    }

}
