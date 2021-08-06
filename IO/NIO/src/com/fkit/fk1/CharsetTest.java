package com.fkit.fk1;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

public class CharsetTest {

    @Test
    public void test1() {
        //获取Java支持的所有字符集
        SortedMap<String, Charset> sortedMap = Charset.availableCharsets();
        //lambda表达式输出sortedMap的k-v
        sortedMap.forEach((k, v) -> System.out.println(k + "  " + v));
    }

    @Test
    public void test2() {
        //创建简体中文对应的GBK字符集对象
        Charset gbk = Charset.forName("GBK");
        //创建gbk对象对应的解码器和编码器
        CharsetDecoder decoder = gbk.newDecoder();
        CharsetEncoder encoder = gbk.newEncoder();
        //创建CharBuffer对象
        CharBuffer charBuffer = CharBuffer.allocate(5);
        //向charBuffer输入
        charBuffer.put('张');
        charBuffer.put('三');
        //输入完毕后flip
        charBuffer.flip();
        try {
            //将charBuffer中的字符序列转换成字节序咧
            ByteBuffer byteBuffer = encoder.encode(charBuffer);
            //遍历字节序列并且解码后输出
            for (int i = 0; i < byteBuffer.capacity(); i++) {
                System.out.println(byteBuffer.get(i) + " ");
            }
            //将字节序列解码成字符序列
            System.out.println(decoder.decode(byteBuffer));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
