package com.wsl.shoppingKill.component.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.wsl.shoppingKill.constant.FileNameSuffixEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * 阿里云 OSS 工具类
 *
 * @author caibenhao
 */
@Component
public class OssComponent {
    
    protected static final Logger log = LoggerFactory.getLogger(OssComponent.class);
    
    /**
     * OSS配置信息
     */
    @Value("${ali.oss.endpoint}")
    private String endpoint;
    @Value("${ali.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${ali.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${ali.oss.bucketName}")
    private String bucketName;


    /**
     * URL配置
     */
    @Value("${ali.oss.url}")
    private String myUrl;
    @Value("${ali.oss.aliUrl}")
    private String aliUrl;
    
    /* -----------------对外功能---------------- */
    
    /**
     * 单个文件上传
     *
     * @param file 文件
     * @return 返回完整URL地址
     */
    public String uploadFile(String fileDir,MultipartFile file) {
        String fileUrl = uploadImg2Oss(fileDir,file);
        String str = getFileUrl(fileDir,fileUrl);
        return str.trim();
    }
    
    /**
     * 单个文件上传(指定文件名（带后缀）)
     *
     * @param file     文件
     * @param fileName 文件名(带后缀)
     * @return 返回完整URL地址
     */
    public String uploadFile(String fileDir,MultipartFile file, String fileName) {
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile2Oss(fileDir,inputStream, fileName);
            String url = getFileUrl(fileDir,fileName);
            if (url != null && url.length() > 0) {
                return url;
            }
            return "URL获取失败";
        } catch (IOException e) {
            return "上传失败";
        }
    }
    
    /**
     * 多文件上传
     *
     * @param fileList 文件列表
     * @return 返回完整URL，逗号分隔
     */
    public String uploadFile(String fileDir,List<MultipartFile> fileList) {
        String fileUrl;
        String str;
        StringBuilder photoUrl = new StringBuilder();
        for (int i = 0; i < fileList.size(); i++) {
            fileUrl = uploadImg2Oss(fileDir,fileList.get(i));
            str = getFileUrl(fileDir,fileUrl);
            if (i == 0) {
                photoUrl = new StringBuilder(str);
            } else {
                photoUrl.append(",").append(str);
            }
        }
        return photoUrl.toString().trim();
    }
    
    public boolean deleteFile(String fileDir,String fileName) {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 删除文件
        ossClient.deleteObject(bucketName, fileDir + fileName);
        // 判断文件是否存在
        boolean found = ossClient.doesObjectExist(bucketName, fileDir + fileName);
        // 如果文件存在则删除失败
        return !found;
    }
    
    /**
     * 通过文件名获取文完整件路径
     *
     * @param fileUrl 文件名
     * @return 完整URL路径
     */
    public String getFileUrl(String fileDir,String fileUrl) {
        if (fileUrl != null && fileUrl.length() > 0) {
            String[] split = fileUrl.split("/");
            String url = this.getUrl(fileDir + split[split.length - 1]);
            return Objects.requireNonNull(url);
        }
        return null;
    }
    
    /* -----------内部辅助功能------------------------ */
    
    /**
     * 获取去掉参数的完整路径
     *
     * @param url URL
     * @return 去掉参数的URL
     */
    private String getShortUrl(String url) {
        String[] imgUrls = url.split("\\?");
        return imgUrls[0].trim();
    }
    
    /**
     * 获得url链接
     *
     * @param key 文件名
     * @return URL
     */
    private String getUrl(String key) {
        // 设置URL过期时间为20年  3600l* 1000*24*365*20
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 20);
        // 生成URL
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            String replaceUrl = url.toString()
                    .replace(aliUrl, myUrl);
            return getShortUrl(replaceUrl);
        }
        return null;
    }
    
    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件名
     */
    private String uploadImg2Oss(String fileDir,MultipartFile file) {
        // 1、限制最大文件为20M
        int maxFileSize = 1024 * 1024 * 20;
        if (file.getSize() > maxFileSize) {
            return "图片太大";
        }
        // 2、重命名文件
        String fileName = Objects.requireNonNull(file.getOriginalFilename(), "文件名不能为空");
        // 文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase(Locale.ENGLISH);
        String uuid = UUID.randomUUID().toString();
        String name = uuid + suffix;
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile2Oss(fileDir,inputStream, name);
            return name;
        } catch (Exception e) {
            return "上传失败";
        }
    }
    
    /**
     * 上传文件（指定文件名）
     *
     * @param inputStream 输入流
     * @param fileName    文件名
     */
    private void uploadFile2Oss(String fileDir,InputStream inputStream, String fileName) {
        String ret;
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            PutObjectResult putResult = ossClient.putObject(bucketName, fileDir + fileName, inputStream, objectMetadata);
            ret = putResult.getETag();
            if (StringUtils.isEmpty(ret)) {
                log.error("上传失败，文件ETag为空");
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static String getContentType(String filenameExtension) {
        if (FileNameSuffixEnum.BMP.getSuffix().equalsIgnoreCase(filenameExtension)) {
            return "image/bmp";
        }
        if (FileNameSuffixEnum.GIF.getSuffix().equalsIgnoreCase(filenameExtension)) {
            return "image/gif";
        }
        if (FileNameSuffixEnum.JPEG.getSuffix().equalsIgnoreCase(filenameExtension) ||
                FileNameSuffixEnum.JPG.getSuffix().equalsIgnoreCase(filenameExtension) ||
                FileNameSuffixEnum.PNG.getSuffix().equalsIgnoreCase(filenameExtension)) {
            return "image/jpeg";
        }
        if (FileNameSuffixEnum.HTML.getSuffix().equalsIgnoreCase(filenameExtension)) {
            return "text/html";
        }
        if (FileNameSuffixEnum.TXT.getSuffix().equalsIgnoreCase(filenameExtension)) {
            return "text/plain";
        }
        if (FileNameSuffixEnum.VSD.getSuffix().equalsIgnoreCase(filenameExtension)) {
            return "application/vnd.visio";
        }
        if (FileNameSuffixEnum.PPTX.getSuffix().equalsIgnoreCase(filenameExtension) ||
                FileNameSuffixEnum.PPT.getSuffix().equalsIgnoreCase(filenameExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (FileNameSuffixEnum.DOCX.getSuffix().equalsIgnoreCase(filenameExtension) ||
                FileNameSuffixEnum.DOC.getSuffix().equalsIgnoreCase(filenameExtension)) {
            return "application/msword";
        }
        if (FileNameSuffixEnum.XML.getSuffix().equalsIgnoreCase(filenameExtension)) {
            return "text/xml";
        }
        if (FileNameSuffixEnum.PDF.getSuffix().equalsIgnoreCase(filenameExtension)) {
            return "application/pdf";
        }
        return "image/jpeg";
    }
}
