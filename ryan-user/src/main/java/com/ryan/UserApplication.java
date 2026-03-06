package com.ryan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName UserApplication
 * @Author Ryan
 * @Date 2026/3/4 22:31
 * @Version 1.0.0
 * @Description User 微服务启动类
 */
@SpringBootApplication
@MapperScan("com.ryan.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
