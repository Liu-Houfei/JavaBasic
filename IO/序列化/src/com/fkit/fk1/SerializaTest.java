package com.fkit.fk1;


import org.junit.Test;

import java.io.*;

public class SerializaTest {

    @Test
    public void test1() {
        try {
            //Person对象序列化存到磁盘
            //创建输底层出流（节点流）
            String path = "resources/object.txt";
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            //创建实现序列化的输出流（处理流）
            ObjectOutput objectOutput = new ObjectOutputStream(fileOutputStream);
            //创建Person对象
            Person person = new Person("张三", 20);
            //将person对象写入到输出流
            objectOutput.writeObject(person);
            //关闭上层流
            objectOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            String path = "resources/object.txt";
            //创建底层输入节点流
            FileInputStream fileInputStream = new FileInputStream(path);
            //创建上层输入处理流
            ObjectInput objectInput = new ObjectInputStream(fileInputStream);
            //反序列化
            Person person = (Person) objectInput.readObject();
            System.out.println(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            String path = "resources/object.txt";
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            //创建实现序列化的输出流（处理流）
            ObjectOutput objectOutput = new ObjectOutputStream(fileOutputStream);
            //两个Teacher对象引用同一个Person对象
            Person per = new Person("张三", 20);
            Teacher tea1 = new Teacher("北京", per);
            Teacher tea2 = new Teacher("上海", per);
            //将对象写入到输出流进行序列化
            objectOutput.writeObject(per);
            objectOutput.writeObject(tea1);
            objectOutput.writeObject(tea2);
            //验证java序列化机制对同一对象的处理方法
            objectOutput.writeObject(tea1);
            //关闭输出流
            objectOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        try {
            String path = "resources/object.txt";
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInput objectInput = new ObjectInputStream(fileInputStream);
            //读取多个序列化对象时，要按照序列化的顺序
            Person per = (Person) objectInput.readObject();
            Teacher tea1 = (Teacher) objectInput.readObject();
            Teacher tea2 = (Teacher) objectInput.readObject();
            Teacher tea3 = (Teacher) objectInput.readObject();
            //比较
            System.out.println(tea1.getPer() == per);
            System.out.println(tea2.getPer() == per);
            System.out.println(tea3.getPer() == per);
            System.out.println(tea1 == tea3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        try {
            //序列化
            String path = "resources/object.txt";
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            Person per = new Person("张三", 20);
            objectOutputStream.writeObject(per);
            //更改person对象属性值
            per.setName("李四");
            per.setAge(30);
            //不会将更改属性值之后的对象序列化，只输出之前序列化的编号
            objectOutputStream.writeObject(per);
            objectOutputStream.close();

            //反序列化
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
            Person per_1 = (Person) objectInputStream.readObject();
            Person per_2 = (Person) objectInputStream.readObject();
            System.out.println(per_1);
            System.out.println(per_2);
            System.out.println(per == per_1);   //false
            System.out.println(per == per_2);  //false
            System.out.println(per_1 == per_2);  //true
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6() {
        try {
            String path = "resources/object.txt";
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(new FileInputStream(path));
            {
                //设置过滤器setObjectInputFilter
                objectInputStream.setObjectInputFilter((info) -> {
                    System.out.println("执行数据过滤");
                    //获取过滤器实例
                    ObjectInputFilter serialFilter = ObjectInputFilter.Config.getSerialFilter();
                    if (serialFilter != null) {
                        //首先使用ObjectInputFilter的默认检查
                        ObjectInputFilter.Status status = serialFilter.checkInput(info);
                        //
                        if (status != ObjectInputFilter.Status.UNDECIDED) {
                            //直接返回检测结果
                            return status;
                        }
                    }
                    //如果要恢复的对象不是1个
                    if (info.references() != 1) {
                        //不允许恢复对象
                        return ObjectInputFilter.Status.REJECTED;
                    }
                    //如果恢复的不是Person类
                    if (info.serialClass() != null && info.serialClass() != Person.class) {
                        //不允许恢复
                        return ObjectInputFilter.Status.REJECTED;
                    }
                    return ObjectInputFilter.Status.UNDECIDED;
                });
                Person p = (Person) objectInputStream.readObject();
                System.out.println(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test7() {
        try {
            String path = "resources/object.txt";
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            Person per = new Person("张三", 12);
            Teacher tea1 = new Teacher("吉林", per);
            oos.writeObject(per);
            oos.writeObject(tea1);
            oos.close();
            Person per_ = (Person) ois.readObject();
            Teacher tea1_ = (Teacher) ois.readObject();
            System.out.println(per_);
            System.out.println(tea1_);
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
