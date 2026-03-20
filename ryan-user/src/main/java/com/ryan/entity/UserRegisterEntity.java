package com.ryan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserRegisterEntity
 * @Author Ryan
 * @Date 2026/3/19 16:33
 * @Version 1.0.0
 * @Description 用户注册请求实体
 */
@ApiModel(value = "用户注册请求实体", description = "用户注册请求实体")
@Data
public class UserRegisterEntity {
    @ApiModelProperty(value = "昵称", example = "Ryan")
    private String name;

    @ApiModelProperty(value = "密码", example = "123456")
    private String password;

    @ApiModelProperty(value = "头像", example = "https://ryan-oss.oss-cn-shenzhen.aliyuncs.com/user/2026/03/18/097410be95d34eac932504baf224b403.png")
    private String avatar;

    @ApiModelProperty(value = "性别 0女 1男", example = "1")
    private String sex;

    @ApiModelProperty(value = "邮箱", example = "752244016@qq.com")
    private String mail;

    @ApiModelProperty(value = "手机号", example = "178866908286")
    private String phone;

    @ApiModelProperty(value = "验证码", example = "8HG2")
    private String code;

}
