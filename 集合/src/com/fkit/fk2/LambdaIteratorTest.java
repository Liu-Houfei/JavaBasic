package com.fkit.fk2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LambdaIteratorTest {
//    public static void main(String[] args) {
//        ArrayList<String> strs = new ArrayList<>();
//        strs.add("123");
//        strs.add("abc");
//        strs.add("你好");
//        Iterator iterator = strs.iterator();
//        iterator.forEachRemaining( list -> {
//            if(!list.equals("abc")){
//                System.out.println(list + "_lambda"+"_not abc");
//            }else {
//                System.out.println(list + "_lambda");
//            }
//        });
//    }

//    public static void main(String[] args) {
//        Collection strs = new ArrayList<>();
//        strs.add("123");
//        strs.add("abc");
//        strs.add("你好");
//        //foreach循环便利集合strs
//        for (Object obj : strs) {
//            if (obj instanceof String) {
//                System.out.println((String) obj+"_foreach");
//            }
//        }
//    }

    public static void main(String[] args) {
        //Predicate 是一个函数式接口
        Collection strs = new ArrayList();
        strs.add("123");
        strs.add("abc");
        strs.add("你好");
        strs.removeIf(list -> list.equals("你好"));
        for (Object obj : strs) {
            if (obj instanceof String) {
                System.out.println((String) obj);
            }
        }
    }
}
