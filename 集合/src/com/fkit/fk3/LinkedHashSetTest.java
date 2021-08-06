package com.fkit.fk3;

import java.util.LinkedHashSet;

public class LinkedHashSetTest {
    public static void main(String[] args) {
        LinkedHashSet lhs = new LinkedHashSet();
        lhs.add("1");
        lhs.add("2");
        lhs.add("3");
        System.out.println(lhs);
        lhs.remove("2");
        lhs.add("2");
        System.out.println(lhs);
    }
}
