package com.fkit.fk2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("执行writeObject");
        //将name实例变量值反转后写入二进制输出流
        out.writeObject(new StringBuffer(name).reverse());
        out.writeObject(age);
        System.out.println("结束writeObject");
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("执行readObject");
        //将读取的字符串反转后赋给name实例变量
        //this.name = ((StringBuffer) in.readObject()).reverse().toString();
        this.name = ((StringBuffer) in.readObject()).append("_*_").toString();
        this.age = (Integer) in.readObject();
        System.out.println("结束readObject");
    }

    //序列化对象时将该对象换成其他对象，程序序列化之前优先调用这个方法
    private Object writeReplace() {
        System.out.println("执行writeReplace");
        //将Person对象替换成ArrayList对象
        ArrayList<Object> list = new ArrayList<>();
        list.add(name);
        list.add(age);
        System.out.println("结束writeReplace");
        return list;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
