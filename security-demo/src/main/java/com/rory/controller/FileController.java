package com.rory.controller;

import com.rory.bean.FileInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
public class FileController {

    private  String folder="E:\\ideaworkplace\\securityStudyWorkplace\\security\\security-demo\\src\\main\\java\\com\\rory\\controller";

    //测试文件上传
    @PostMapping("/file")
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        File localFile=new File(folder,new Date().getTime()+".txt");
        //将文件写到本地 如果想要写到其他服务器 获取其他文件服务服务器 可以通过new File()  然后获取文件的输入流 getInputStream
        file.transferTo(localFile);
        FileInfo fileInfo=new FileInfo();
        fileInfo.setPath(localFile.getAbsolutePath());
        return fileInfo;
    }


    //测试文件下载
    @GetMapping("/file/{id}")
    public void DownLoadFile(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try (
                InputStream inputStream=new FileInputStream(new File(folder,id+".txt"));
                OutputStream outputStream=response.getOutputStream();
        ){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");//指定文件下载的名称为test.txt
            IOUtils.copy(inputStream,outputStream);//将输入流拷贝到输出流里面去
            outputStream.flush();
        }
    }
}
