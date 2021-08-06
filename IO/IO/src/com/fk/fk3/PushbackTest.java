package com.fk.fk3;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

public class PushbackTest {

    @Test
    public void test1() {
        try {
            String path = "resources/gushi.txt";
            //创建输入流
            InputStream inputStream = new FileInputStream(path);
            //创建推回输入流,指定输入流和推回缓冲区大小（默认不指定为1）
            PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream, 5);
            byte[] b = new byte[3];
            //先读取1个字节数组的字节
            pushbackInputStream.read(b, 0, 3);
            //输出数组的字节
            System.out.println(new String(b, 0, b.length));
            //将字节数组前2个字节推回到退回缓冲区
            pushbackInputStream.unread(b, 0, 2);
            //再读取1个字节数组的字节
            pushbackInputStream.read(b, 0, 3);
            //输出数组的字节
            System.out.println(new String(b, 0, b.length));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            //创建输出流（节点流）
            OutputStream outputStream = new FileOutputStream("resources/redirectTest.txt");
            //创建处理流
            PrintStream printStream = new PrintStream(outputStream);
            {
                //重定向标准输出流
                System.setOut(printStream);
                //向标准输出输出一个字符串
                System.out.println("重定向标准输出测试");
                //向标准输出输出一个对象
                System.out.println(new PushbackTest());
            }
            printStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            String path = "resources/redirectTest.txt";
            InputStream inputStream = new FileInputStream(path);
            //重定向输入
            System.setIn(inputStream);
            //使用System.in创建标准Scanner对象，使用Scanner获取标准输出
            Scanner scanner = new Scanner(System.in);
            //把回车符作为分隔符
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                //输出
                System.out.println(scanner.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        try {
            //运行javac命令，返回运行该命令的子进程
            Process process = Runtime.getRuntime().exec("javac");
            //创建字节输入流InputStream
            InputStream inputStream = process.getErrorStream();
            //字节转换成字符流InputStreamReader（处理流）
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            //设置字符集为utf-8，解决中文乱码问题
            //创建缓存输入流（处理流）
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            {
                String message = null;
                //循环读取
                while ((message = bufferedReader.readLine()) != null) {
                    System.out.println(message);
                }
            }
            //关闭最上层的流BufferedReader
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        try {
            System.out.println("开始执行 test5");
            //运行java ReadStandard命令，返回运行该命令的子进程
            Process process = Runtime.getRuntime().exec("java ReadStandard");
            System.out.println(process);
            //获取子进程的输出流对象(节点流)
            OutputStream outputStream = process.getOutputStream();
            //使用处理流包装子进程的节点流
            PrintStream printStream = new PrintStream(outputStream);
            {
                //向java ReadStandard程序写入内容，这些内容被java ReadStandard读取
                printStream.println("你好");
                printStream.println("hello");
                printStream.println(new PushbackTest());
            }
            System.out.println("结束执行 test5");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ReadStandard {
    public static void main(String[] args) {
        try {
            System.out.println("开始执行 ReadStandard");
            //使用System.in创建Scanner对象，获取标准输入
            Scanner scanner = new Scanner(System.in);
            //将标准输入写入到out.txt
            PrintStream printStream =
                    new PrintStream(new FileOutputStream("resources/out.txt"));
            {
                //增加下面一行只把回车作为分隔符
                scanner.useDelimiter("\n");
                while (scanner.hasNext()) {
                    printStream.println("程序向子进程输出的内容：" + scanner.next());
                }
            }
            System.out.println("结束执行 ReadStandard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}