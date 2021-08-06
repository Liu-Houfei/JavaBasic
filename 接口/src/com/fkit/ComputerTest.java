package com.fkit;

public class ComputerTest {
    public static void main(String[] args) {
        OutputFactory outFactory = new OutputFactory();
        Computer com = new Computer(outFactory.getOutput());
        com.keyIn("Python");
        com.print();
    }
}
