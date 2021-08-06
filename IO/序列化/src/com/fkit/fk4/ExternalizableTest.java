package com.fkit.fk4;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ExternalizableTest {
    @Test
    public void test1() {
        try {
            String path = "resources/external.txt";
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            Person per = new Person("张三", 123);
            oos.writeObject(per);
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            Person per_ = (Person) ois.readObject();
            System.out.println(per_);
            System.out.println(per == per_);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
