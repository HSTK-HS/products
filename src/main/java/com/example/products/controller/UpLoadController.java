package com.example.products.controller;

import com.alibaba.fastjson.JSON;
import com.example.products.util.Layui;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xf on 2018/11/15 16:15
 **/
@Controller
@RequestMapping("file")
public class UpLoadController {

    @RequestMapping("/upload")
    @ResponseBody
    public Layui up(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file.getName());
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
        Map<String,String> result = new HashMap<String,String>();
        List list = new ArrayList();
        if (file != null) {
            String myFileName = file.getOriginalFilename();// 文件原名称
            String name = getName(myFileName);
            String ext = ext(myFileName);
            String data = df.format(new Date());
            String filePath =this.getClass().getResource("/").getPath()+"static/upload/"; //保存的路径
            System.out.println(filePath);
            File fileDir=new File(filePath);
            if (!fileDir.exists()) { //如果不存在 则创建
                fileDir.mkdirs();
            }
            String finalName = name+data+"."+ext;
            File imageFile = new File(fileDir, finalName);
            FileOutputStream fops = new FileOutputStream(imageFile);
            // 将上传的文件信息保存到相应的文件目录里
            fops.write(file.getBytes());
            fops.close();
            return Layui.data(list, 1, finalName);
        }else{
            return Layui.data(list, 1, "上传失败");
        }
    }

    public static String ext(String filename) {
        int index = filename.lastIndexOf(".");

        if (index == -1) {
            return null;
        }
        String result = filename.substring(index + 1);
        return result;
    }

    public static String getName(String filename){
        return filename.substring(0, filename.lastIndexOf("."));
    }

}
