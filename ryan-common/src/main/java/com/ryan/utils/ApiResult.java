package com.ryan.utils;

import com.ryan.enums.BizCodes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ApiResult
 * @Author Ryan
 * @Date 2026/3/4 21:26
 * @Version 1.0.0
 * @Description 统一接口响应类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult {

    /**
     * 接口响应状态码 0 表示成功 其他表示失败
     */
    private Integer code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 描述
     */
    private String msg;

    /**
     * 成功响应
     */
    public static ApiResult doSuccess() {
        return new ApiResult(0, null, "成功");
    }


    /**
     *  成功响应，传入数据
     * @param data
     * @return
     */
    public static ApiResult doSuccess(Object data) {
        return new ApiResult(0, data, null);
    }


    /**
     * 失败响应
     * @param code
     * @return
     */
    public static ApiResult doError(Integer code, String msg) {
        return new ApiResult(code, null, msg);
    }


    /**
     * 自定义状态码和错误信息
     * @param code
     * @param msg
     * @return
     */
    public static ApiResult buildResult(int code, String msg) {
        return new ApiResult(code, null, msg);
    }

    /**
     * 通过枚举返回
     */
    public static ApiResult doResult(BizCodes codes) {
        return ApiResult.buildResult(codes.getCode(),codes.getMessage());
    }

}
