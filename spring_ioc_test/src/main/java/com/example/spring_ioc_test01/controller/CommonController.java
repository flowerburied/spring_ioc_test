package com.example.spring_ioc_test01.controller;


import com.example.spring_ioc_test01.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传和下载
 */
@RestController
@RequestMapping("/common")
@Slf4j

public class CommonController {

    //获取到配置类中的路径
    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    //MultipartFile spring封装的上传类 file指定的名称
    public R<String> upload(MultipartFile file) {
//       当前的file是一个临时文件（tmp格式存放），需要转存到指定位置，否则本次请求完成后临时文件就会删除
        log.info(file.toString());

//原始文件名
        String originalFilename = file.getOriginalFilename();
//获取文件后缀  如：.jpg
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));


//        使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
        String filename = UUID.randomUUID().toString() + suffix;


//        创建一个目录对象
        File dir = new File(basePath);
        if (!dir.exists()) {
//    目录不存在，需要创建目录
            dir.mkdirs();
        }

        try {
//            将临时文件转存到指定位置
            file.transferTo(new File(basePath + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.success(filename);
    }

    /**
     * 文件下载
     *
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {


        try {
            //        输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

//            输出流，通过输出流将文件写回游览器，在游览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");


            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {

                outputStream.write(bytes, 0, len);
                outputStream.flush();

            }

            //关闭资源
            outputStream.close();
            fileInputStream.close();

        } catch (Exception e) { //FileNotFoundException 局部   Exception 全部
            e.printStackTrace();
        }


    }

}