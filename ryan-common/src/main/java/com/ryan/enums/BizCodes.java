package com.ryan.enums;

import lombok.Getter;

/**
 * @ClassName BizCodes
 * @Author Ryan
 * @Date 2026/3/4 21:22
 * @Version 1.0.0
 * @Description 业务状态码枚举类
 * 整个状态码总共 7 位，前三位表示「业务微服务」状态码，后4位表示「服务内部接口」状态码，后续需要再进行拆分。
 * 公共操作：110。
 * 用户服务：210，其中验证码：2101 开头，用户账号：2102 开头。
 * 购物车服务：310。
 * 商品服务：410。
 * 优惠券服务：510。
 * 订单服务：610。
 * 支付服务：710。
 * 美食服务：810。
 * 购物车服务：910。
 * 库存服务：1010。
 * 后台服务：1110.
 */
public enum BizCodes {
    /**
     * 通用操作码
     */
    COMMON_OP_REPEAT(110001,"重复操作"),
    COMMON_PARAM_ERROR(110002, "参数错误"),
    COMMON_SERVER_ERROR(110003, "服务异常"),
    COMMON_TOO_MANY_TRY(110004, "当前访问人数过多，请稍候再试..."),
    COMMON_NETWORK_ADDRESS_ERROR(110005, "网络地址错误"),
    COMMON_SERVER_BUSY_ERROR(110006, "系统繁忙，请稍后重试"),

    /**
     * 用户微服务验证码相关  2101 开头
     */
    USER_PHONE_ERROR(2101001,"手机号不合法"),
    USER_CODE_FAST_LIMITED(2101002,"验证码发送太快了"),
    USER_CODE_PHONE_ERROR(2101003,"手机验证码错误"),
    USER_CODE_CAPTCHA_ERROR(2101004,"图形验证码错误"),
    USER_CODE_EMAIL_ERROR(2101005,"邮箱验证码错误"),

    /**
     * 用户微服务账号相关 2102 开头
     */
    USER_ACCOUNT_EXIST(2102001,"用户账号已存在"),
    USER_ACCOUNT_UNREGISTER(2102002,"用户账号未注册"),
    USER_ACCOUNT_PWD_ERROR(2102003,"用户账号或密码错误"),
    USER_REFRESH_TOKEN_EMPTY(2102004,"用户刷新 token 为空"),
    USER_ACCOUNT_UNLOGIN(2102005,"用户账号未登录"),
    USER_PHONE_UPDATE_ERROR(2102006,"用户手机号修改失败"),
    USER_POINTS_OR_MONEY_UPDATE_ERROR(2102007,"用户积分/余额修改失败"),

    USER_LOGIN_SUCCESS(0,"用户登录成功"),
    USER_UPDATE_LOCK_FAIL(2102006,"修改用户信息获取锁失败"),
    USER_INFO_LOCK_FAIL(2102007,"读取用户信息获取锁失败"),
    USER_ACCOUNT_NOT_EXIST(2102008,"用户账号不存在"),

    /**
     * 用户微服务上传相关 2103 开头
     */
    USER_AVATAR_FILE_UPLOAD_ERROR(2103001, "用户头像上传失败"),

    /**
     * 用户微服务收货地址相关 2104 开头
     */
    USER_ADDRESS_NOT_EXITS(2104001, "收货地址不存在"),
    USER_ADDRESS_ADD_FAIL(2104002,"新增收货地址失败"),
    USER_ADDRESS_DEL_FAIL(2104003,"删除收货地址失败"),

    /**
     * 用户关注、取消 2105 开头
     * 博主关注、取消
     */
    USER_FOLLOWER_NOT_SELF(2105001, "不能关注自己哦"),
    USER_UN_FOLLOWER_NOT_SELF(2105002, "不能取关自己哦"),
    USER_FOLLOWED(2105003, "已经关注过了哦"),
    USER_UN_FOLLOWED(2105004, "已经取关过了哦"),
    USER_ATTENTION_NOT_SELF(2105005, "博主不能关注自己哦"),
    USER_UN_ATTENTION_NOT_SELF(2105006, "博主不能取关自己哦"),
    USER_ATTENTED(2105007, "已经关注过了哦"),
    USER_UN_ATTENTED(2105008, "已经取关过了哦"),
    USER_FOLLOWER_SUCCESS(0, "关注成功"),
    USER_UN_FOLLOWER_SUCCESS(0, "取关成功"),
    USER_ATTENTION_SUCCESS(0, "关注成功"),
    USER_UN_ATTENTION_SUCCESS(0, "取关成功"),
    USER_FOLLOWER_INFO_LOCK_FAIL(2105009,"查询用户是否关注锁失败"),

    /**
     * 优惠券服务相关 5101 开头
     */
    COUPON_TO_SKU_NOT_EXIST_FAIL(5101001, "该商品关联的优惠券不存在或者异常"),
    COUPON_TEMPLATE_NOT_EXIST_FAIL(5101002, "该优惠券不存在或者异常"),
    NOT_MATCH_REDEEM_COUPON_FAIL(5101003, "不在优惠券领取时间范围内"),
    USER_COUPON_LIMIT_UP_FAIL(5101004, "您已达到该优惠券的领取上限"),
    USER_COUPON_STOCK_INSUFFICIENT_FAIL(5101005, "该优惠券库存不足无法领券"),

    /**
     * 订单服务相关 6101 开头
     */
    ORDER_BUYER_FAIL(6101001, "订单获取用户基础信息失败"),
    ORDER_BUYER_NOT_EXIST_FAIL(6101002, "该用户不存在或者异常"),
    ORDER_CREATE_VALID_FAILED(6101003, "订单创建校验失败"),
    ORDER_USER_COUPON_FAIL(6101005, "订单获取用户领取优惠券信息失败"),
    ORDER_USER_COUPON_NOT_EXIST_FAIL(6101006, "订单获取用户领取优惠券信息为空"),
    ORDER_COUPON_NOT_EXIST_FAIL(6101007, "订单获取用户领取优惠券信息匹配为空"),
    ORDER_COUPON_EXPIRED_FAIL(6101008, "订单获取用户领取优惠券信息已过期"),
    ORDER_INVENTORY_FAIL(6101009, "订单获取库存信息失败"),
    ORDER_INVENTORY_NOT_ENOUGH(6101010, "订单获取库存信息不足"),
    ORDER_SKU_FAIL(6101011, "订单获取商品信息失败"),
    ORDER_SKU_NOT_EXIST_FAIL(6101012, "该订单商品不存在或者异常"),
    ORDER_SKU_PRICE_ERROR(6101013, "该订单商品价格不一致"),
    ORDER_INVENTORY_DEDUCT_FAIL(6101014, "该订单商品库存扣减失败"),
    ORDER_EXIST(6101015, "订单已存在"),
    ORDER_SNAPSHOT_CREATE_FAIL(6101016, "订单流水创建失败"),
    ORDER_CREATE_FAIL(6101017, "订单创建失败"),
    ORDER_NOT_EXIST(6101018, "订单不存在"),
    ORDER_CONFIRM_FAIL(6101019, "订单确认失败"),
    ORDER_PERMISSION_DENIED(6101020, "订单操作权限不足"),
    ORDER_SNAPSHOT_EXIST(6101021, "订单流水已存在"),
    ORDER_UPDATE_FAILED(6101022, "订单更新失败"),
    ORDER_UPDATE_LOCK_FAIL(6101023,"更新订单信息获取锁失败"),
    ORDER_PAID_NOT_CANCEL(6101024,"订单已支付无法取消"),
    ORDER_DELETE_ERROR(6101025,"订单删除失败"),
    ORDER_BUYER_ADDRESS_FAIL(6101026, "订单获取用户收货地址信息失败"),
    ORDER_BUYER_ADDRESS_NOT_EXIST_FAIL(6101027, "订单获取用户收货地址信息不存在"),
    ORDER_PAY_SUCCESS_UPDATE_ORDER_FAIL(6101028, "订单支付成功更新订单失败"),
    ORDER_PAID_NOT_UPDATE(6101029,"订单已支付无法修改状态"),

    /**
     * 支付微服务相关 7101 开头
     */
    PAY_ORDER_TIMEOUT(7101001,"订单支付超时"),
    PAY_ORDER_CALLBACK_NOT_SUCCESS(7101002,"支付宝回调更新订单失败"),
    PAY_ORDER_FAIL(7101003,"创建支付订单失败"),
    /**
     * 支付订单查询失败
     */
    PAY_ORDER_QUERY_FAILED(7101004, "支付订单查询失败"),
    /**
     * 支付订单不存在
     */
    PAY_ORDER_NOT_EXIST(7101005, "支付订单不存在"),

    /**
     * 美食微服务相关 8101 开头
     */
    FOOD_INFO_NOT_EXISTS(8101001, "美食信息不存在"),
    FOOD_UPDATE_LOCK_FAIL(8101002,"修改用户信息获取锁失败"),
    FOOD_INFO_LOCK_FAIL(8101003,"读取用户信息获取锁失败"),
    FOOD_EXIST_LIKE_FAIL(8101004,"该用户已经点赞过该美食了，不能重复点赞"),
    FOOD_EXIST_UN_LIKE_FAIL(8101005,"该用户已经取消点赞该美食了，不能重复取消点赞"),
    FOOD_EXIST_COLLECT_FAIL(8101006,"该用户已经收藏过该美食了，不能重复收藏"),
    FOOD_EXIST_UN_COLLECT_FAIL(8101007,"该用户已经取消收藏该美食了，不能重复取消收藏"),

    /**
     * 美食微服务评论相关 8102 开头
     */
    FOOD_COMMENT_NOT_EXISTS(8102001, "美食评论信息不存在"),
    FOOD_COMMENT_NOT_TOP(8102002,"非自己发布的美食评论信息无法置顶"),
    FOOD_COMMENT_NOT_DEL(8102003,"非自己发布的美食评论信息无法删除"),


    /**
     * 购物车微服务评论相关 9101 开头
     */
    CART_SKU_COUNT_THRESHOLD_ERROR(9101001, "购物车商品数量达到上限"),
    CART_SKU_SELL_STATUS_ERROR(9101002, "该商品目前未开放销售"),
    CART_SKU_NOT_EXIST_ERROR(9101003, "购物车中没有该商品"),
    CART_SKU_PERSISTENCE_ERROR(9101004, "购物车商品持久化失败"),
    CART_SKU_CHANGE_LOCK_FAIL(9101005,"加购失败，请不要频繁操作哦"),
    CART_SKU_NOT_EXIST_FAIL(9101006,"该商品不存在或者异常"),

    /**
     * 库存服务相关 1010 开头
     */
    INVENTORY_STOCK_DETAIL_CREATE_FAIL(10100001, "商品库存变更明细创建失败"),
    INVENTORY_STOCK_UPDATE_FAIL(10100002, "商品库存修改失败"),

    /**
     * 后台服务
     */
    ADMIN_CAPTCHA_CODE_ERROR(1110001,"验证码不正确"),
    ADMIN_USER_NOT_EXISTS_OR_PWD_ERROR(1110002,"后台用户不存在或密码错误"),
    ADMIN_USER_REFRESH_TOKEN_EMPTY(1110003,"后台用户刷新 token 为空"),
    ADMIN_USER_ACCOUNT_UNLOGIN(1110004,"后台用户账号未登录"),
    ADMIN_USER_LOGOUT(1110005,"后台登录信息已过期，请重新登录"),
    ADMIN_USER_NOT_EXISTS(1110006,"后台用户名不存在"),
    ADMIN_USER_OR_PWD_ERROR(1110007,"账号或者密码不正确"),
    ADMIN_USER_EXISTS(1110008,"账号已存在"),
    ADMIN_MANGER_NOT_EXISTS(1110009,"管理员不存在"),
    ADMIN_MANGER_ADD_ERROR(1110010,"添加管理员失败"),
    ADMIN_MANGER_DEL_ERROR(1110011,"系统管理员删除失败"),
    ADMIN_MANGER_UPDATE_ERROR(1110012,"系统管理员修改失败"),


    ADMIN_MENU_NOT_EXISTS(1120001,"系统菜单不存在"),
    ADMIN_MENU_ADD_ERROR(1120002,"系统菜单添加失败"),
    ADMIN_MENU_DEL_ERROR(1120003,"系统菜单删除失败"),
    ADMIN_MENU_UPDATE_ERROR(1120003,"系统菜单修改失败"),

    ADMIN_ROLE_NOT_EXISTS(1130001,"系统角色不存在"),
    ADMIN_ROLE_ADD_ERROR(1130002,"系统角色添加失败"),
    ADMIN_ROLE_DEL_ERROR(1130003,"系统角色删除失败"),
    ADMIN_ROLE_UPDATE_ERROR(1130004,"系统角色修改失败"),
    ADMIN_ROLE_NAME_EXISTS(1130005,"角色名称重复"),
    ADMIN_ROLE_DEL(1130006,"角色已删除"),

    ADMIN_PRODUCT_ADD_ERROR(1140001,"新增商品失败"),
    ADMIN_PRODUCT_RESTORE_ERROR(1140002,"商品恢复失败"),
    ADMIN_PRODUCT_UPDATE_ERROR(1140003,"修改商品失败"),
    ADMIN_PRODUCT_OFF_ERROR(1140004,"商品下架失败"),
    ADMIN_PRODUCT_ON_ERROR(1140005, "商品上架失败"),
    ADMIN_PRODUCT_NOT_EXISTS(1140006,"商品不存在"),

    ADMIN_CATEGORY_ADD_ERROR(1150001,"新增分类失败"),
    ADMIN_CATEGORY_DEL_ERROR(1150002,"删除分类失败"),
    ADMIN_CATEGORY_UPDATE_ERROR(1150003,"修改分类失败"),

    ADMIN_ATTR_ADD_ERROR(1160001,"新增属性失败"),
    ADMIN_ATTR_DEL_ERROR(1160002,"删除属性失败"),
    ADMIN_ATTR_UPDATE_ERROR(1160003,"修改属性失败"),

    ADMIN_ATTR_GROUP_ADD_ERROR(1170001,"新增属性组失败"),
    ADMIN_ATTR_GROUP_DEL_ERROR(1170002,"删除属性组失败"),
    ADMIN_ATTR_GROUP_UPDATE_ERROR(1170003,"修改属性组失败"),

    ADMIN_BRAND_ADD_ERROR(1170001,"新增品牌失败"),
    ADMIN_BRAND_DEL_ERROR(1170002,"删除品牌失败"),
    ADMIN_BRAND_UPDATE_ERROR(1170003,"修改品牌失败"),
    ADMIN_BRAND_UPDATE_STATUS_ERROR(1170004,"修改品牌状态失败"),


    ADMIN_FOOD_DEL_ERROR(1180001, "美食笔记删除失败"),
    ADMIN_FOOD_UPDATE_STATUS_ERROR(1180002, "美食笔记修改状态失败"),

    ADMIN_COUPON_ADD_ERROR(1190001,"新增优惠券模板失败"),
    ADMIN_COUPON_DEL_ERROR(1190002,"删除优惠券模板失败"),
    ADMIN_COUPON_UPDATE_STATUS_ERROR(1190003,"修改优惠券模板状态失败"),
    ADMIN_COUPON_PRODUCT_ERROR(1190004,"商品专属券必须指定有效商品ID"),
    ADMIN_COUPON_NOT_EXISTS(1190005,"优惠券模板不存在"),
    ADMIN_COUPON_NOT_EXISTS_OR_VALID(1190006,"优惠券模板不存在或者已失效"),
    ADMIN_COUPON_NOT_CHANGE(1190007,"优惠券状态无需变更"),
    ADMIN_COUPON_CONSUME_RULE_GENERATE_ERROR(1190008,"优惠券消耗规则生成失败"),
    ADMIN_COUPON_RECEIVE_RULE_GENERATE_ERROR(1190008,"优惠券领取规则生成失败"),
    ADMIN_COUPON_ADD_TASK_ERROR(1190009,"新增优惠券推送任务失败"),
    ADMIN_COUPON_TASK_NUM_VALID(1190010,"优惠券发放数量不能大于模板的发行数量"),
    ADMIN_COUPON_TASK_DEL_ERROR(1190011,"删除优惠券推送任务失败"),
    ADMIN_COUPON_TASK_UPDATE_STATUS_ERROR(1190012,"修改优惠券推送任务状态失败"),
    ADMIN_COUPON_TASK_NOT_EXISTS(1190013,"优惠券推送任务不存在"),


    ADMIN_NOT_AUTH(1200001,"没有权限访问！"),

    ADMIN_ATTACHMENT_NOT_EXISTS(1300001,"系统附件不存在"),
    ADMIN_ATTACHMENT_ADD_ERROR(1300002,"系统附件添加失败"),
    ADMIN_ATTACHMENT_DEL_ERROR(1300003,"系统附件删除失败"),
    ADMIN_ATTACHMENT_UPDATE_ERROR(1300004,"系统附件修改失败"),
    ADMIN_ATTACHMENT_MOVE_ERROR(1300005,"系统附件修改图片目录失败"),


    /**
     * sentinel 流控操作
     */
    SENTINEL_CONTROL_FLOW(1400001,"限流控制"),
    SENTINEL_CONTROL_DEGRADE(1400002,"降级控制"),
    SENTINEL_CONTROL_AUTH(1400003,"认证控制"),
    SENTINEL_CONTROL_PARAM(1400004,"热点参数控制"),
    SENTINEL_CONTROL_SYSTEM(1400005,"系统规则控制");



    /**
     * 错误信息
     */
    @Getter
    private String message;

    /**
     * code 状态码
     */
    @Getter
    private int code;

    /**
     * 内部使用
     * @param code
     * @param message
     */
    private BizCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
