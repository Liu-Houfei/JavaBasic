package com.fkit2;

public class ProcessArray {
    /**
     * 行为的具体实现无法确定，只能等到具体执行(main方法测试)时才确定用什么方法
     * 使用Command接口作为参数，等到具体执行时传入具体的操作类对象
     */
    public void process(int[] target, Command cmd) {
        cmd.process(target);
    }
}
