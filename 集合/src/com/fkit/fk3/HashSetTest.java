package com.fkit.fk3;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

class R {
    int count;

    public R(int count) {
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "R[" +
                "count=" + count +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof R)) return false;
        R r = (R) o;
        return count == r.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}

public class HashSetTest {
    public static void main(String[] args) {
        HashSet hs = new HashSet();
        hs.add(new R(5));
        hs.add(new R(-3));
        hs.add(new R(9));
        hs.add(new R(-2));
        System.out.println(hs);
        Iterator ite = hs.iterator();
        R first = (R) ite.next();
        first.setCount(-3);
        System.out.println(hs);
        hs.remove(new R(-3));
        System.out.println(hs);
        System.out.println(hs.contains(new R(-3)));
        System.out.println(hs.contains(new R(5)));
    }
}
