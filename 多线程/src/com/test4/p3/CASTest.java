package com.test4.p3;

import org.junit.Test;

public class CASTest {

    @Test
    public void test1() throws InterruptedException {
        CASCounter casCounter = new CASCounter();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() +
                            "--->" + casCounter.incrementAndGet());
                }
            }).start();
        }
        Thread.sleep(5000);
        System.out.println(casCounter.getValue());
        Thread.sleep(5000);
    }
}

class CASCounter {
    //使用volatile修饰value值，使线程可见
    volatile private long value;
    public long getValue() {
        return value;
    }
    //定义compare和swap方法
    private boolean compareAndSwap(long exceptdVal, long newVal) {
        //如果当前value的值与期望的值一样，就把当前的value替换为newVal
        synchronized (this) {
            if (value == exceptdVal) {
                value = newVal;
                return true;
            } else {
                return false;
            }
        }
    }
    //定义自增的方法
    public long incrementAndGet() {
        long oldVal;
        long newVal;
        do {
            oldVal = value;
            newVal = oldVal + 1;
        } while (!compareAndSwap(oldVal, newVal));
        return newVal;
    }
}
