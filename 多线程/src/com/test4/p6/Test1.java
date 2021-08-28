package com.test4.p6;

import org.junit.Test;

public class Test1 {

    @Test
    public void test1() throws InterruptedException {
        User user = new User(10010, 10);
        for (int i = 0; i < 10; i++) {
            new SubThread(user).start();
        }
        Thread.sleep(10000);
    }
}
