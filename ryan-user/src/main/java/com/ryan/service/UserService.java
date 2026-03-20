package com.ryan.service;

import com.ryan.entity.UserRegisterEntity;
import com.ryan.utils.ApiResult;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Ryan
 * @since 2026-03-04
 */
public interface UserService {

    ApiResult register(UserRegisterEntity registerEntity);
}
