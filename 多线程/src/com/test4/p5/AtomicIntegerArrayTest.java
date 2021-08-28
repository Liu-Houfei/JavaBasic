package com.test4.p5;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {

    @Test
    public void test1() {
        //创建一个指定长度的原子数组
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        System.out.println(atomicIntegerArray);
        //返回指定位置的元素
        System.out.println(atomicIntegerArray.get(0));
        System.out.println(atomicIntegerArray.get(1));
        //设置指定位置的元素
        atomicIntegerArray.set(0, 10);
        int t1 = atomicIntegerArray.getAndSet(1, 11);
        System.out.println(atomicIntegerArray.get(0));
        System.out.println(atomicIntegerArray.get(1));
        //修改数组元素的值，把数组元素加上某个值
        atomicIntegerArray.addAndGet(0, 12);
        System.out.println(atomicIntegerArray.get(0));
        atomicIntegerArray.getAndAdd(0, 12);
        System.out.println(atomicIntegerArray.get(0));
        //CAS操作
        System.out.println(atomicIntegerArray.compareAndSet(3, 0, 222));
        System.out.println(atomicIntegerArray.get(3));
        atomicIntegerArray.incrementAndGet(0);
        atomicIntegerArray.decrementAndGet(0);
    }
}
