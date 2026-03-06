package com.ryan.controller;


import com.alibaba.fastjson.JSONObject;
import com.ryan.service.impl.UserServiceImpl;
import com.ryan.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Ryan
 * @since 2026-03-04
 */
@Api(tags = "用户微服务账号模块")
@RestController
@RequestMapping("/api/user/v1/")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @ApiOperation("用户账号信息查询")
    @GetMapping("/userDetail/{user_id}")
    public ApiResult userDetail() {
        String userInfo ="{'id':1,'name':'huazai','sex':'男','mail':'1030181803@qq.com','avatar':'https://img1.baidu.com/it/u=1427121515,4062087528&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=750'}";
        return ApiResult.doSuccess(JSONObject.parseObject(userInfo));
    }

}

