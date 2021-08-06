package com.fkit.fk1;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;

public class FileTest {

    @Test
    public void test1() throws IOException {
        //获取要操作文件的路径
        Path path = Paths.get("resources/out.txt");
        //获取访问文件基本属性的BasicFileAttributeView
        BasicFileAttributeView basicFileAttributeView =
                Files.getFileAttributeView(path, BasicFileAttributeView.class);
        //获取访问基本属性的BasicFileAttributes
        BasicFileAttributes basicFileAttributes = basicFileAttributeView.readAttributes();
        //访问文件基本属性
        System.out.println("创建时间：" + basicFileAttributes.creationTime());
        System.out.println("最后修改时间：" + basicFileAttributes.lastModifiedTime());
        System.out.println("最后访问时间：" + basicFileAttributes.lastAccessTime());
        System.out.println("文件大小：" + basicFileAttributes.size());
        //获取访问文件所有者的FileOwnerAttributeView对象
        FileOwnerAttributeView fileOwnerAttributeView =
                Files.getFileAttributeView(path, FileOwnerAttributeView.class);
        //获取该文件所属的用户
        System.out.println(fileOwnerAttributeView);
        //获取系统中gust对应的用户
        UserPrincipal userPrincipal = FileSystems.getDefault().
                getUserPrincipalLookupService().
                lookupPrincipalByName("gust");
        //修改用户
        fileOwnerAttributeView.setOwner(userPrincipal);
    }
}
