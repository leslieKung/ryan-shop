package com.ryan.service;

/**
 * @ClassName MailService
 * @Author Ryan
 * @Date 2026/3/6 17:01
 * @Version 1.0.0
 * @Description 邮件发送接口
 */
public interface MailService {
    void sendMail(String to, String subject, String content);
}
