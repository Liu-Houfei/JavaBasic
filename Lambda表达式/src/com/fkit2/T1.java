package com.fkit2;

//定义一个函数式接口
@FunctionalInterface
interface Convert {
    //该方法实现字符串转成整数
    int convert(String s);
}

public class T1 {
    public static void main(String[] args) {
        // 使用Lambda语句给Convert变量赋值
        //Convert convert1 = (s) -> Integer.valueOf(s);
        // 改进方法：让lambda表达式引用Integer类方法valueOf
        Convert convert1 = Integer::valueOf;
        Integer n1 = convert1.convert("100");
        System.out.println(n1);

        //Convert convert2 = s -> "fkit".indexOf(s);
        Convert convert2 = "fkit"::indexOf;
        Integer n2 = convert2.convert("it");
        System.out.println(n2);
    }
}
