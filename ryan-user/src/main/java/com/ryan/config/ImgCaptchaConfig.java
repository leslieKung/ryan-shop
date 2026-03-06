package com.ryan.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 * @ClassName ImgCaptchaConfig
 * @Author Ryan
 * @Date 2026/3/5 17:00
 * @Version 1.0.0
 * @Description 图形验证码配置类
 */
public class ImgCaptchaConfig {

    @Bean
    @Qualifier("imgCaptchaProducer")
    public DefaultKaptcha imgCaptcha() {
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 验证码个数
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "5");
        // 字体间隔
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "8");
        // 背景颜色渐变开始，这里设置的是 rgb 值 156,156,156
        properties.put(Constants.KAPTCHA_BACKGROUND_CLR_FROM, "156,156,156");
        // 背景颜色渐变结束，这里设置以白色结束
        properties.put(Constants.KAPTCHA_BACKGROUND_CLR_TO, "white");
        // 字体颜色，这里设置为黑色
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
        // 文字间隔，这里设置为10px
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "10");
        // 干扰实现类
        properties.setProperty(Constants.KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.NoNoise");
        // 图片样式
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.WaterRipple");
        // 文字来源
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "abcdefghigklmiopqrstuvwxyz0123456789");
        // 设置配置
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
