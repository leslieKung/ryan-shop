package com.ryan.controller;


import com.alibaba.fastjson.JSONObject;
import com.ryan.enums.BizCodes;
import com.ryan.service.FileService;
import com.ryan.service.impl.UserServiceImpl;
import com.ryan.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private FileService fileService;

    @ApiOperation("用户账号信息查询")
    @GetMapping("/userDetail/{user_id}")
    public ApiResult userDetail() {
        String userInfo = "{'id':1,'name':'ryan','sex':'男','mail':'752244016@qq.com','avatar':'https://img1.baidu.com/it/u=1427121515,4062087528&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=750'}";
        return ApiResult.doSuccess(JSONObject.parseObject(userInfo));
    }


    @ApiOperation("用户头像上传")
    @PostMapping("/upload")
    public ApiResult userDetail(@ApiParam(value = "文件上传", required = true) @RequestPart("file") MultipartFile file) {
        String result = fileService.uploadUserAvatar(file);
        return result != null ? ApiResult.doSuccess(result) : ApiResult.doResult(BizCodes.USER_AVATAR_FILE_UPLOAD_ERROR);
    }

}

