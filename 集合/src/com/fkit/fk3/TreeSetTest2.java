package com.fkit.fk3;

import java.util.Comparator;
import java.util.TreeSet;

class M {
    int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "M[" +
                "age=" + age +
                ']';
    }
}

public class TreeSetTest2 {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet((o1, o2) -> {
            M m1 = (M) o1;
            M m2 = (M) o2;
            //逆序排列
            return m1.getAge() > m2.getAge() ? -1 : m1.getAge() < m2.getAge() ? 1 : 0;
        });
        M man1 = new M();
        man1.setAge(10);
        M man2 = new M();
        man2.setAge(25);
        M man3 = new M();
        man3.setAge(1);
        ts.add(man1);
        ts.add(man2);
        ts.add(man3);
        System.out.println(ts);
    }
}
