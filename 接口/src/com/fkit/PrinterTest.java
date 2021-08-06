package com.fkit;

public class PrinterTest {
    public static void main(String[] args) {
        Printer p = new Printer();
        //创建Output接口引用变量，引用Printer对象
        Output o = p;
        //接口回调，调用接口普通方法
        o.getData("轻量级选手");
        o.getData("java");
        o.out();
        o.getData("python");
        o.getData("c++");
        o.out();
        //调用接口中默认方法
        o.print("孙悟空", "猪八戒");
        o.test();
        //创建Product接口变量引用Printer实例对象
        Product product = p;
        System.out.println(product.getProduceTime());
        Object obj = o;
        obj = product;
        obj = p;
    }
}
