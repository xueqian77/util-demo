package com.study.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.study.config.OSSConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @program: java-pdf-demo
 * @description: OSS文件对象存储服务
 * @author: 唐嘉
 * @create: 2020-05-07 09:32
 **/
public class OSSUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(OSSUtils.class);

    private static final ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();

    static {
        LOGGER.info("initialize ClientBuilderConfiguration");
    }

    /**
     * 获取oss 客户端 自定义配置
     *
     * @param config oss连接对象
     * @return OSS oss客户端
     */
    public static OSS getClient(OSSConfig config, ClientBuilderConfiguration conf) {
        try {
            return new OSSClientBuilder().build(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret(), conf);
        } catch (Exception e) {
            LOGGER.info("oss client open faild" + e);
            return null;
        }
    }

    /**
     * 获取oss 客户端 默认配置
     *
     * @param config oss连接对象
     * @return OSS oss客户端
     */
    public static OSS getClient(OSSConfig config) {
        try {
            return new OSSClientBuilder().build(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret(), clientBuilderConfiguration);
        } catch (Exception e) {
            LOGGER.info("oss client open faild" + e);
            return null;
        }
    }

    /**
     * oss 连接断开
     *
     * @param oss 客户端
     */
    public static void close(OSS oss) {
        try {
            oss.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * oss上传单个文件
     *
     * @param oss       oss客户端
     * @param file      文件流
     * @param directory 目标地址
     * @param newName   保存后名字
     * @return result   是否成功
     */
    public static boolean upload(OSS oss, MultipartFile file, String directory, String newName, String bucketName) {
        boolean result = false;
        try {
            //获取文件流
            InputStream inputStream = file.getInputStream();
            //获取文件全名
            if (newName == null) {
                newName = file.getOriginalFilename();//默认名字
            } else {
                newName = newName + "." + FileUtils.getPostfix(file);//主键名
            }
            oss.putObject(bucketName, directory + newName, inputStream);
            result = true;
        } catch (Exception e) {
            LOGGER.info("上传失败", e.toString());
        }
        return result;
    }


    /**
     * 获取oss的文件流
     *
     * @param oss       oss客户端
     * @param directory 目标地址
     * @return config 配置文件
     */
    public static byte[] download(OSS oss, String directory, String bucketName) {
        byte[] body = null;
        OSSObject ossObject = null;
        try {
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            ossObject = oss.getObject(bucketName, directory);
            // 读取文件内容。
            InputStream is = ossObject.getObjectContent();
            body = TypeConversionUtils.inputStremTobyte(is);
            is.read(body);
            is.close();
            return body;
        } catch (Exception e) {
            LOGGER.info("下载失败" + e.toString());
        } finally {
            if (ossObject != null) {
                try {
                    ossObject.close();
                } catch (IOException e) {
                    LOGGER.info("关闭ossObject失败" + e.toString());
                }
            }
        }
        return body;
    }

    /**
     * 获取oss的InputStream
     *
     * @param oss       oss客户端
     * @param directory 目标地址
     * @return config 配置文件
     */
    public static InputStream getInputStream(OSS oss, String directory, String bucketName) {
        InputStream inputStream = null;
        OSSObject ossObject = null;
        try {
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            ossObject = oss.getObject(bucketName, directory);
            // 读取文件内容。
            inputStream = ossObject.getObjectContent();
        } catch (Exception e) {
            LOGGER.info("下载失败" + e.toString());
        } finally {
            if (ossObject != null) {
                try {
                    ossObject.close();
                } catch (IOException e) {
                    LOGGER.info("关闭ossObject失败" + e.toString());
                }
            }
        }
        return inputStream;
    }

    /**
     * 删除oss文件
     *
     * @param oss       oss客户端
     * @param directory 目标地址
     * @return config 配置文件
     */
    public static boolean delete(OSS oss, String directory, String bucketName) {
        try {
            // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
            oss.deleteObject(bucketName, directory);
            return true;
        } catch (Exception e) {
            LOGGER.info("删除失败：" + e.toString());
        }
        return false;
    }

    /**
     * 批量下载文件
     */
    public static byte[] zipFilesDown(Long Id, OSS oss, String bucketName, List<Map<String, String>> directorys) {
        byte[] body = new byte[1024];
        try {
            // 创建临时文件
            File zipFile = File.createTempFile(Id.toString(), ".zip");
            FileOutputStream f = new FileOutputStream(zipFile);
            /**
             * 作用是为任何OutputStream产生校验和
             * 第一个参数是制定产生校验和的输出流，第二个参数是指定Checksum的类型 （Adler32（较快）和CRC32两种）
             */
            CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
            // 用于将数据压缩成Zip文件格式
            ZipOutputStream zos = new ZipOutputStream(csum);


            for (Map<String, String> map : directorys) {
                // 获取Object，返回结果为OSSObject对象
                OSSObject ossObject = oss.getObject(bucketName, map.get("directory"));
                // 读去Object内容  返回
                InputStream inputStream = ossObject.getObjectContent();
                // 对于每一个要被存放到压缩包的文件，都必须调用ZipOutputStream对象的putNextEntry()方法，确保压缩包里面文件不同名
                zos.putNextEntry(new ZipEntry(map.get("name")));
                int bytesRead = 0;
                // 向压缩文件中输出数据
                while ((bytesRead = inputStream.read()) != -1) {
                    zos.write(bytesRead);
                }
                inputStream.close();
                zos.closeEntry(); // 当前文件写完，定位为写入下一条项目
            }
            zos.close();

            FileInputStream fis = new FileInputStream(zipFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int n;
            while ((n = fis.read(body)) != -1) {
                bos.write(body, 0, n);
            }
            fis.close();
            bos.close();
            body = bos.toByteArray();

            // 删除临时文件
            zipFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }
}
