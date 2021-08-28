package com.test4.p6;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class SubThread extends Thread {

    private User user;  //要更新的User对象
    //创建更新器
    private AtomicIntegerFieldUpdater<User> updater =
            AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    public SubThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+",start--");
        for (int i = 0; i < 10; i++) {
            updater.getAndIncrement(user);
        }
        System.out.println(Thread.currentThread().getName() + ",end -->" + user.age);
    }
}
