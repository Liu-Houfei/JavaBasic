package com.fkit.fk1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Collection1 {
    public static void main(String[] args) {
        Collection c = new ArrayList<>();
        c.add("Java");
        c.add("python");
        c.add("c++");
        c.forEach((s) -> System.out.println(s));
    }
}
