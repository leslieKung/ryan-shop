package com.ryan.exception;

import com.ryan.enums.BizCodes;
import lombok.Data;

/**
 * @ClassName BizException
 * @Author Ryan
 * @Date 2026/3/4 21:31
 * @Version 1.0.0
 * @Description 统一异常处理类
 */
@Data
public class BizException extends RuntimeException{
    /**
     * 异常code码
     */
    private int code;

    /**
     * 异常信息
     */
    private String message;

    /**
     * 构造方法
     */
    public BizException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 根据bizCode返回异常信息
     */
    public BizException(BizCodes bizCodes) {
        super(bizCodes.getMessage());
        this.code = bizCodes.getCode();
        this.message = bizCodes.getMessage();
    }
}
