package com.fkit.fk3;

import sun.reflect.generics.tree.Tree;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

class Z1 implements Comparable {
    int age;

    public Z1(int age) {
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int compareTo(Object obj) {
        return 1;
    }

    @Override
    public String toString() {
        return "Z1{" +
                "age=" + age +
                '}';
    }
}

public class TreeSetTest {

    public static void main(String[] args) {
        Z1 z1 = new Z1(6);
        TreeSet ts = new TreeSet();
        //在Z1类中重写的compareTo方法始终返回1，表明永远不会相等。TreeSet会插入相同的元素
        ts.add(z1);
        ts.add(z1);
        //在Z1类中重写toString方法才会输出，输出两个相同的元素
        System.out.println(ts);
        //修改第一个元素的age
        ((Z1) ts.first()).setAge(10);
        //输出所有元素,第二个元素也被改成了10
        System.out.println(ts);

//        TreeSet ts = new TreeSet();
//        Z1 z1 = new Z1(6);
//        Z1 z2 = new Z1(6);
//        ts.add(z1);
//        ts.add(z2);
//        System.out.println(ts);
//        ((Z1) ts.first()).setAge(10);
//        System.out.println(ts);

    }


//    public static void main(String[] args) {
//        TreeSet ts = new TreeSet();
//        //向TreeSet中添加元素
//        ts.add(5);
//        ts.add(2);
//        ts.add(10);
//        ts.add(-9);
//        //输出集合，元素处于排序状态
//        System.out.println(ts);
//        //输出集合首尾元素
//        System.out.println(ts.first());
//        System.out.println(ts.last());
//        //输出集合中指定元素之前和之后的元素
//        System.out.println(ts.lower(5));
//        System.out.println(ts.higher(5));
//        //子集合
//        SortedSet sub_ts = ts.subSet(2, 10);
//        System.out.println(sub_ts);
//        SortedSet head_ts = ts.headSet(2);
//        System.out.println(head_ts);
//        SortedSet tail_ts = ts.tailSet(2);
//        System.out.println(tail_ts);
//
//        Comparable cp;
//
//        Integer i;
//
//        Character c;
//    }
}
