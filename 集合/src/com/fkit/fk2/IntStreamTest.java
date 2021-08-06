package com.fkit.fk2;

import java.util.stream.IntStream;

public class IntStreamTest {
    public static void main(String[] args) {
        IntStream is = IntStream.builder().add(20).add(-10).add(30).build();
        System.out.println("最大值：" + is.max().getAsInt());
        System.out.println("最小值：" + is.min().getAsInt());
        System.out.println("总和：" + is.sum());
        System.out.println("总数：" + is.count());
        System.out.println("均值：" + is.average());
        System.out.println("所有元素的平方是否都大于20:" + is.allMatch(ele -> Math.pow(ele, 2) > 20));
        System.out.println("是否包含任何元素的平方大于20:" + is.anyMatch(ele -> Math.pow(ele, 2) > 20));
        //将is映射成一个新的Stream,新Stream的元素是原Stream的2倍+1
        IntStream newIs = is.map(ele -> ele * 2 + 1);
        newIs.forEach(ele -> System.out.println(ele));
        newIs.forEach(System.out::println);
    }
}
