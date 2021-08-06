package com.fkit;

import java.util.Date;

public class Printer implements Product, Output {
    //接口实现类中使用接口中定义的静态常量MAX_CACHE_LINE
    private String[] printData = new String[MAX_CACHE_LINE];
    private int dataNum = 0;

    @Override
    public void out() {
        //只要有作业就继续打印
        while (dataNum > 0) {
            System.out.println("打印机打印：" + printData[0]);
            //把作业整体前移一位，将剩下的作业数减1
            System.arraycopy(printData, 1, printData, 0, --dataNum);
        }
    }

    @Override
    public void getData(String msg) {
        //判断打印队列是否满
        if (dataNum >= MAX_CACHE_LINE) {
            System.out.println("输出队列满，添加失败");
        } else {
            //队列不满，将打印数据添加到打印队列，任务数加1
            printData[dataNum++] = msg;
        }
    }

    @Override
    public long getProduceTime() {
        return new Date().getTime();
    }
}
