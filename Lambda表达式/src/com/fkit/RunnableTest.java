package com.fkit;

public class RunnableTest {

    public static void main(String[] args) {
        //使用lambda表达式实现Runnable接口的 public abstract void run();
        Runnable r = () -> {
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            System.out.println(sum);
        };
        r.run();

        r =()->{
            System.out.println("函数式接口");
        };
        r.run();

        Object obj = (Runnable)()->{
            System.out.println("");
        };
        Object obj1 = r;

    }
}
