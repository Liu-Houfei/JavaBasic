package com.fkit.fk3;

import java.io.Serializable;

//java5之前的早期枚举类写法
public class Orientation implements Serializable {
    //枚举变量 HORIZONTAL=1，VERTICAL=2
    public static final Orientation HORIZONTAL = new Orientation(1);
    public static final Orientation VERTICAL = new Orientation(2);
    private int value;

    //私有构造器设置value
    private Orientation(int value) {
        this.value = value;
    }

    private Object readResolve() {
        if (value == 1)
            return HORIZONTAL;
        if (value == 2)
            return VERTICAL;
        return null;
    }

}
