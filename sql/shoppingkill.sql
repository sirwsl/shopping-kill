/*
 Navicat Premium Data Transfer

 Source Server         : my-test
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 121.196.187.160:3306
 Source Schema         : shoppingkill

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 04/11/2020 19:04:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity`  (
  `id` int(11) NOT NULL COMMENT '秒杀活动',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `start_time` datetime(0) NOT NULL COMMENT '开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '结束时间',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '跟新时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '限时抢购活动表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_activity
-- ----------------------------

-- ----------------------------
-- Table structure for t_activity_limit
-- ----------------------------
DROP TABLE IF EXISTS `t_activity_limit`;
CREATE TABLE `t_activity_limit`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `sku_id` int(11) NULL DEFAULT NULL COMMENT 'skuId',
  `activity_id` int(11) NULL DEFAULT NULL COMMENT '活动id',
  `limit_num` int(11) NULL DEFAULT 0 COMMENT '上限数量',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'SKU在秒杀时候限制上限数量' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_activity_limit
-- ----------------------------

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address`  (
  `id` int(11) NOT NULL COMMENT '地址id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人姓名',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人电话',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `address_num` int(11) NULL DEFAULT NULL COMMENT '邮编',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '是否是默认的',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收货地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_address
-- ----------------------------

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '家庭住址',
  `we_chat` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '跟新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否离职',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_card`(`id_card`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------

-- ----------------------------
-- Table structure for t_advertise
-- ----------------------------
DROP TABLE IF EXISTS `t_advertise`;
CREATE TABLE `t_advertise`  (
  `id` int(11) NOT NULL COMMENT '广告id',
  `img_url` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告图片链接',
  `target_url` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标url',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_advertise
-- ----------------------------

-- ----------------------------
-- Table structure for t_after_sales
-- ----------------------------
DROP TABLE IF EXISTS `t_after_sales`;
CREATE TABLE `t_after_sales`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `order_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `admin_id` int(11) NULL DEFAULT NULL COMMENT '管理员id',
  `detail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理内容',
  `type` int(1) NULL DEFAULT NULL COMMENT '处理类型 （0-退货退款 1-换货 3-仅退款）',
  `deal_time` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `result` tinyint(1) NULL DEFAULT 0 COMMENT '是否解决（0-未解决 1-已解决  默认0）',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '售后记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_after_sales
-- ----------------------------

-- ----------------------------
-- Table structure for t_appraisal
-- ----------------------------
DROP TABLE IF EXISTS `t_appraisal`;
CREATE TABLE `t_appraisal`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `order_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `detail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价内容',
  `img_url` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价图片',
  `grade` int(1) NULL DEFAULT 5 COMMENT '评价星级（1-5）',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（评价时间）',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评价表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_appraisal
-- ----------------------------

-- ----------------------------
-- Table structure for t_cart
-- ----------------------------
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart`  (
  `id` int(11) NOT NULL COMMENT '购物车id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `sku_id` int(11) NULL DEFAULT NULL COMMENT 'sku_id',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间（加入时间）',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态(0-正常 1-已失效)',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cart
-- ----------------------------

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `id` int(11) NOT NULL COMMENT '商品id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名',
  `type_id` int(11) NULL DEFAULT NULL COMMENT '类别id',
  `img_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `shelf` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否上架',
  `detail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_goods
-- ----------------------------

-- ----------------------------
-- Table structure for t_limit_list
-- ----------------------------
DROP TABLE IF EXISTS `t_limit_list`;
CREATE TABLE `t_limit_list`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `type` int(1) NOT NULL COMMENT '类型(0-手机号 1-ip)',
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '号码',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态（0-黑名单 1-白名单）',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `number`(`number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '黑名单与白名单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_limit_list
-- ----------------------------

-- ----------------------------
-- Table structure for t_loggers
-- ----------------------------
DROP TABLE IF EXISTS `t_loggers`;
CREATE TABLE `t_loggers`  (
  `id` int(11) NOT NULL COMMENT '日志id',
  `detail` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作内容',
  `man_id` int(11) NULL DEFAULT NULL COMMENT '操作人id',
  `type` int(1) NULL DEFAULT NULL COMMENT '操作类型(0-用户 1-管理员)',
  `grade` int(1) NULL DEFAULT NULL COMMENT '等级(0-正常 1-良好 2-严重 3-极其严重)',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间(操作时间)',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_loggers
-- ----------------------------

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `user_id` int(11) NOT NULL COMMENT '买家id',
  `sku_id` int(11) NOT NULL COMMENT 'sku_id',
  `num` int(11) NOT NULL COMMENT '购买数量',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '下单时间',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `pay_type` int(1) NULL DEFAULT NULL COMMENT '支付类型 (0-支付宝 1-微信 2-银行...)',
  `address_id` int(11) NULL DEFAULT NULL COMMENT '地址id',
  `pay_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `remark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` int(1) NULL DEFAULT NULL COMMENT '订单状态(0-已下单未支付 1-已支付 2-已出库 3-已收货 4-已评价)',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_sku
-- ----------------------------
DROP TABLE IF EXISTS `t_sku`;
CREATE TABLE `t_sku`  (
  `id` int(11) NOT NULL COMMENT 'SKU_ID',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `attribute` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品属性',
  `img_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `real_price` decimal(10, 2) NOT NULL COMMENT '进价',
  `cost_price` decimal(10, 2) NOT NULL COMMENT '成本价',
  `sell_price` decimal(10, 2) NOT NULL COMMENT '售价',
  `num` int(11) NOT NULL COMMENT '数量',
  `warn_num` int(10) NULL DEFAULT 10 COMMENT '预警量（默认10）',
  `exp_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '快递费用',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'SKU（商品的售卖产生的影响属性）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sku
-- ----------------------------

-- ----------------------------
-- Table structure for t_subscription
-- ----------------------------
DROP TABLE IF EXISTS `t_subscription`;
CREATE TABLE `t_subscription`  (
  `number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订阅者号',
  `type` int(1) NOT NULL COMMENT '订阅类型(0-手机订阅 1-邮件订阅)',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态(0-正常  1-取消)',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订阅者表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_subscription
-- ----------------------------

-- ----------------------------
-- Table structure for t_subscription_history
-- ----------------------------
DROP TABLE IF EXISTS `t_subscription_history`;
CREATE TABLE `t_subscription_history`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订阅内容',
  `type` int(1) NULL DEFAULT NULL COMMENT '类型(0-手机  1-邮件)',
  `admin_id` int(11) NULL DEFAULT NULL COMMENT '发布者id',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间(发布时间)',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订阅历史表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_subscription_history
-- ----------------------------

-- ----------------------------
-- Table structure for t_types
-- ----------------------------
DROP TABLE IF EXISTS `t_types`;
CREATE TABLE `t_types`  (
  `id` int(11) NOT NULL COMMENT '类别id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名字',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_types
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `img` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `signature` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `email` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `real_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `we_chat` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信',
  `apply` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付宝（暂时不用）',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '跟新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE COMMENT '手机号非重复',
  UNIQUE INDEX `id_card`(`id_card`) USING BTREE COMMENT '身份证非重复',
  UNIQUE INDEX `name`(`name`) USING BTREE COMMENT '账号非重复'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
