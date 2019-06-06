package com.rory;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.annotate.JsonTypeResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ComomUtilTest {


    //复制目录
    @Test
    public void copyFolder() throws IOException {
        //如果拷贝的目录是它的夫目录 就会报错  包文件已存在
        try {
            FileUtils.copyDirectoryToDirectory(new File("E:\\test1"),new File("E:\\"));
        } catch (IOException e) {
            //e.printStackTrace();
            FileUtils.copyDirectoryToDirectory(new File("E:\\test1"),new File("E:\\test2"));
        }
    }

    //复制文件
    @Test
    public void copyFile() throws IOException {
        //如果文件不存在 会报文件不存在错误
        FileUtils.copyFile(new File("E:\\test.txt"),new File("E:\\test1/testaa.txt"));
    }

    //写入文件
    @Test
    public void writeStringToFile()throws IOException {
        //如果文件不存在 则自动创建文件
        FileUtils.writeStringToFile(new File("E:\\test.txt"),"sujinquan love juan");
    }

    //读取文本
    @Test
    public void readFileToString() throws IOException {
        //文件不存在会报错
     String test=FileUtils.readFileToString(new File("E:\\test.txt"));
     System.out.println(test);
    }



}
