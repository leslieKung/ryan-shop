package com.ryan.constant;

/**
 * @ClassName CacheKeyConstant
 * @Author Ryan
 * @Date 2026/3/6 20:41
 * @Version 1.0.0
 * @Description 常量定义
 */
public class CacheKeyConstant {
    /**
     * 验证码校验，其中第一个%s是类型，第二个%s是手机号
     * 比如注册下的验证码
     */
    public static final String CHECK_CODE_KEY = "code:%s:%s";

    /**
     * JWT refreshToken key
     */
    public static final String REFRESH_TOKEN_KEY = "refresh:%s";

    /**
     * redis连接失败
     */
    public static final String REDIS_CONNECTION_FAILED = "redis_connection_failed";
}
