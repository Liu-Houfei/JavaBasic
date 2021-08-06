package com.fkit2;

@FunctionalInterface
interface MyTest {
    String test(String a, int b, int c);
}

public class T2 {
    public static void main(String[] args) {
        //lambda表达式实现函数式接口方法
        //MyTest myTest = (a, b, c) -> a.substring(b, c);
        //应用String类对象的substring方法
        MyTest myTest = String::substring;
        String s = myTest.test("hello,world", 2, 4);
        System.out.println(s);
    }
}
