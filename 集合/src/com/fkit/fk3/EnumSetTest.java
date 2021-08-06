package com.fkit.fk3;

import java.util.EnumSet;

enum Weekday {
    Mon, Tes, Wen, Thur, Fri
}

public class EnumSetTest {
    public static void main(String[] args) {
        //创建一个EnumSet集合，集合元素是枚举类Weekday的所有枚举值
        EnumSet es1 = EnumSet.allOf(Weekday.class);
        System.out.println(es1);
        //创建一个空EnumSet集合，集合的枚举类型为Weekday
        EnumSet es2 = EnumSet.noneOf(Weekday.class);
        System.out.println(es2);
        //手动向es2集合添加两个元素
        es2.add(Weekday.Tes);
        es2.add(Weekday.Fri);
        System.out.println(es2);
        //创建一个新EnumSet集合，集合中包含es2之外和枚举类剩下的枚举值
        EnumSet es3 = EnumSet.complementOf(es2);
        System.out.println(es3);
        //创建新集合，使用EnumSet.of方法添加集合元素
        EnumSet es4 = EnumSet.of(Weekday.Mon, Weekday.Tes, Weekday.Thur);
        System.out.println(es4);
        //创建新集合，使用EnumSet.range(from,to)方法添加范围内的枚举值
        EnumSet es5 = EnumSet.range(Weekday.Mon, Weekday.Thur);
        System.out.println(es5);
    }
}
