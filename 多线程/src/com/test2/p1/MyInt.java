package com.test2.p1;

public class MyInt {
    private int num;

    public int getNum() {
        return num++;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
