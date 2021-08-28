package com.test4.p6;

/**
 * AtomicIntegerFieldUpdater,字符必须使用volatile修饰
 */
public class User {
    private int id;
    volatile int age;

    public User(int id, int age) {
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }
}
