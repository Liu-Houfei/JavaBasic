package com.fkit.fk1;

import org.junit.Test;

import java.nio.CharBuffer;

public class BufferTest {

    @Test
    public void test1() {
        //创建Buffer
        CharBuffer charBuffer = CharBuffer.allocate(8);
        //获取Buffer的capacity
        System.out.println(charBuffer.capacity());  //8
        //获取Buffer的limit
        System.out.println(charBuffer.limit());   //8
        //获取Buffer的position
        System.out.println(charBuffer.position());  //0

        //放入元素
        charBuffer.put('a');
        charBuffer.put('b');
        charBuffer.put('c');
        //获取加入3个元素后的position
        System.out.println(charBuffer.position());   //3

        //调用flip方法，让limit=position，position=0
        charBuffer.flip();
        //获取Buffer的limit
        System.out.println(charBuffer.limit());  //3
        //获取Buffer的position
        System.out.println(charBuffer.position());  //0

        //取出第一个元素
        System.out.println(charBuffer.get());  //a
        //获取取出1个元素后的position
        System.out.println(charBuffer.position());  //1

        //调用clear方法，让limit=capacity，position=0
        charBuffer.clear();
        //获取Buffer的limit
        System.out.println(charBuffer.limit());  //8
        //获取Buffer的position
        System.out.println(charBuffer.position());  //0

        //执行绝对读取
        System.out.println(charBuffer.get(2));  //c
        //获取Buffer的position
        System.out.println(charBuffer.position());  //0
    }
}
