package com.ryan.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.symmetric.DES;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

/**
 * @ClassName CommonUtil
 * @Author Ryan
 * @Date 2026/3/6 19:35
 * @Version 1.0.0
 * @Description 公共工具类
 */
@Slf4j
public class CommonUtil {

    /**
     * 获取客户端 ip
     */
    public static String getRemoteIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的 IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个 IP 为客户端真实 IP,多个 IP 按照 ',' 分割
            if (ipAddress != null && ipAddress.length() > 15) {
                // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "127.0.0.1";
        }
        return ipAddress;
    }

    /**
     * MD5 加密
     * @param data
     * @return
     */
    public static String MD5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
            }

            return sb.toString().toUpperCase();
        } catch (Exception exception) {
        }
        return null;

    }


    /**
     * 获取验证码随机数
     */
    public static String getRandomCode(int length){

        String sources = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int j=0; j<length; j++){
            sb.append(sources.charAt(random.nextInt(9)));
        }
        return sb.toString();
    }

    /**
     * 获取当前时间戳
     */
    public static long getCurrentTimestamp(){
        return System.currentTimeMillis();
    }

    /**
     * 生成 UUID
     */
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    /**
     * 加密串
     */
    private static final String SECRET_STRING = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 生成指定长度随机串
     * 这里直接参考 Md5Crypt 源码实现
     */
    public static String getRandomSalt(int num) {
        //生成随机数字和字母,符号
        Random random = new Random();
        StringBuilder saltString = new StringBuilder(num);
        for (int i = 1; i <= num; ++i) {
            saltString.append(SECRET_STRING.charAt(random.nextInt(SECRET_STRING.length())));
        }
        return saltString.toString();
    }

    /**
     * 返回响应给前端
     */
    public static void sendResponse(HttpServletResponse response, Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        // 设置响应头
        response.setContentType("application/json; charset=utf-8");

        try (PrintWriter writer = response.getWriter()) {
            // 写出响应
            writer.print(objectMapper.writeValueAsString(object));
            log.info("返回响应给前端数据：{}", objectMapper.writeValueAsString(object));
            // flush
            response.flushBuffer();
        } catch (IOException e) {
            log.warn("返回响应给前端异常：{}", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成区间范围内的随机数
     */
    public static int genRandomInt(int min, int max) {
        return genRandomInt(max-min)+min;
    }

    /**
     * 生成随机数
     */
    public static int genRandomInt(int bound) {
        return new Random().nextInt(bound);
    }

    /**
     * 生成一个新的时间戳
     */
    public static long addHoursToTimeStamp(long timestamp, long hours) {
        long hoursInMs = hours * 60 * 60 * 1000;
        return timestamp + hoursInMs;
    }

    /**
     * 生成密钥
     */
    public static String encryptPassword(String pwd, String key) {
        DES des = new DES(getDESSercretKey(key));
        byte[] result = des.encrypt(pwd);
        return Base64.encode(result);
    }

    /**
     * 解密密码
     */
    public static String decryptPassowrd(String pwd, String key) {
        DES des = new DES(getDESSercretKey(key));
        return des.decryptStr(pwd);
    }

    /**
     * 获得DES加密秘钥
     * @param key
     * @return
     */
    public static byte[] getDESSercretKey(String key) {
        byte[] result = new byte[8];
        byte[] keys = null;
        keys = key.getBytes(StandardCharsets.UTF_8);
        for(int i = 0; i<8;i++){
            if(i < keys.length){
                result[i] = keys[i];
            }else{
                result[i] = 0x01;
            }
        }
        return result;
    }

    /**
     * 字符串分割，转化为数组
     * @param str 字符串
     * @return List<Integer>
     */
    public static List<Integer> stringToArray(String str){
        return stringToArrayByRegex(str, ",");
    }


    /**
     * 数字字符数据转int格式数据
     * @param str 待转换的数字字符串
     * @return int数组
     */
    public static List<Integer> stringToArrayInt(String str){
        List<String> strings = stringToArrayStrRegex(str, ",");
        List<Integer> ids = new ArrayList<>();
        for (String string : strings) {
            ids.add(Integer.parseInt(string.trim()));
        }
        return ids;
    }

    /**
     * 字符串分割，转化为数组
     * @param str 字符串
     * @param regex 分隔符有
     * @return List<String>
     */
    public static List<String> stringToArrayStrRegex(String str, String regex ){
        List<String> list = new ArrayList<>();
        if (str.contains(regex)){

            String[] split = str.split(regex);

            for (String value : split) {
                if(!StringUtils.isBlank(value)){
                    list.add(value);
                }
            }
        }else {
            list.add(str);
        }
        return list;
    }

    /**
     * 字符串分割，转化为数组
     * @param str 字符串
     * @param regex 分隔符有
     * @return List<Integer>
     */
    public static List<Integer> stringToArrayByRegex(String str, String regex ){
        List<Integer> list = new ArrayList<>();
        if (str.contains(regex)){

            String[] split = str.split(regex);

            for (String value : split) {
                if(!StringUtils.isBlank(value)){
                    list.add(Integer.parseInt(value.trim()));
                }
            }
        }else {
            list.add(Integer.parseInt(str));
        }
        return list;
    }

    /**
     * 字符串分割，转化为数组
     * @param str 字符串
     * @return List<String>
     */
    public static List<String> stringToArrayStr(String str){
        return stringToArrayStrRegex(str, ",");
    }

    /**
     * 数据格式转换
     */
    public static <T> T convertValue(String value, Class<T> targetType) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            if (targetType == Long.class) {
                return targetType.cast(Long.valueOf(value));
            } else if (targetType == Integer.class) {
                return targetType.cast(Integer.valueOf(value));
            } else if (targetType == Boolean.class) {
                // 处理布尔值，假设 "true" 或 "false" 字符串
                return targetType.cast(Boolean.valueOf(value));
            } else if (targetType == BigInteger.class) {
                return targetType.cast(new BigInteger(value));
            } else if (targetType == Date.class) {
                // 假设时间戳为毫秒
                return targetType.cast(new Date(Long.parseLong(value)));
            } else if (targetType == String.class) {
                return targetType.cast(value);
            }
            // 可以根据需要添加更多类型的处理
        } catch (IllegalArgumentException e) {
            log.warn("类型转换失败，字段值: {}, 目标类型: {}", value, targetType.getSimpleName(), e);
        }
        return null;
    }

    /**
     * 获取uuid
     * @since 2024-08-06
     * @
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 响应json数据给前端
     *
     * @param response
     * @param obj
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json; charset=utf-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.print(objectMapper.writeValueAsString(obj));
            response.flushBuffer();
        } catch (IOException e) {
            log.warn("响应json数据给前端异常:{}", e.toString());
        }
    }

    /**
     * 生成支付 Html
     * @param response
     * @param result
     */
    public static void writePayData(HttpServletResponse response, ApiResult result) {
        try {
            response.setContentType("text/html;charset=UTF8");
            response.getWriter().write(result.getData().toString());
            response.getWriter().flush();
            response.getWriter().close();
        }catch (IOException e){
            log.error("生成支付 Html异常：{}", e.toString());
        }
    }
}
