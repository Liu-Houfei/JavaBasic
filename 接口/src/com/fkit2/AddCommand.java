package com.fkit2;

public class AddCommand implements Command {

    @Override
    public void process(int[] target) {
        int sum = 0;
        for (int t : target) {
            sum += t;
        }
        System.out.println("数组元素总和=" + sum);
    }
}
