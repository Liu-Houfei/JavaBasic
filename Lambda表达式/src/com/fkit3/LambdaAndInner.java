package com.fkit3;

@FunctionalInterface
interface Displayable {
    void display();

    default int add(int a, int b) {
        return a + b;
    }
}

public class LambdaAndInner{

}
