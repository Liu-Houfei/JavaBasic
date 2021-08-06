package com.fkit.fk1;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class LockTest {

    @Test
    public void test1() {
        try {
            File file = new File("resources/lock.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            FileChannel fileChannel = fileOutputStream.getChannel();
            {
                //获得非阻塞式的排它锁
                //shared=false 排它锁
                //shared=true 共享锁
                FileLock fileLock = fileChannel.tryLock(0, file.length(), false);
                //程序暂停10秒
                Thread.sleep(20 * 1000);
                //释放锁
                fileLock.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
