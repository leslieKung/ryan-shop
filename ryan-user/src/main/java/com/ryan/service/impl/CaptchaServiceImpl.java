package com.ryan.service.impl;

import com.ryan.constant.CacheKeyConstant;
import com.ryan.enums.BizCodes;
import com.ryan.enums.SendCodes;
import com.ryan.service.CaptchaService;
import com.ryan.service.MailService;
import com.ryan.utils.ApiResult;
import com.ryan.utils.CheckUtil;
import com.ryan.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName CaptchaServiceImpl
 * @Author Ryan
 * @Date 2026/3/6 20:39
 * @Version 1.0.0
 * @Description 验证码服务实现类
 */
@Service
@Slf4j
public class CaptchaServiceImpl implements CaptchaService {
    /**
     * 注入邮箱发送服务
     */
    @Autowired
    private MailService mailService;

    /**
     * 验证码标题
     */
    private static final String SUBJECT = "Ryan电商验证码";
    /**
     * 验证码内容
     */
    private static final String CONTENT = "您的验证码是%s,有效时间是10分钟,打死也不要告诉任何人";

    /**
     * 目前验证码为 key-value 形式，直接使用 StringRedisTemplate 即可
     */
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 验证码过期时间
     */
    private static final int CODE_EXPIRED = 60 * 1000 * 10;

    /**
     * 发送验证码
     * 防刷方案：基于原先的 code 拼装时间戳来一次性写入 Redis，满足原子性
     *
     * @param sendCodes
     * @param to
     * @return
     */
    public ApiResult sendCode(SendCodes sendCodes, String to) {
        // 先从缓存中获取验证码 key
        String cacheCheckKey = String.format(CacheKeyConstant.CHECK_CODE_KEY, sendCodes.name(), to);
        String cacheCheckVal = redisTemplate.opsForValue().get(cacheCheckKey);

        /**
         * 如果不为空，则判断是否60秒内重复发送
         */
        if (StringUtils.isNotBlank(cacheCheckVal)) {
            log.info("验证码模块-请不要重复发送验证码，验证码：{}", cacheCheckVal);
            return ApiResult.doResult(BizCodes.USER_CODE_FAST_LIMITED);
        }

        // 获取随机验证码
        String code = CommonUtil.getRandomCode(6);
        // 拼接验证码  格式：验证码_时间戳
        String newCode = code + "_" + CommonUtil.getCurrentTimestamp();
        log.info("验证码模块-写入 Redis 验证码：{}", cacheCheckKey + "_" + newCode);
        // 写入 Redis 中
        redisTemplate.opsForValue().set(cacheCheckKey, newCode, CODE_EXPIRED, TimeUnit.MILLISECONDS);

        // 邮箱验证码
        if (CheckUtil.isEmail(to)) {
            mailService.sendMail(to, SUBJECT, String.format(CONTENT, code));
            return ApiResult.doSuccess();
        } else if (CheckUtil.isPhone(to)) { // 短信验证码
            // TODO
        }
        return ApiResult.doResult(BizCodes.USER_PHONE_ERROR);
    }

    /**
     * 检查验证码是否正确
     *
     * @param sendCodes
     * @param to
     * @param code
     * @return
     */
    @Override
    public boolean checkCode(SendCodes sendCodes, String to, String code) {
        // 先从缓存中获取验证码 key
        String cacheCheckKey = String.format(CacheKeyConstant.CHECK_CODE_KEY, sendCodes.name(), to);
        String cacheCheckVal = redisTemplate.opsForValue().get(cacheCheckKey);
//        log.info("验证码模块-缓存验证码key：{}, 缓存验证码val：{}", cacheCheckKey, cacheCheckVal);

        /**
         * 如果不为空，则判断是否匹配
         */
        if (StringUtils.isNotBlank(cacheCheckVal)) {
            // 从缓存中取出验证码
            String cacheCheckCode = cacheCheckVal.split("_")[0];
//            log.info("验证码模块-缓存验证码：{}, 提交的邮箱验证码：{}", cacheCheckCode, code);
            // 判断缓存中的验证码跟传过来的验证码是否匹配，如果匹配成功则删除对应的缓存
            if (cacheCheckCode.equals(code)) {
                // 删除验证码
                redisTemplate.delete(cacheCheckKey);
                return true;
            }
        }

        return false;
    }
}
