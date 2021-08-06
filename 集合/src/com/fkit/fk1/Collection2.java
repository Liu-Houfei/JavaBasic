package com.fkit.fk1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Collection2 {

    public static void main(String[] args) {
        Collection c = new ArrayList<>();
        c.add("Java");
        c.add("python");
        c.add("c++");
        // 获取集合的迭代器
        Iterator ite = c.iterator();
        //调用Iterator的默认方法forEachRemaining
        //参数使用lambda表达式，目标类型是Consumer
        ite.forEachRemaining((s) -> System.out.println(s));
    }
}
