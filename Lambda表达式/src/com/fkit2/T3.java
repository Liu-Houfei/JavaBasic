package com.fkit2;

import javax.swing.*;

@FunctionalInterface
interface YourTest {
    JFrame win(String title);
}

public class T3 {
    public static void main(String[] args) {
        //使用lambda语句创建YourTest对象，引用JFrame构造器
        //YourTest yourTest = s -> new JFrame(s);
        //lambda表达式引用JFrame的构造器
        YourTest yourTest = JFrame::new;
        JFrame frame = yourTest.win("我的窗口");
        System.out.println(frame.getName());

    }
}
