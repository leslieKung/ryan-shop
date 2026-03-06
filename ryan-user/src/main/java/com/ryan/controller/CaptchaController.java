package com.ryan.controller;

import com.google.code.kaptcha.Producer;
import com.ryan.enums.BizCodes;
import com.ryan.enums.SendCodes;
import com.ryan.service.CaptchaService;
import com.ryan.utils.ApiResult;
import com.ryan.utils.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CaptchaController
 * @Author Ryan
 * @Date 2026/3/5 17:05
 * @Version 1.0.0
 * @Description // TODO:
 */
@Api(tags = "验证码模块")
@RestController
@RequestMapping("/api/captcha/v1")
@Slf4j
public class CaptchaController {

    @Autowired
    private Producer captchaProducer;

    // 验证码有效期 10 分钟
    private static final long CAPTCHA_EXPIRE = 1000 * 10 * 60;

    // 验证码 Redis key 前缀
    private static final String CAPTCHA_REDIS_KEY_PREFIX = "ryan-user:captcha:";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CaptchaService captchaService;

    @ApiOperation("获取图形验证码")
    @RequestMapping("getImgCaptcha")
    public void getImgCaptcha(HttpServletRequest request, HttpServletResponse response) {
        // 1. 生成图形验证码文本
        String text = captchaProducer.createText();
        log.info("验证码模块-获取图形验证码文本：{}", text);

        // 2. 保存到 Redis 中
        redisTemplate.opsForValue().set(getCaptchaKey(request), text, CAPTCHA_EXPIRE, TimeUnit.MILLISECONDS);

        // 3. 创建图片
        BufferedImage image = captchaProducer.createImage(text);
        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
            out.flush();
        } catch (IOException e) {
            log.error("验证码模块-获取图形验证码异常：{}", e);
        }
    }

    /**
     * 这里需要对用户进行唯一识别，方式就是获取用户的 IP + 浏览器的 User-Agent 属性
     */
    private String getCaptchaKey(HttpServletRequest request) {
        // 获取客户端用户的 IP 地址
        String ip = CommonUtil.getRemoteIpAddr(request);
        // 获取请求头中的 User-Agent 属性值
        String userAgent = request.getHeader("User-Agent");

        // 根据 ip + userAgent 生成对应的 key
        String keyGen = CAPTCHA_REDIS_KEY_PREFIX + CommonUtil.MD5(ip + userAgent);

        log.info("验证码模块-验证码 key:{}", keyGen);
        return keyGen;
    }

    /**
     * 发送注册验证码
     * 1.先从缓存中获取验证码，如果获取到则返回验证码已发送
     * 2.如果没有获取到验证码，则生成验证码，并保存到缓存中，并返回验证码发送成功
     * 3.发送验证码
     *
     * @param to
     * @param captcha
     * @param request
     * @return
     */
    @ApiOperation("发送注册验证码")
    @PostMapping("sendCode")
    public ApiResult sendRegisterCode(@RequestParam(value = "to", required = true) String to,
                                      @RequestParam(value = "captcha", required = true) String captcha, HttpServletRequest request) {
        String captchaKey = getCaptchaKey(request);
        String captchaCache = redisTemplate.opsForValue().get(captchaKey);
        if (captchaCache != null & captcha != null & captcha.equals(captchaCache)) {
            ApiResult apiResult = captchaService.sendCode(SendCodes.USER_REGISTER, captcha);
            return apiResult;
        } else {
            return ApiResult.doResult(BizCodes.USER_CODE_CAPTCHA_ERROR);
        }
    }
}
