package com.ryan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ryan.entity.UserRegisterEntity;
import com.ryan.enums.BizCodes;
import com.ryan.enums.SendCodes;
import com.ryan.mapper.UserMapper;
import com.ryan.model.UserDO;
import com.ryan.service.CaptchaService;
import com.ryan.service.UserService;
import com.ryan.utils.ApiResult;
import com.ryan.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Ryan
 * @since 2026-03-04
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册核心逻辑
     * 1. 检查邮箱验证码是否正确
     * 2. 密码安全、高并发下账号唯一性检测
     * 3. 入库
     *
     * @param registerEntity
     * @return
     */
    @Override
    public ApiResult register(UserRegisterEntity registerEntity) {
        boolean checkCode = false;

        if (StringUtils.isNotBlank(registerEntity.getMail())) {
            checkCode = captchaService.checkCode(SendCodes.USER_REGISTER, registerEntity.getMail(), registerEntity.getCode());
        }

        if (!checkCode) {
            return ApiResult.doResult(BizCodes.USER_CODE_EMAIL_ERROR);
        }

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(registerEntity, userDO);
        userDO.setCreateTime(new Date());

        // 密码安全，设置密码
        // 生成盐值 salt
        userDO.setSecret("$1$" + CommonUtil.getRandomSalt(8));
        // 生成密码 密码+盐值
        String pwd = Md5Crypt.md5Crypt(registerEntity.getPassword().getBytes(), userDO.getSecret());
        userDO.setPwd(pwd);

        // 账号唯一性检测
        if (checkUnique(registerEntity.getPhone())) {
            int row = userMapper.insert(userDO);
            log.info("用户注册模块-影响行数: {}, 注册成功: {}", row, userDO.toString());

            // 新用户注册成功，需要初始化信息，发送福利等 TODO
            initTask(userDO);
            return ApiResult.doSuccess();
        } else {
            // 用户已经存在，无法注册
            return ApiResult.doResult(BizCodes.USER_ACCOUNT_EXIST);
        }
    }

    /**
     * 新用户注册成功，需要初始化信息，发送福利等 TODO
     *
     * @param userDO
     */
    private void initTask(UserDO userDO) {
    }

    /**
     * 账号唯一性检测
     *
     * @param phone
     * @return
     */
    private boolean checkUnique(String phone) {
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<UserDO>().eq("phone", phone);
        List<UserDO> users = userMapper.selectList(queryWrapper);
        log.info("用户注册模块-查询手机号结果: {} ", users.toArray());
        return users.isEmpty();
    }
}
