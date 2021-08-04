package com.study.utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * @program: java-pdf-demo
 * @description: 文件处理工具类
 * @author: 唐嘉
 * @create: 2019-12-19 17:26
 */
public class FileUtils {
    //返回文件后缀
    public static String getPostfix(MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                int begin = file.getOriginalFilename().lastIndexOf(".");
                int last = file.getOriginalFilename().length();
                String a = file.getOriginalFilename().substring(begin + 1, last);
                return a;
            }
        } catch (Exception e) {

        }
        return "";
    }

    //返回文件名除后缀
    public static String getNameElsePostfix(MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                int begin = 0;
                int last = file.getOriginalFilename().lastIndexOf(".");
                String a = file.getOriginalFilename().substring(begin, last);
                return a;
            }
        } catch (Exception e) {

        }
        return "";
    }
}
