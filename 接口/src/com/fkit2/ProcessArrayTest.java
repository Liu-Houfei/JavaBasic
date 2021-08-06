package com.fkit2;

public class ProcessArrayTest {
    public static void main(String[] args) {
        ProcessArray psa = new ProcessArray();
        int[] target = {1, 7, 1, 12, 23, 123, 123};
        psa.process(target, new PrintCommand());
        psa.process(target, new AddCommand());
    }
}
