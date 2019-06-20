package com.tl.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Controller
public class FileUploadController {

    @Value("${file.path}")
    private String filePath;

    //进入上传界面
    @RequestMapping("file")
    public String file(){
        return "fileUpload";
    }
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    @ResponseBody
    //MultipartFile 的参数应与ftl界面的表达name值对应
    public String upload(MultipartFile file) throws Exception {
        //拿到文件名称 取到它的后缀名==扩展名 a.jpg--->.jpg
        String extName=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        System.out.println(extName);
        //重新命名上传的文件名+extName
        String fileName = UUID.randomUUID().toString()+extName;
        System.out.println(fileName);
        //进行上传
        FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(new File(filePath+fileName)));
        return fileName+" 上传成功";
    }

}
