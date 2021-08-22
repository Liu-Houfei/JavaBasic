package com.test2.p1;

import java.util.concurrent.atomic.AtomicInteger;

public class MyInt2 {
    AtomicInteger num = new AtomicInteger();

    public int getNum() {
        return num.getAndIncrement();
    }
}
