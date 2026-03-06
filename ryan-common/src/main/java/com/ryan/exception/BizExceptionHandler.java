package com.ryan.exception;

import com.ryan.utils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName BizExceptionHandler
 * @Author Ryan
 * @Date 2026/3/4 22:21
 * @Version 1.0.0
 * @Description 全局异常处理类
 */
@ControllerAdvice
@Slf4j
public class BizExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public static ApiResult handle(Exception e) {
        if (e instanceof BizException bizException){
            log.error("[这里是业务异常信息]{}，具体内容如下：", e);
            return ApiResult.doError(bizException.getCode(),bizException.getMessage());
        } else {
            log.error("[这里是系统异常信息]{}，具体内容如下：", e);
            return ApiResult.doError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "这里是系统异常：" + e);
        }
    }
}
