package com.fkit.fk3;

import org.junit.Test;

import java.io.*;

public class ResolveTest {
    @Test
    public void test1() {
        try {
            String path = "resources/transient.txt";
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            //序列化枚举对象：Orientation.HORIZONTAL
            oos.writeObject(Orientation.HORIZONTAL);
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            Orientation ori = (Orientation) ois.readObject();
            System.out.println(ori == Orientation.HORIZONTAL);  //true
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        Externalizable externalizable;

    }
}
