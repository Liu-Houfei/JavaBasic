package com.fk.fk4;

import org.junit.Test;

import java.io.*;

public class RandomAccessFileTest {

    @Test
    public void test1() {
        try {
            String path = "resources/newFile.txt";
            File file = new File(path);
            //指定RandomAccessFile访问模式
            String mode = "r";
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, mode);
            {
                //获取RandomAccessFile对象的文件指针的位置，初始位置是0
                System.out.println(randomAccessFile.getFilePointer());
                //移动文件指针的位置
                randomAccessFile.seek(300);
                byte[] buf = new byte[1024];
                //记录实际读取的字节数
                int hasRead = 0;
                //使用循环来读取
                while ((hasRead = randomAccessFile.read(buf, 0, buf.length)) > 0) {
                    System.out.println(new String(buf, 0, hasRead));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            String path = "resources/newFile1.txt";
            File file = new File(path);
            //指定RandomAccessFile访问模式
            String mode = "rw";
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, mode);
            {
                //实现文件追加
                //移动记录指针到最后
                randomAccessFile.seek(file.length());
                randomAccessFile.write("你好a".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * abcdefghijklmnopqrstuvwxyz
     * 1234567890
     */
    @Test
    public void test3() {
        try {
            //指定插入文件
            String fpath = "resources/out.txt";
            //指定插入位置
            long pos = 2;
            //指定插入内容
            String insertContent = "[A]";
            //在文件指定位置插入数据
            String path = "resources/";
            //临时文件存放目录
            File dir = new File(path);
            //在resources/目录下创建临时文件
            File tmp = File.createTempFile("tmptxt", "tmptxt", dir);
            //创建临时文件的IO流（先写后读）
            InputStream inputStream = new FileInputStream(tmp.getAbsoluteFile());
            OutputStream outputStream = new FileOutputStream(tmp.getAbsoluteFile());
            //创建随机读写类RandomAccessFile,mode=rw
            RandomAccessFile randomAccessFile = new RandomAccessFile(fpath, "rw");
            //移动记录指针到指定位置
            randomAccessFile.seek(pos);
            //将插入位置之后的数据输出到临时文件中
            byte[] buf = new byte[64];
            int hasRead = 0;
            while ((hasRead = randomAccessFile.read(buf)) > 0) {
                outputStream.write(buf, 0, hasRead);
            }
            //移动记录指针到指定位置
            randomAccessFile.seek(pos);
            //在指定位置插入指定数据
            randomAccessFile.write(insertContent.getBytes());
            while ((hasRead = inputStream.read(buf)) > 0) {
                randomAccessFile.write(buf, 0, hasRead);
            }
            //关闭所有流
            randomAccessFile.close();
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
