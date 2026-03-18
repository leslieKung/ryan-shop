package com.ryan.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.ryan.config.OSSConfig;
import com.ryan.service.FileService;
import com.ryan.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName FileServiceImpl
 * @Author Ryan
 * @Date 2026/3/18 22:45
 * @Version 1.0.0
 * @Description 使用阿里云OSS实现文件上传
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    private OSSConfig ossConfig;

    @Override
    public String uploadUserAvatar(MultipartFile file) {
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        String bucketName = ossConfig.getBucketName();

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String originalFilename = file.getOriginalFilename();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String folderName = now.format(dateTimeFormatter);
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = "user/" + folderName + "/" + CommonUtil.getUuid() + "/" + extension;

        try {
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, fileName, file.getInputStream());
            if (putObjectResult != null) {
                return "https://" + bucketName + "." + endpoint + "/" + fileName;
            }
        } catch (Exception e) {
            log.error("用户微服务-上传模块-文件上传失败：{}", e);
            throw new RuntimeException(e);
        } finally {
            ossClient.shutdown();
        }
        return null;
    }
}
