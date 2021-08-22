package com.fkit.fk1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class ListTest {

    @Test
    public void test1() {
        //创建List集合
        List books = new ArrayList();
        books.add("西游记");
        books.add(new String("水浒传"));
        System.out.println(books);
        //在List集合index=1的位置插入新对象
        books.add(1, "红楼梦");
        //获取List集合index=1的对象
        System.out.println(books.get(1));
        //删除index=2的对象
        Object obj = books.remove(2);
        System.out.println(books);
        //判断指定对象在集合中位置
        int index = books.indexOf("西游记");
        System.out.println(index);
        //将index=1的对象替换成新对象
        Object obj1 = books.set(1, "java讲义");
        System.out.println(books);
        //获取子集合
        List newList = books.subList(1, 2);
        System.out.println(books);
        System.out.println(newList);
    }

    @Test
    public void test2() {
        List books = new ArrayList();
        books.add("西游记");
        books.add(new String("水浒传"));
        books.add(new String("三国"));
        books.add(new String("红楼梦"));
        System.out.println(books);
        //删除A对象
        books.remove(new A());
        System.out.println(books);
        books.remove(new A());
        System.out.println(books);
    }

    @Test
    public void test3() {
        List books = new ArrayList();
        books.add(new String("西游记"));
        books.add(new String("水浒传"));
        books.add(new String("三国"));
        books.add(new String("红楼梦"));
        System.out.println(books);
        /**
         * replaceAll源码：
         *     default void replaceAll(UnaryOperator<E> operator) {
         *         Objects.requireNonNull(operator);
         *         final ListIterator<E> li = this.listIterator();
         *         while (li.hasNext()) {
         *             li.set(operator.apply(li.next()));
         *         }
         *     }
         */
        //Lambda：使用每个字符串的长度作替换集合中的所有元素
        books.replaceAll((s) -> {
            return ((String) s).length();
        });
        System.out.println(books);
        //匿名接口实现类：使用每个字符串的长度作替换集合中的所有元素
        books.replaceAll(new UnaryOperator() {
            @Override
            public Object apply(Object o) {
                return ((Integer)o)+1;
            }
        });
        System.out.println(books);
    }
}
