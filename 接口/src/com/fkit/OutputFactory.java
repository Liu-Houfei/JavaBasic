package com.fkit;

//简单工厂模式
public class OutputFactory {
    public Output getOutput() {
        //return new Printer();
        return new BetterPrinter();
    }
}
