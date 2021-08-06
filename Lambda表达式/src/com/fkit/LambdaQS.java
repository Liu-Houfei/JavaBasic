package com.fkit;

interface Eatable {
    void taste();
}

class EatableImpl implements Eatable {
    @Override
    public void taste() {
        System.out.println("接口Eatable实现类Eatable：苹果好吃！");
    }
}

interface Flyable {
    void fly(String weather);
}

interface Addable {
    int add(int a, int b);
}

public class LambdaQS {
    public void eat(Eatable e) {
        System.out.println("参数名称：" + e.getClass().getName() + "，参数地址：" + e);
        e.taste();
    }

    public void drive(Flyable f) {
        System.out.println("驾驶" + f);
        f.fly("天空");
    }

    public void test(Addable add) {
        System.out.println("5+3=" + add.add(5, 3));
    }

    public static void main(String[] args) {
        LambdaQS lq = new LambdaQS();
        //使用接口实现类方法
        lq.eat(new EatableImpl());
        //lambda1,只有一条语句可以省略花括号
        lq.eat(() -> System.out.println("Lambda语句：苹果好吃！"));
        //使用匿名类方法
        lq.eat(new Eatable() {
            @Override
            public void taste() {
                System.out.println("匿名类方法：苹果好吃！");
            }
        });
        //lambda2，形参只有一个可以省略括号
        lq.drive(s -> {
            System.out.println("今天天气：" + s);
            System.out.println("直升机飞行");
        });
        //lambda3，代码块只有一条语句，return可以省略
        lq.test((a, b) -> a + b);
    }
}
