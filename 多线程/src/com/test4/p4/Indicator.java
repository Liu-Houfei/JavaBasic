package com.test4.p4;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 使用原子变量类定义一个计数器
 * 该计数器在整个过程中都可以使用，并且所有地方都可以使用这个计数器，
 * 这个设计器要设计为单例
 */
public class Indicator {

    //构造方法私有化
    private Indicator() {
    }

    //定义一个私有本类静态对象
    private static final Indicator INSTANCE = new Indicator();

    //提供一个公共静态方法，返回该类的唯一实例
    public static Indicator getInstance() {
        return INSTANCE;
    }

    //记录请求总数
    //使用原子变量类保存请求总数，成功数，失败数
    private final AtomicLong requestCount = new AtomicLong(0);
    private final AtomicLong successCount = new AtomicLong(0);
    private final AtomicLong failedCount = new AtomicLong(0);

    //有新请求
    public void newRequestReceive() {
        requestCount.incrementAndGet();
    }

    //处理成功
    public void requestProcessSuccess() {
        successCount.incrementAndGet();
    }

    //处理失败
    public void requesuProcessFailure() {
        failedCount.incrementAndGet();
    }

    //查看总数，成功数，失败数
    public long getRequestCount() {
        return requestCount.get();
    }

    public long getSuccessCount() {
        return successCount.get();
    }

    public long getFailureCount() {
        return failedCount.get();
    }

}
