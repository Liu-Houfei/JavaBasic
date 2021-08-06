package com.fk.fk2;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

public class IOTest {

    @Test
    public void test1() {
        try {
            String path = "src/com/fk/fk2/IOTest.java";
            //使用InputStream接口变量引用FileInputStream对象
            InputStream inputStream = new FileInputStream(path);
            //创建一个大小维2014的byte字节数组
            byte[] content = new byte[1024];
            //记录实际读取的字节数
            int count = 0;
            //取字节
//            while ((count = inputStream.read(content)) > 0) {
//                System.out.println(new String(content, 0, count));
//            }
            count = inputStream.read(content);
            System.out.println(new String(content, 0, count));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            String path = "src/com/fk/fk2/IOTest.java";
            Reader reader = new FileReader(path);
            char[] cbuf = new char[1024];
            int count = 0;
            while ((count = reader.read(cbuf)) > 0) {
                System.out.println(new String(cbuf, 0, count));
            }
//            count = reader.read(cbuf);
//            System.out.println(new String(cbuf, 0, count));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            String path = "resources/hello.txt";
            InputStream inputStream = new FileInputStream(path);
            //读取前2个字节，写入到字节数组
            byte[] b = new byte[2];
            int count = 0;
            count = inputStream.read(b);
            System.out.println(new String(b, 0, count));
            //判断当前输入流是否支持mark
            if (inputStream.markSupported()) {
                System.out.println("当前输入流支持mark");
                //在此位置插入mark
                inputStream.mark(count);
            }
            //在读取两个字节
            count = inputStream.read(b);
            System.out.println(new String(b, 0, count));
            //将此流的记录指针移到上次标记的位置
            inputStream.reset();
            //在读取两个字节
            count = inputStream.read(b);
            System.out.println(new String(b, 0, count));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() throws IOException {
        //检查resources/目录下是否存在newFile.txt，没有则创建
        String path = "resources/newFile.txt";
        File file = new File(path);
        if (!file.exists()) {
            if (file.createNewFile())
                System.out.println("创建" + file.getAbsolutePath() + "成功！");
        }
        //将次java文件的内容写入到newFile.txt
        //创建输入流
        InputStream inputStream = new FileInputStream("src/com/fk/fk2/IOTest.java");
        //创建大小为1024的字节数组
        byte[] buf = new byte[1024];
        //记录实际读取的字节数
        int c = 0;
        //创建输出流
        OutputStream outputStream = new FileOutputStream(file);
        //开始从输入流读取数据，输出到输出流
        while ((c = inputStream.read(buf)) > 0) {
            System.out.println("c：" + c);
            // 指定字节数组写入到输出流的开始位置和写入字节个数
            outputStream.write(buf, 0, c);
        }
        //关闭输入流、输出流
        inputStream.close();
        outputStream.close();
    }

    @Test
    public void test5() throws IOException {
        //检查resources/目录下是否存在gushi.txt，没有则创建
        String path = "resources/gushi.txt";
        File file = new File(path);
        if (!file.exists()) {
            if (file.createNewFile())
                System.out.println("创建" + file.getAbsolutePath() + "成功！");
        }
        //创建输出流
        Writer writer = new FileWriter(file);
        {
            writer.write("锦瑟无端五十弦，");
            writer.write("一弦一柱思华年。");
        }
        writer.close();
    }

    @Test
    public void test6() throws FileNotFoundException {
        String path = "resources/hello.txt";
        //创建节点流
        OutputStream outputStream = new FileOutputStream(path);
        //创建处理流
        PrintStream printStream = new PrintStream(outputStream);
        //使用处理流输出方法
        printStream.println(12345);
    }

    @Test
    public void test7() {
        String str = "asdasdasdasdad";
        Reader reader = new StringReader(str);

    }

    @Test
    public void test8() throws IOException {
        //System.in ---> public final static InputStream in = null;
        InputStream inputStream = System.in;
        //将System.in对象转换成InputStreamReader对象（字节流转字符流）(直接读取物理输入的节点流)
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        //将reader节点流包装成BufferedReader处理流
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = null;
        //采用循环方式逐行读取
        while ((line = bufferedReader.readLine()) != null) {
            if (line.equals("exit")) {
                System.exit(1);
            }
            System.out.println(line);
        }

    }


}
