package com.fkit.fk1;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ChannelTest {
    @Test
    public void test1() {
        try {
            File infile = new File("resources/in.txt");
            File outfile = new File("resources/out.txt");
            //以文件输入流创建FileChannel
            FileChannel inChannel = new FileInputStream(infile).getChannel();
            //以文件输出流创建FileChannel
            FileChannel outChannel = new FileOutputStream(outfile).getChannel();
            //将inChannel中的全部数据映射成MappedByteBuffer
            MappedByteBuffer byteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY,
                    0, infile.length());
            //直接将Buffer中的数据全部输出
            outChannel.write(byteBuffer);

            //调用clear方法，复原position和limit位置
            byteBuffer.clear();
            //使用GBK字符集创建解码器
            Charset charset = Charset.forName("GBK");
            //创建解码器CharsetDecoder对象
            CharsetDecoder decoder = charset.newDecoder();
            //使用解码器将ByteBuffer转换成CharBuffer
            CharBuffer charBuffer = decoder.decode(byteBuffer);
            //charBuffer的toString方法获取字符串
            System.out.println(charBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            File f = new File("resources/f.txt");
            //以RandomAccessFile输入输出流创建fileChannel对象
            FileChannel fileChannel = new RandomAccessFile(f, "rw").getChannel();
            {
                //将Channel中的所有数据映射成ByteBuffer
                MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,
                        0, f.length());
                //把position指针移到最后（追加效果）
                fileChannel.position(f.length());
                //将buffer中所有数据输出
                fileChannel.write(mappedByteBuffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            File f = new File("resources/f.txt");
            //创建文件输入流
            FileInputStream fileInputStream = new FileInputStream(f);
            //以文件输入流创建Channel对象
            FileChannel fileChannel = fileInputStream.getChannel();
            //创建一个capacity=3的Buffer
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(5);
            //read方式多次读取
            while (fileChannel.read(byteBuffer) != -1) {
                //处理byteBuffer中的数据
                System.out.println("当前buffer中的position：" + byteBuffer.position());
                //输入完之后要用flip方法锁定空白区，并让position=0（准备输出）
                byteBuffer.flip();
                //创建解码器解码byte数据
                Charset charset = Charset.forName("GBK");
                CharsetDecoder charsetDecoder = charset.newDecoder();
                //将byteBuffer转换成charBuffer
                CharBuffer charBuffer = charsetDecoder.decode(byteBuffer);
                //输出charBuffer
                System.out.println(charBuffer.toString());
                //输出完之后，要调用clear方法重置buffer
                byteBuffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
