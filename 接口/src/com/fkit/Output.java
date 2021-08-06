package com.fkit;

public interface Output {
    //接口中定义常量
    public static final int MAX_CACHE_LINE = 10;
    //接口中定义普通方法
    public void out();
    public void getData(String msg);
    //接口中定义默认方法
    public default void print(String...msgs){
        for(String msg:msgs){
            System.out.println(msg);
        }
    }
    public default void test(){
        System.out.println("接口Output默认方法test");
    }
    //接口中定义类方法
    public static String staticTest(){
        return "接口中的类方法";
    }
//    //接口中定义私有方法,Java9新增
//    private void foo(){
//        System.out.println("接口中的私有foo方法");
//    }
//    //接口中定义私有静态方法，Java9新增
//    private static void bar(){
//        System.out.println("接口中的私有静态方法bar");
//    }


}
