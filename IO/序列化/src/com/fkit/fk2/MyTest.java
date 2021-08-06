package com.fkit.fk2;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

public class MyTest {

    @Test
    public void test1() {
        try {
            String path = "resources/object1.txt";
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            Person per = new Person("张三", 12);
            oos.writeObject(per);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
//            Person per_ = (Person) ois.readObject();
//            System.out.println(per_);
            ArrayList list = (ArrayList) ois.readObject();
            list.forEach((s) -> System.out.println(s));
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
