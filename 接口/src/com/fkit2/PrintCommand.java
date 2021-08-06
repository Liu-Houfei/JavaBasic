package com.fkit2;

public class PrintCommand implements Command {
    @Override
    public void process(int[] target) {
        //输出数组的每个元素
        for (int t : target) {
            System.out.println(t);
        }
    }
}
