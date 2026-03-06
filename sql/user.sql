SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                         `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
                         `pwd` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
                         `avatar` varchar(524) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
                         `sex` tinyint(1) NULL DEFAULT 1 COMMENT '0 女，1 男',
                         `points` int(0) NULL DEFAULT 0 COMMENT '用户积分',
                         `now_money` decimal(16, 2) NULL DEFAULT NULL COMMENT '用户余额',
                         `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户手机号',
                         `mail` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
                         `vip` tinyint(1) NULL DEFAULT 0 COMMENT '是否是 vip 0 普通，1 会员',
                         `secret` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用来个人敏感信息处理',
                         `last_login_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '最后登录时间',
                         `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                         `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                         PRIMARY KEY (`id`) USING BTREE,
                         UNIQUE INDEX `phone_idx`(`phone`) USING BTREE,
                         INDEX `idx_sex`(`sex`) USING BTREE,
                         INDEX `idx_create_time`(`create_time`) USING BTREE,
                         INDEX `idx_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, '华仔', '$1$WWkqM7NY$VeHgNqDRvwnwXb2Ct4/7/.', 'https://sns-avatar-qc.xhscdn.com/avatar/1040g2jo312o35lk7io6g5n10nodhrb4q8s4pq7o?imageView2/2/w/540/format/webp|imageMogr2/strip2', 1, 0, NULL, '15810907859', 'wangjianghua1qaz@163.com', 0, '$1$WWkqM7NY', '2025-07-08 10:11:09', '2024-08-18 02:17:14', '2025-01-04 15:37:56');
INSERT INTO `user` VALUES (3, '渝小厨', '$1$WWkqM7NY$VeHgNqDRvwnwXb2Ct4/7/.', 'https://sns-avatar-qc.xhscdn.com/avatar/1040g2jo312o35lk7io6g5n10nodhrb4q8s4pq7o?imageView2/2/w/540/format/webp|imageMogr2/strip2', 1, 0, NULL, '15810907838', 'wangjianghua@163.com', 0, '$1$WWkqM7NY', '2025-07-08 10:11:09', '2024-08-18 02:17:14', '2025-01-07 13:44:23');
INSERT INTO `user` VALUES (36, '华仔1', '$1$WWkqM7NY$VeHgNqDRvwnwXb2Ct4/7/.', 'https://sns-avatar-qc.xhscdn.com/avatar/1040g2jo312o35lk7io6g5n10nodhrb4q8s4pq7o?imageView2/2/w/540/format/webp|imageMogr2/strip2', 1, 0, NULL, '15810907851', 'wangjianghua1qaz@163.com', 0, '$1$WWkqM7NY', '2025-07-08 10:11:09', '2024-08-18 02:17:14', '2025-01-04 15:37:56');
INSERT INTO `user` VALUES (37, '华仔2', '$1$WWkqM7NY$VeHgNqDRvwnwXb2Ct4/7/.', 'https://sns-avatar-qc.xhscdn.com/avatar/1040g2jo312o35lk7io6g5n10nodhrb4q8s4pq7o?imageView2/2/w/540/format/webp|imageMogr2/strip2', 1, 0, NULL, '15810907852', 'wangjianghua1qaz@163.com', 0, '$1$WWkqM7NY', '2025-07-08 10:11:09', '2024-08-18 02:17:14', '2025-01-04 15:37:56');
INSERT INTO `user` VALUES (38, '华仔3', '$1$WWkqM7NY$VeHgNqDRvwnwXb2Ct4/7/.', 'https://sns-avatar-qc.xhscdn.com/avatar/1040g2jo312o35lk7io6g5n10nodhrb4q8s4pq7o?imageView2/2/w/540/format/webp|imageMogr2/strip2', 1, 0, NULL, '15810907853', 'wangjianghua1qaz@163.com', 0, '$1$WWkqM7NY', '2025-07-08 10:11:09', '2024-08-18 02:17:14', '2025-01-04 15:37:56');
INSERT INTO `user` VALUES (39, '华仔4', '$1$WWkqM7NY$VeHgNqDRvwnwXb2Ct4/7/.', 'https://sns-avatar-qc.xhscdn.com/avatar/1040g2jo312o35lk7io6g5n10nodhrb4q8s4pq7o?imageView2/2/w/540/format/webp|imageMogr2/strip2', 1, 0, NULL, '15810907854', 'wangjianghua1qaz@163.com', 0, '$1$WWkqM7NY', '2025-07-08 10:11:09', '2024-08-18 02:17:14', '2025-01-04 15:37:56');
INSERT INTO `user` VALUES (40, '华仔5', '$1$WWkqM7NY$VeHgNqDRvwnwXb2Ct4/7/.', 'https://sns-avatar-qc.xhscdn.com/avatar/1040g2jo312o35lk7io6g5n10nodhrb4q8s4pq7o?imageView2/2/w/540/format/webp|imageMogr2/strip2', 1, 0, NULL, '15810907855', 'wangjianghua1qaz@163.com', 0, '$1$WWkqM7NY', '2025-07-08 10:11:09', '2024-08-18 02:17:14', '2025-01-04 15:37:56');
INSERT INTO `user` VALUES (41, '华仔6', '$1$WWkqM7NY$VeHgNqDRvwnwXb2Ct4/7/.', 'https://sns-avatar-qc.xhscdn.com/avatar/1040g2jo312o35lk7io6g5n10nodhrb4q8s4pq7o?imageView2/2/w/540/format/webp|imageMogr2/strip2', 1, 0, NULL, '15810907856', 'wangjianghua1qaz@163.com', 0, '$1$WWkqM7NY', '2025-07-08 10:11:09', '2024-08-18 02:17:14', '2025-01-04 15:37:56');
INSERT INTO `user` VALUES (42, '华仔7', '$1$WWkqM7NY$VeHgNqDRvwnwXb2Ct4/7/.', 'https://sns-avatar-qc.xhscdn.com/avatar/1040g2jo312o35lk7io6g5n10nodhrb4q8s4pq7o?imageView2/2/w/540/format/webp|imageMogr2/strip2', 1, 0, NULL, '15810907857', 'wangjianghua1qaz@163.com', 0, '$1$WWkqM7NY', '2025-07-08 10:11:09', '2024-08-18 02:17:14', '2025-01-04 15:37:56');
INSERT INTO `user` VALUES (43, '华仔8', '$1$WWkqM7NY$VeHgNqDRvwnwXb2Ct4/7/.', 'https://sns-avatar-qc.xhscdn.com/avatar/1040g2jo312o35lk7io6g5n10nodhrb4q8s4pq7o?imageView2/2/w/540/format/webp|imageMogr2/strip2', 1, 0, NULL, '15810907858', 'wangjianghua1qaz@163.com', 0, '$1$WWkqM7NY', '2025-07-08 10:11:09', '2024-08-18 02:17:14', '2025-01-04 15:37:56');
INSERT INTO `user` VALUES (44, '华仔9', '$1$WWkqM7NY$VeHgNqDRvwnwXb2Ct4/7/.', 'https://sns-avatar-qc.xhscdn.com/avatar/1040g2jo312o35lk7io6g5n10nodhrb4q8s4pq7o?imageView2/2/w/540/format/webp|imageMogr2/strip2', 1, 0, NULL, '15810907850', 'wangjianghua1qaz@163.com', 0, '$1$WWkqM7NY', '2025-07-08 10:11:09', '2024-08-18 02:17:14', '2025-01-04 15:37:56');
INSERT INTO `user` VALUES (45, '华仔10', '$1$WWkqM7NY$VeHgNqDRvwnwXb2Ct4/7/.', 'https://sns-avatar-qc.xhscdn.com/avatar/1040g2jo312o35lk7io6g5n10nodhrb4q8s4pq7o?imageView2/2/w/540/format/webp|imageMogr2/strip2', 1, 0, NULL, '15810907860', 'wangjianghua1qaz@163.com', 0, '$1$WWkqM7NY', '2025-07-08 10:11:09', '2024-08-18 02:17:14', '2025-01-04 15:37:56');

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address`  (
                                 `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                                 `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
                                 `default_status` tinyint(1) NULL DEFAULT 0 COMMENT '是否默认收货地址：0否 1是',
                                 `receive_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收发货人姓名',
                                 `phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人电话',
                                 `province` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省/直辖市',
                                 `city` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市',
                                 `region` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区',
                                 `detail_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
                                 `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                 `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `uid`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户收货地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_address
-- ----------------------------
INSERT INTO `user_address` VALUES (3, 2, 0, '华仔', '15810907859', '北京市', '北京市', '昌平区', '老王隔壁 111 号', '2024-08-21 00:03:24', '2024-08-21 00:03:24');
INSERT INTO `user_address` VALUES (4, 2, 0, '华仔', '15810907859', '北京市', '北京市', '昌平区', '老王隔壁 112 号', '2024-08-21 00:07:10', '2024-08-21 08:45:07');
INSERT INTO `user_address` VALUES (5, 2, 1, '华仔1', '15810907859', '北京市', '北京市', '昌平区', '老王隔壁 113 号', '2024-08-21 08:40:45', '2025-05-01 23:37:39');

-- ----------------------------
-- Table structure for user_attention
-- ----------------------------
DROP TABLE IF EXISTS `user_attention`;
CREATE TABLE `user_attention`  (
                                   `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                   `user_id` bigint(0) NOT NULL COMMENT '当前博主用户id',
                                   `attention_id` bigint(0) NOT NULL COMMENT '关注博主用户id',
                                   `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建设计，会按这个字段排序',
                                   `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除，0 正常 1 删除',
                                   PRIMARY KEY (`id`) USING BTREE,
                                   INDEX `uid`(`user_id`, `create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户关注表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_attention
-- ----------------------------
INSERT INTO `user_attention` VALUES (1, 2, 3, '2024-09-27 23:01:55', 1);
INSERT INTO `user_attention` VALUES (2, 2, 40, '2025-01-09 20:11:07', 0);
INSERT INTO `user_attention` VALUES (3, 2, 1, '2025-01-11 12:32:26', 0);
INSERT INTO `user_attention` VALUES (4, 2, 44, '2025-01-11 12:43:45', 0);
INSERT INTO `user_attention` VALUES (5, 2, 43, '2025-01-11 23:42:01', 0);
INSERT INTO `user_attention` VALUES (6, 2, 36, '2025-01-12 23:36:50', 0);
INSERT INTO `user_attention` VALUES (7, 2, 39, '2025-01-13 16:00:31', 0);
INSERT INTO `user_attention` VALUES (8, 2, 41, '2025-01-13 16:00:40', 0);
INSERT INTO `user_attention` VALUES (9, 2, 38, '2025-01-13 16:00:41', 0);
INSERT INTO `user_attention` VALUES (10, 2, 37, '2025-01-13 16:00:43', 0);

-- ----------------------------
-- Table structure for user_follower
-- ----------------------------
DROP TABLE IF EXISTS `user_follower`;
CREATE TABLE `user_follower`  (
                                  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                  `user_id` bigint(0) NOT NULL COMMENT '博主id',
                                  `follower_id` bigint(0) NOT NULL COMMENT '粉丝id',
                                  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间，按时间排序',
                                  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0 正常 1删除',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  UNIQUE INDEX `idx_ukey`(`user_id`, `follower_id`) USING BTREE,
                                  INDEX `uid`(`user_id`, `create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '粉丝关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_follower
-- ----------------------------
INSERT INTO `user_follower` VALUES (1, 3, 2, '2024-09-27 22:34:47', 0);
INSERT INTO `user_follower` VALUES (7, 2, 36, '2025-01-07 13:45:20', 0);
INSERT INTO `user_follower` VALUES (8, 2, 37, '2025-01-07 13:45:22', 0);
INSERT INTO `user_follower` VALUES (9, 2, 38, '2025-01-07 13:45:25', 0);
INSERT INTO `user_follower` VALUES (10, 2, 39, '2025-01-07 13:45:43', 0);
INSERT INTO `user_follower` VALUES (11, 2, 40, '2025-01-07 13:45:42', 0);
INSERT INTO `user_follower` VALUES (13, 2, 41, '2025-01-07 13:45:40', 0);
INSERT INTO `user_follower` VALUES (14, 2, 42, '2025-01-07 13:45:50', 0);
INSERT INTO `user_follower` VALUES (15, 2, 43, '2025-01-07 13:45:54', 0);
INSERT INTO `user_follower` VALUES (16, 3, 61, '2024-09-21 22:58:52', 0);
INSERT INTO `user_follower` VALUES (17, 2, 44, '2025-01-07 13:46:03', 0);
INSERT INTO `user_follower` VALUES (19, 4, 44, '2025-01-07 13:46:29', 0);
INSERT INTO `user_follower` VALUES (20, 3, 28, '2024-09-21 22:58:52', 0);
INSERT INTO `user_follower` VALUES (21, 3, 5, '2024-09-21 22:58:52', 0);
INSERT INTO `user_follower` VALUES (22, 3, 56, '2024-09-21 22:58:52', 0);
INSERT INTO `user_follower` VALUES (23, 3, 75, '2024-09-21 22:58:52', 0);
INSERT INTO `user_follower` VALUES (24, 3, 16, '2024-09-21 22:58:52', 0);
INSERT INTO `user_follower` VALUES (25, 3, 78, '2024-09-21 22:58:52', 0);
INSERT INTO `user_follower` VALUES (26, 3, 9, '2024-09-21 22:58:53', 0);
INSERT INTO `user_follower` VALUES (27, 3, 62, '2024-09-22 22:45:58', 0);
INSERT INTO `user_follower` VALUES (28, 3, 26, '2024-09-22 22:45:57', 0);
INSERT INTO `user_follower` VALUES (29, 3, 95, '2024-09-22 22:45:58', 0);
INSERT INTO `user_follower` VALUES (30, 3, 53, '2024-09-22 22:45:58', 0);
INSERT INTO `user_follower` VALUES (31, 3, 15, '2024-09-22 22:45:58', 0);
INSERT INTO `user_follower` VALUES (32, 3, 86, '2024-09-22 22:45:58', 0);
INSERT INTO `user_follower` VALUES (33, 3, 48, '2024-09-22 22:45:58', 0);
INSERT INTO `user_follower` VALUES (34, 3, 55, '2024-09-22 22:45:58', 0);
INSERT INTO `user_follower` VALUES (35, 3, 88, '2024-09-22 22:45:58', 0);
INSERT INTO `user_follower` VALUES (36, 3, 3, '2024-09-22 22:45:58', 0);
INSERT INTO `user_follower` VALUES (37, 3, 81, '2024-09-22 22:45:58', 0);
INSERT INTO `user_follower` VALUES (38, 3, 18, '2024-09-22 22:45:58', 0);
INSERT INTO `user_follower` VALUES (39, 3, 38, '2024-09-22 22:45:58', 0);
INSERT INTO `user_follower` VALUES (40, 3, 35, '2024-09-22 22:45:58', 0);
INSERT INTO `user_follower` VALUES (41, 3, 80, '2024-09-22 22:45:58', 0);
INSERT INTO `user_follower` VALUES (42, 3, 77, '2024-09-22 22:45:58', 0);
INSERT INTO `user_follower` VALUES (43, 3, 70, '2024-09-22 23:18:58', 0);
INSERT INTO `user_follower` VALUES (44, 3, 36, '2024-09-22 23:18:58', 0);
INSERT INTO `user_follower` VALUES (45, 3, 21, '2024-09-22 23:18:58', 0);
INSERT INTO `user_follower` VALUES (46, 3, 71, '2024-09-22 23:18:58', 0);
INSERT INTO `user_follower` VALUES (47, 3, 31, '2024-09-22 23:18:58', 0);
INSERT INTO `user_follower` VALUES (48, 3, 41, '2024-09-22 23:18:58', 0);
INSERT INTO `user_follower` VALUES (49, 3, 23, '2024-09-22 23:18:58', 0);
INSERT INTO `user_follower` VALUES (50, 3, 94, '2024-09-22 23:18:58', 0);
INSERT INTO `user_follower` VALUES (51, 3, 7, '2024-09-22 23:18:58', 0);
INSERT INTO `user_follower` VALUES (52, 3, 44, '2024-09-22 23:18:58', 0);
INSERT INTO `user_follower` VALUES (53, 3, 4, '2024-09-22 23:18:58', 0);
INSERT INTO `user_follower` VALUES (54, 3, 82, '2024-09-22 23:18:58', 0);
INSERT INTO `user_follower` VALUES (55, 3, 64, '2024-09-22 23:18:58', 0);
INSERT INTO `user_follower` VALUES (56, 4, 2, '2024-10-09 23:51:51', 0);
INSERT INTO `user_follower` VALUES (57, 45, 2, '2024-10-09 23:52:31', 0);
INSERT INTO `user_follower` VALUES (58, 5, 2, '2024-10-14 10:55:03', 0);
INSERT INTO `user_follower` VALUES (59, 1, 2, '2024-11-24 20:39:20', 0);
INSERT INTO `user_follower` VALUES (60, 44, 2, '2025-01-11 12:43:45', 0);
INSERT INTO `user_follower` VALUES (61, 43, 2, '2025-01-11 23:42:01', 0);
INSERT INTO `user_follower` VALUES (62, 36, 2, '2025-01-12 23:36:50', 0);
INSERT INTO `user_follower` VALUES (63, 39, 2, '2025-01-13 16:00:31', 0);
INSERT INTO `user_follower` VALUES (64, 40, 2, '2025-01-13 16:00:38', 0);
INSERT INTO `user_follower` VALUES (65, 41, 2, '2025-01-13 16:00:40', 0);
INSERT INTO `user_follower` VALUES (66, 38, 2, '2025-01-13 16:00:41', 0);
INSERT INTO `user_follower` VALUES (67, 37, 2, '2025-01-13 16:00:43', 0);

SET FOREIGN_KEY_CHECKS = 1;
