package com.ryan.service;

import com.ryan.enums.SendCodes;
import com.ryan.utils.ApiResult;

/**
 * @ClassName CaptchaService
 * @Author Ryan
 * @Date 2026/3/6 20:37
 * @Version 1.0.0
 * @Description // TODO:
 */
public interface CaptchaService {
    /**
     * 发送验证码
     *
     * @param sendCodes
     * @param to
     * @return
     */
    ApiResult sendCode(SendCodes sendCodes, String to);

    /**
     * 检查验证码是否正确
     *
     * @param sendCodes
     * @param to
     * @param code
     * @return
     */
    boolean checkCode(SendCodes sendCodes, String to, String code);
}
