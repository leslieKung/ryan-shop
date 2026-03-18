package com.ryan.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileService
 * @Author Ryan
 * @Date 2026/3/18 22:45
 * @Version 1.0.0
 * @Description // TODO:
 */
public interface FileService {
    String uploadUserAvatar(MultipartFile file);
}
