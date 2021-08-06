package com.fkit.fk1;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PathTest {

    @Test
    public void test1() {
        //以当前路径创建path对象
        Path path = Paths.get(".");
        System.out.println(path);
        System.out.println("path里包含路径的数量：" + path.getNameCount());
        System.out.println("path的跟路径：" + path.getRoot());
        //path对应的绝对路径
        Path absolutePath = path.toAbsolutePath();
        System.out.println("path对应的绝对路径" + absolutePath);
        //获取绝对路径的根路径
        System.out.println("绝对路径的跟路径:" + absolutePath.getRoot());
        //获取绝对路径包含的路径数量
        System.out.println("绝对路径包含的路径数量：" + absolutePath.getNameCount());
        //用多个String构建path对象
        Path path2 = Paths.get("g:", "publish", "codes");
        System.out.println(path2);

    }

    @Test
    public void test2() {
        try {
            Path filePath = Paths.get("resources/in.txt");
            File copyFile = new File("resources/copyFile.txt");
            OutputStream outputStream = new FileOutputStream(copyFile);
            //Files.copy() 复制文件
            Files.copy(filePath, outputStream);
            //判断in.txt是否为隐藏文件
            System.out.println(Files.isHidden(filePath));
            //一次性读取所有行
            List<String> lines = Files.readAllLines(filePath, Charset.forName("GBK"));
            //输出所有行
            lines.forEach((s) -> System.out.println(s));
            //判断指定文件的大小
            System.out.println(Files.size(filePath));
            //直接将多个字符串写入到文件中
            List<String> newlines = new ArrayList<>();
            newlines.add("abcdefg");
            newlines.add("1234567");
            Files.write(filePath, newlines, Charset.forName("GBK"));
            //使用Java8新增的Stream API列出当前目录下的所有文件和目录(Stream流访问集合)
            Stream stream = Files.list(Paths.get("resources/"));
            //遍历流中每个元素，对每个元素执行action
            //s是流stream中的每个元素
            stream.forEach((s) -> System.out.println(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() throws IOException {
        //遍历resources目录下的所有文件和子目录
        //第一个参数是开始遍历的path对象
        //第二个参数是FileVistor<?super Path> visitor(是一个接口)，要使用它的实现类
        Files.walkFileTree(Paths.get("resources/"), new SimpleFileVisitor<Path>() {
            //重写访问文件时触发的方法visitFile
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                System.out.println("正在访问：" + file + "文件");
                if (file.endsWith("out.txt")) {
                    System.out.println("已经找到" + file.getFileName() + "文件");
                    return FileVisitResult.CONTINUE;
                }
                return FileVisitResult.CONTINUE;
            }

            //重写访问子目录之前触发的方法 preVisitDirectory
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                System.out.println("正在访问：" + dir + "路径");
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Test
    public void test4() {
        try {
            //获取默认的文件系统对象
            FileSystem fileSystem = FileSystems.getDefault();
            //获取文件系统的WatchService对象
            WatchService watchService = fileSystem.newWatchService();
            //给resources/目录注册监听
            Path path = Paths.get("resources/");
            //第一个参数：要注册的文件监听服务
            //后面的参数：要监听的事件（创建、删除、修改、溢出）
            path.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.OVERFLOW);
            while (true) {
                //一直等待，获取下一个变化事件
                WatchKey watchKey = watchService.take();
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    System.out.println(event.context());
                    System.out.println(event.kind());
                }
                //重设WatchKey
                boolean valid = watchKey.reset();
                //重设失败退出监听
                if (!valid)
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
