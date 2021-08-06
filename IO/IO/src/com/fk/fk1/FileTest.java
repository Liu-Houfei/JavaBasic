package com.fk.fk1;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileTest {


    @Test
    public void test1() {
        String path = "src/3.text";
        File file = new File(path);
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParent());
        System.out.println(file.renameTo(new File("3.text")));
    }

    @Test
    public void test2() {
        String path = "resources/1.text";
        File file = new File(path);
        System.out.println(file.exists());
        System.out.println(file.canWrite());
        System.out.println(file.canRead());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        System.out.println(file.isAbsolute());
    }

    @Test
    public void test3() {
        String path = "resources/1.text";
        File file = new File(path);
        System.out.println(new Date(file.lastModified()));
        System.out.println(file.length());
    }

    @Test
    public void test4() throws IOException {
        String path = "resources/new3.text";
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("文件不存在，创建新文件");
            file.createNewFile();
        }
        if (file.delete()) {
            System.out.println("文件已删除");
        }
        File newTmpFile1 = File.createTempFile("test", "mail");
        System.out.println(newTmpFile1.getAbsolutePath());

        File newTmpFile2 = File.createTempFile("test", "phone",
                new File("resources/"));
        System.out.println(newTmpFile2.getAbsolutePath());

        newTmpFile1.deleteOnExit();
    }

    @Test
    public void test5() {
        String path = "resources/";
        File file = new File(path + "myDir/");
        file.mkdir();
        System.out.println(file.getAbsolutePath());
        File dir = new File(path);
        String[] dirsStr = dir.list();
        File[] dirsFile = dir.listFiles();
        File[] roots = File.listRoots();
        for (String s : dirsStr)
            System.out.println(s);
        for (File e : dirsFile)
            System.out.println(e.getAbsolutePath());
        for (File e : roots)
            System.out.println(e.getAbsolutePath());
    }

    @Test
    public void test6() {
        FileFilter fileFilter;
        FilenameFilter filenameFilter;
        String path = "resources/";
        File file = new File(path);
        //筛选出以.text为后缀的文件
        // public String[] list(FilenameFilter filter)
        //filter.accept(this, names[i])
        //简单调用lambda表达式
        String[] files_string1 = file.list((dir_, name_) -> name_.endsWith(".text"));
        for (String s : files_string1)
            System.out.println(s);

        file.list((dir_, name_) -> {
            System.out.println("当前文件：" + name_);
            if (!dir_.getName().equals("resources")) {
                System.out.println("不在resources目录下，不进行筛选！");
                return false;
            } else {
                if (name_.endsWith(".text") || name_.length() < 5) {
                    System.out.println("筛选结果：" + name_);
                    return true;
                } else {
                    System.out.println("未通过筛选！");
                    return false;
                }
            }
        });
    }

    @Test
    public void test7() {
        String path = "resources/";
        File file = new File(path);
        // public String[] list(FilenameFilter filter)
        //使用匿名接口实现类实现accept方法
        file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                System.out.println("当前文件：" + name);
                if (!dir.getName().equals("resources")) {
                    System.out.println("不在resources目录下，不进行筛选！");
                    return false;
                } else {
                    if (name.endsWith(".text") || name.length() < 5) {
                        System.out.println("筛选结果：" + name);
                        return true;
                    } else {
                        System.out.println("未通过筛选！");
                        return false;
                    }
                }
            }
        });
    }
/*    public String[] list(FilenameFilter filter) {
        String names[] = list();
        if ((names == null) || (filter == null)) {
            return names;
        }
        List<String> v = new ArrayList<>();
        for (int i = 0 ; i < names.length ; i++) {
            if (filter.accept(this, names[i])) {
                v.add(names[i]);
            }
        }
        return v.toArray(new String[v.size()]);
    }*/

    @Test
    public void test8() {
        String path = "resources/";
        File file = new File(path);
        //使用Lambda表达式完成筛选逻辑
        /**
         * listFiles方法源码：
         *     public File[] listFiles(FileFilter filter) {
         *         String ss[] = list();
         *         if (ss == null) return null;
         *         ArrayList<File> files = new ArrayList<>();
         *         for (String s : ss) {
         *             File f = new File(s, this);
         *             if ((filter == null) || filter.accept(f))
         *                 files.add(f);
         *         }
         *         return files.toArray(new File[files.size()]);
         *     }
         */
        file.listFiles(f -> {
            System.out.println("当前文件:" + f.getAbsolutePath());
            if(f.getName().endsWith(".text")){
                System.out.println("符合筛选条件："+f.getName());
                return true;
            }else{
                System.out.println("不符合筛选条件："+f.getName());
                return false;
            }
        });
    }

    @Test
    public void test9() {
        String path = "resources/";
        File file = new File(path);
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                System.out.println("当前文件:" + pathname.getAbsolutePath());
                if(pathname.getName().endsWith(".text")){
                    System.out.println("符合筛选条件："+pathname.getName());
                    return true;
                }else{
                    System.out.println("不符合筛选条件："+pathname.getName());
                    return false;
                }
            }
        });
    }



}
