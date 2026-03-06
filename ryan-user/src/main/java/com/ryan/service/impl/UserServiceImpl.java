package com.ryan.service.impl;

import com.ryan.model.UserDO;
import com.ryan.mapper.UserMapper;
import com.ryan.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Ryan
 * @since 2026-03-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

}
