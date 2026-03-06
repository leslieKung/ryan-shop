package com.ryan.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Ryan
 * @since 2026-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户密码
     */
    private String pwd;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 0 女，1 男
     */
    private Boolean sex;

    /**
     * 用户积分
     */
    private Integer points;

    /**
     * 用户余额
     */
    private BigDecimal nowMoney;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String mail;

    /**
     * 是否是 vip 0 普通，1 会员
     */
    private Boolean vip;

    /**
     * 用来个人敏感信息处理
     */
    private String secret;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
