package com.ryan.service.impl;

import com.ryan.model.UserAddressDO;
import com.ryan.mapper.UserAddressMapper;
import com.ryan.service.UserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户收货地址表 服务实现类
 * </p>
 *
 * @author Ryan
 * @since 2026-03-04
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddressDO> implements UserAddressService {

}
