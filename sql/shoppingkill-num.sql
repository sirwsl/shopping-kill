/*
 Navicat Premium Data Transfer

 Source Server         :
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : :3306
 Source Schema         : shoppingkill

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 08/12/2020 11:10:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sku_id` bigint(20) NOT NULL COMMENT 'SkuId',
  `love` int(11) NOT NULL DEFAULT 0 COMMENT '收藏人数',
  `sell_num` int(11) NULL DEFAULT 0 COMMENT '已售数量',
  `total_num` int(11) NOT NULL DEFAULT 0 COMMENT '总数',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '销售价格',
  `start_time` datetime(0) NOT NULL COMMENT '开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '结束时间',
  `creat_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_activity
-- ----------------------------
INSERT INTO `t_activity` VALUES (1, 1, 10, 10, 200, 99.00, '2020-12-05 08:48:03', '2021-01-03 08:48:08', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (2, 2, 9, 2, 200, 89.00, '2020-12-05 08:48:03', '2021-01-03 08:48:08', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (3, 3, 8, 32, 200, 98.00, '2020-12-05 08:48:03', '2021-01-03 08:48:08', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (4, 1, 50, 34, 200, 88.00, '2020-02-02 12:22:22', '2020-02-12 12:22:22', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (5, 2, 20, 56, 200, 78.00, '2020-02-02 12:22:22', '2020-02-12 12:22:22', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (6, 3, 33, 20, 200, 56.00, '2020-02-02 12:22:22', '2020-02-12 12:22:22', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (7, 1, 66, 65, 200, 1000.00, '2020-12-30 00:00:00', '2021-08-12 12:00:00', '2020-11-30 08:48:15', '2020-12-08 10:23:56', 0);
INSERT INTO `t_activity` VALUES (8, 2, 78, 65, 100, 38.00, '2020-12-30 00:00:00', '2021-08-12 12:00:00', '2020-11-30 08:48:15', '2020-12-08 10:23:56', 0);
INSERT INTO `t_activity` VALUES (9, 3, 10, 3, 100, 55.00, '2020-12-30 00:00:00', '2021-08-12 12:00:00', '2020-11-30 08:48:15', '2020-12-08 10:23:56', 0);
INSERT INTO `t_activity` VALUES (10, 4, 85, 4, 200, 88.00, '2020-12-05 08:48:03', '2021-01-03 08:48:08', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (11, 5, 10, 32, 200, 66.00, '2020-12-05 08:48:03', '2021-01-03 08:48:08', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (12, 6, 66, 23, 100, 77.00, '2020-12-05 08:48:03', '2021-01-03 08:48:08', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (13, 4, 78, 2, 100, 88.00, '2020-02-02 12:22:22', '2020-02-12 12:22:22', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (14, 5, 10, 23, 100, 119.00, '2020-02-02 12:22:22', '2020-02-12 12:22:22', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (15, 6, 88, 7, 100, 139.00, '2020-02-02 12:22:22', '2020-02-12 12:22:22', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (16, 4, 10, 23, 100, 199.00, '2020-12-30 00:00:00', '2021-08-12 12:00:00', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (17, 5, 8, 2, 100, 99.00, '2020-12-30 00:00:00', '2021-08-12 12:00:00', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (18, 6, 6, 2, 100, 129.00, '2020-12-30 00:00:00', '2021-08-12 12:00:00', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (19, 7, 9, 3, 200, 89.00, '2020-12-05 08:48:03', '2021-01-03 08:48:08', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (20, 8, 8, 4, 200, 98.00, '2020-12-05 08:48:03', '2021-01-03 08:48:08', '2020-11-30 08:48:15', '2020-11-29 08:48:19', 0);
INSERT INTO `t_activity` VALUES (21, 7, 50, 64, 200, 88.00, '2020-02-02 12:22:22', '2020-02-12 12:22:22', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (22, 8, 20, 34, 200, 78.00, '2020-02-02 12:22:22', '2020-02-12 12:22:22', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (23, 7, 33, 20, 200, 56.00, '2020-12-30 00:00:00', '2021-08-12 12:00:00', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (24, 8, 66, 65, 200, 49.00, '2020-12-30 00:00:00', '2021-08-12 12:00:00', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (25, 1, 78, 76, 200, 38.00, '2020-12-16 00:00:00', '2021-08-12 12:00:00', '2020-11-30 08:48:15', '2020-11-30 19:08:08', 0);
INSERT INTO `t_activity` VALUES (26, 2, 10, 4, 200, 55.00, '2020-12-16 00:00:00', '2021-08-12 12:00:00', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (27, 3, 85, 34, 200, 88.00, '2020-12-04 00:00:00', '2020-12-02 08:48:08', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (28, 1, 10, 23, 200, 66.00, '2020-12-04 00:00:00', '2020-12-02 08:48:08', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (29, 2, 66, 23, 100, 77.00, '2020-02-02 12:22:22', '2020-02-02 12:22:22', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (30, 3, 78, 23, 100, 88.00, '2020-02-02 12:22:22', '2020-02-02 12:22:22', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (31, 4, 10, 4, 100, 119.00, '2020-11-02 08:48:03', '2020-12-06 15:18:38', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (32, 5, 88, 7, 100, 139.00, '2020-11-02 08:48:03', '2020-12-06 15:18:38', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (33, 6, 10, 67, 100, 199.00, '2020-11-02 08:48:03', '2020-12-06 15:18:38', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (34, 4, 8, 98, 100, 99.00, '2020-11-02 08:48:03', '2020-12-06 15:18:38', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (35, 7, 6, 10, 100, 129.00, '2020-11-02 08:48:03', '2020-12-06 15:18:38', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 0);
INSERT INTO `t_activity` VALUES (36, 8, 0, 565, 0, 0.00, '2020-11-02 08:48:03', '2020-12-06 15:18:38', '2020-12-05 15:18:45', '2020-12-05 15:18:50', 0);
INSERT INTO `t_activity` VALUES (37, 1, 0, 0, 498, 1695.00, '2020-12-09 00:00:00', '2020-12-31 00:00:00', '2020-12-08 10:30:20', '2020-12-08 10:30:20', 0);
INSERT INTO `t_activity` VALUES (38, 2, 0, 0, 500, 1695.00, '2020-12-09 00:00:00', '2020-12-31 00:00:00', '2020-12-08 10:30:20', '2020-12-08 10:30:20', 0);
INSERT INTO `t_activity` VALUES (39, 3, 0, 0, 330, 1695.00, '2020-12-09 00:00:00', '2020-12-31 00:00:00', '2020-12-08 10:30:20', '2020-12-08 10:30:20', 0);
INSERT INTO `t_activity` VALUES (40, 4, 0, 0, 600, 8766.00, '2020-12-11 00:00:00', '2020-12-30 00:00:00', '2020-12-08 10:32:28', '2020-12-08 10:32:28', 0);
INSERT INTO `t_activity` VALUES (41, 5, 0, 0, 600, 8766.00, '2020-12-11 00:00:00', '2020-12-30 00:00:00', '2020-12-08 10:32:28', '2020-12-08 10:32:28', 0);
INSERT INTO `t_activity` VALUES (42, 6, 0, 0, 600, 8766.00, '2020-12-11 00:00:00', '2020-12-30 00:00:00', '2020-12-08 10:32:28', '2020-12-08 10:32:28', 0);
INSERT INTO `t_activity` VALUES (43, 1, 0, 0, 498, 1695.00, '2020-12-30 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:40:11', '2020-12-08 10:40:11', 0);
INSERT INTO `t_activity` VALUES (44, 2, 0, 0, 500, 1695.00, '2020-12-30 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:40:11', '2020-12-08 10:40:11', 0);
INSERT INTO `t_activity` VALUES (45, 3, 0, 0, 330, 1695.00, '2020-12-30 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:40:11', '2020-12-08 10:40:11', 0);
INSERT INTO `t_activity` VALUES (46, 1, 0, 0, 498, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:44:38', '2020-12-08 10:44:38', 0);
INSERT INTO `t_activity` VALUES (47, 2, 0, 0, 500, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:44:38', '2020-12-08 10:44:38', 0);
INSERT INTO `t_activity` VALUES (48, 3, 0, 0, 330, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:44:38', '2020-12-08 10:44:38', 0);
INSERT INTO `t_activity` VALUES (49, 1, 0, 0, 498, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:46:40', '2020-12-08 10:46:40', 0);
INSERT INTO `t_activity` VALUES (50, 2, 0, 0, 500, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:46:40', '2020-12-08 10:46:40', 0);
INSERT INTO `t_activity` VALUES (51, 3, 0, 0, 330, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:46:40', '2020-12-08 10:46:40', 0);
INSERT INTO `t_activity` VALUES (52, 1, 0, 0, 498, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:47:21', '2020-12-08 10:47:21', 0);
INSERT INTO `t_activity` VALUES (53, 2, 0, 0, 500, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:47:21', '2020-12-08 10:47:21', 0);
INSERT INTO `t_activity` VALUES (54, 3, 0, 0, 330, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:47:21', '2020-12-08 10:47:21', 0);
INSERT INTO `t_activity` VALUES (55, 1, 0, 0, 498, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:53:02', '2020-12-08 10:53:02', 0);
INSERT INTO `t_activity` VALUES (56, 2, 0, 0, 500, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:53:02', '2020-12-08 10:53:02', 0);
INSERT INTO `t_activity` VALUES (57, 3, 0, 0, 330, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:53:02', '2020-12-08 10:53:02', 0);
INSERT INTO `t_activity` VALUES (58, 1, 0, 0, 498, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:53:32', '2020-12-08 10:53:32', 0);
INSERT INTO `t_activity` VALUES (59, 2, 0, 0, 500, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:53:32', '2020-12-08 10:53:32', 0);
INSERT INTO `t_activity` VALUES (60, 3, 0, 0, 330, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:53:32', '2020-12-08 10:53:32', 0);
INSERT INTO `t_activity` VALUES (61, 1, 0, 0, 498, 1695.00, '2020-12-22 00:00:00', '2020-12-31 00:00:00', '2020-12-08 10:57:17', '2020-12-08 10:57:17', 0);
INSERT INTO `t_activity` VALUES (62, 2, 0, 0, 500, 1695.00, '2020-12-22 00:00:00', '2020-12-31 00:00:00', '2020-12-08 10:57:17', '2020-12-08 10:57:17', 0);
INSERT INTO `t_activity` VALUES (63, 3, 0, 0, 330, 1695.00, '2020-12-22 00:00:00', '2020-12-31 00:00:00', '2020-12-08 10:57:17', '2020-12-08 10:57:17', 0);
INSERT INTO `t_activity` VALUES (64, 1, 0, 0, 498, 1695.00, '2020-12-30 00:00:00', '2021-12-16 00:00:00', '2020-12-08 11:04:25', '2020-12-08 11:04:25', 0);
INSERT INTO `t_activity` VALUES (65, 2, 0, 0, 500, 1695.00, '2020-12-30 00:00:00', '2021-12-16 00:00:00', '2020-12-08 11:04:25', '2020-12-08 11:04:25', 0);
INSERT INTO `t_activity` VALUES (66, 3, 0, 0, 330, 1695.00, '2020-12-30 00:00:00', '2021-12-16 00:00:00', '2020-12-08 11:04:25', '2020-12-08 11:04:25', 0);

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人姓名',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人电话',
  `address` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `address_num` int(11) NULL DEFAULT NULL COMMENT '邮编',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否是默认的',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收货地址' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_address
-- ----------------------------
INSERT INTO `t_address` VALUES (1, 1, '王世磊', '1562253547', '云南大力', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);
INSERT INTO `t_address` VALUES (2, 1, '张三', '1562253547', '云南大力', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);
INSERT INTO `t_address` VALUES (3, 1, '李四', '1562253547', '云南大力', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);
INSERT INTO `t_address` VALUES (4, 1, '张八九', '1562253547', '云南大力', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);
INSERT INTO `t_address` VALUES (5, 1, '刘教授', '1562253547', '丽江', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);
INSERT INTO `t_address` VALUES (6, 1, '小姐姐', '1562253547', '山东', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);
INSERT INTO `t_address` VALUES (7, 1, '阿里啥子', '1562253547', '昆明', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);
INSERT INTO `t_address` VALUES (8, 1, '王世磊', '1562253547', '云南昆明', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `sex` int(11) NOT NULL COMMENT '性别',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `mail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '家庭住址',
  `we_chat` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '跟新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否离职',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (56, 'wsl', 'sirwsl', '123', 0, '532923199701161916', '18314263373', 'test.jpg', 'sirwsl@163.com', '云南省祥云县刘厂镇王家庄村7组91号', NULL, '2020-11-16 13:17:27', '2020-11-16 13:17:27', 0);

-- ----------------------------
-- Table structure for t_advertise
-- ----------------------------
DROP TABLE IF EXISTS `t_advertise`;
CREATE TABLE `t_advertise`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '广告id',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告图片链接',
  `target_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目标url',
  `start_time` datetime(0) NOT NULL COMMENT '开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '结束时间',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告内容' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_advertise
-- ----------------------------

-- ----------------------------
-- Table structure for t_after_sales
-- ----------------------------
DROP TABLE IF EXISTS `t_after_sales`;
CREATE TABLE `t_after_sales`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `admin_id` bigint(20) NULL DEFAULT NULL COMMENT '处理人id',
  `detail` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '售后内容',
  `result_detail` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理内容',
  `type` int(11) NOT NULL COMMENT '处理类型 （3-退货退款 2-换货 1-仅退款）',
  `deal_time` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `result` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否解决（0-未解决 1-已解决  默认0）',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '售后记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_after_sales
-- ----------------------------
INSERT INTO `t_after_sales` VALUES (1, '1323456789876543', 1, '申请退货退款应为所见所闻i哦发红发', '这是一件换货的商品', 2, '2020-11-22 23:28:34', 1, '2020-11-20 23:03:14', '2020-11-22 23:28:34', 0);
INSERT INTO `t_after_sales` VALUES (2, '123124234125', 1, '申请退款，因为测试太垃圾', '这是一件换货的商品', 2, '2020-11-22 22:59:41', 0, '2020-11-20 23:03:14', '2020-11-22 22:59:41', 0);
INSERT INTO `t_after_sales` VALUES (3, '23124234125', 1, '申请换货啦啦啦啦啦啦啦啦', '这是一件换货的商品', 2, '2020-11-22 23:28:04', 1, '2020-11-20 23:03:14', '2020-11-22 23:28:04', 0);

-- ----------------------------
-- Table structure for t_appraisal
-- ----------------------------
DROP TABLE IF EXISTS `t_appraisal`;
CREATE TABLE `t_appraisal`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价内容',
  `img_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价图片',
  `grade` int(11) NOT NULL DEFAULT 5 COMMENT '评价星级（1-5）',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间（评价时间）',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评价表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_appraisal
-- ----------------------------
INSERT INTO `t_appraisal` VALUES (1, 1, 1, '测试评价1', NULL, 5, '2020-11-18 13:38:15', '2020-11-18 13:38:19', 0);
INSERT INTO `t_appraisal` VALUES (2, 2, 2, '评价内容', NULL, 2, '2020-11-18 13:38:30', '2020-11-18 13:38:33', 0);
INSERT INTO `t_appraisal` VALUES (3, 1, 1, '测试评价1', NULL, 5, '2020-11-18 13:38:15', '2020-11-18 13:38:19', 0);
INSERT INTO `t_appraisal` VALUES (4, 2, 1, '测试评价1', NULL, 3, '2020-11-18 13:38:15', '2020-11-18 13:38:19', 0);
INSERT INTO `t_appraisal` VALUES (5, 3, 3, '测试评价1', NULL, 4, '2020-11-18 13:38:15', '2020-11-18 13:38:19', 0);
INSERT INTO `t_appraisal` VALUES (6, 6, 6, '测试评价1', NULL, 5, '2020-11-18 13:38:15', '2020-11-18 13:38:19', 0);

-- ----------------------------
-- Table structure for t_cart
-- ----------------------------
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `sku_id` bigint(20) NOT NULL COMMENT 'sku_id',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间（加入时间）',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态(0-正常 1-已失效)',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_cart
-- ----------------------------

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名',
  `type_id` int(11) NULL DEFAULT NULL COMMENT '类别id',
  `img_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `shelf` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否上架',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `spreadPrice` decimal(10, 2) NULL DEFAULT NULL COMMENT '差价',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES (1, '手机', 1, '/url/img/img.jpg', 0, '这就是一个手机，一个很牛逼的手机', NULL, '2020-11-17 14:27:07', '2020-11-21 14:27:11', 0);
INSERT INTO `t_goods` VALUES (2, '电脑', 1, 'https://www.wslhome.top/img/imgs/asdasasdf.jpg', 1, '电脑电脑电脑电脑电脑', NULL, '2020-11-17 14:28:26', '2020-11-20 14:02:14', 0);
INSERT INTO `t_goods` VALUES (3, '充电宝', 2, 'https://wslhome.top/test/upload/test.jpg', 1, '充电宝', NULL, '2020-11-19 14:30:05', '2020-11-20 14:06:02', 0);
INSERT INTO `t_goods` VALUES (4, '手机壳', 2, 'img/jeisjfoiaw.jpeg', 0, '这就是个测试手机壳', NULL, '2020-11-17 14:30:52', '2020-11-17 14:30:54', 0);
INSERT INTO `t_goods` VALUES (5, 'test1', 1, 'http://static.wslhome.top/goods/8bba6198-91a8-4389-9488-e43fec38358d.jpg', 0, '测试描述', NULL, '2020-11-20 11:12:34', '2020-11-20 13:42:56', 0);

-- ----------------------------
-- Table structure for t_limit_list
-- ----------------------------
DROP TABLE IF EXISTS `t_limit_list`;
CREATE TABLE `t_limit_list`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` int(11) NOT NULL COMMENT '类型(0-手机号 1-ip)',
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '号码',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态（0-黑名单 1-白名单）',
  `start_time` datetime(0) NOT NULL COMMENT '开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '结束时间',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '黑名单与白名单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_limit_list
-- ----------------------------

-- ----------------------------
-- Table structure for t_loggers
-- ----------------------------
DROP TABLE IF EXISTS `t_loggers`;
CREATE TABLE `t_loggers`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作内容',
  `man_id` bigint(20) NOT NULL COMMENT '操作人id',
  `type` int(11) NOT NULL COMMENT '操作类型(0-用户 1-管理员)',
  `grade` int(11) NULL DEFAULT NULL COMMENT '等级(0-正常 1-良好 2-严重 3-极其严重)',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间(操作时间)',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 124 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_loggers
-- ----------------------------
INSERT INTO `t_loggers` VALUES (12, '添加管理员->[操作参数：王世磊]->[Class：AdminServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-11-16 10:58:28', '2020-11-16 10:58:28', 0);
INSERT INTO `t_loggers` VALUES (13, '添加管理员->[操作参数：王世磊]->[Class：AdminServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-11-16 11:42:28', '2020-11-16 11:42:28', 0);
INSERT INTO `t_loggers` VALUES (14, '删除管理员->[操作参数：48]->[Class：AdminServiceImpl]', 1, 1, 3, '0:0:0:0:0:0:0:1', '2020-11-16 11:44:29', '2020-11-16 11:44:29', 0);
INSERT INTO `t_loggers` VALUES (15, '删除管理员->[操作参数：48]->[Class：AdminServiceImpl]', 1, 1, 3, '0:0:0:0:0:0:0:1', '2020-11-16 11:47:15', '2020-11-16 11:47:15', 0);
INSERT INTO `t_loggers` VALUES (16, '删除管理员->[操作参数：48]->[Class：AdminServiceImpl]', 1, 1, 3, '0:0:0:0:0:0:0:1', '2020-11-16 11:50:18', '2020-11-16 11:50:18', 0);
INSERT INTO `t_loggers` VALUES (17, '删除管理员->[操作参数：48]->[Class：AdminServiceImpl]', 1, 1, 3, '0:0:0:0:0:0:0:1', '2020-11-16 11:55:02', '2020-11-16 11:55:02', 0);
INSERT INTO `t_loggers` VALUES (18, '删除管理员->[操作参数：48]->[Class：AdminServiceImpl]', 1, 1, 3, '0:0:0:0:0:0:0:1', '2020-11-16 12:36:58', '2020-11-16 12:36:58', 0);
INSERT INTO `t_loggers` VALUES (19, '删除管理员->[操作参数：48]->[Class：AdminServiceImpl]', 1, 1, 3, '0:0:0:0:0:0:0:1', '2020-11-16 12:38:12', '2020-11-16 12:38:12', 0);
INSERT INTO `t_loggers` VALUES (20, '添加发布订阅内容->[操作参数：45]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 12:42:16', '2020-11-16 12:42:16', 0);
INSERT INTO `t_loggers` VALUES (21, '添加发布订阅内容->[操作参数：48]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 12:50:12', '2020-11-16 12:50:12', 0);
INSERT INTO `t_loggers` VALUES (22, '添加管理员->[操作参数：王世磊]->[Class：AdminServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-11-16 12:51:34', '2020-11-16 12:51:34', 0);
INSERT INTO `t_loggers` VALUES (23, '添加发布订阅内容->[操作参数：49]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 12:54:09', '2020-11-16 12:54:09', 0);
INSERT INTO `t_loggers` VALUES (24, '添加发布订阅内容->[操作参数：52]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 12:56:31', '2020-11-16 12:56:31', 0);
INSERT INTO `t_loggers` VALUES (25, '添加发布订阅内容->[操作参数：56]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 13:00:38', '2020-11-16 13:00:38', 0);
INSERT INTO `t_loggers` VALUES (26, '添加发布订阅内容->[操作参数：63]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 13:15:19', '2020-11-16 13:15:19', 0);
INSERT INTO `t_loggers` VALUES (27, '添加管理员->[操作参数：王世磊]->[Class：AdminServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-11-16 13:17:15', '2020-11-16 13:17:15', 0);
INSERT INTO `t_loggers` VALUES (28, '添加管理员->[操作参数：王世磊]->[Class：AdminServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-11-16 13:17:27', '2020-11-16 13:17:27', 0);
INSERT INTO `t_loggers` VALUES (29, '添加发布订阅内容->[操作参数：69]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 13:32:05', '2020-11-16 13:32:05', 0);
INSERT INTO `t_loggers` VALUES (30, '添加发布订阅内容->[操作参数：71]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 13:42:22', '2020-11-16 13:42:22', 0);
INSERT INTO `t_loggers` VALUES (31, '添加发布订阅内容->[操作参数：72]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 13:48:59', '2020-11-16 13:48:59', 0);
INSERT INTO `t_loggers` VALUES (32, '添加发布订阅内容->[操作参数：73]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 13:49:01', '2020-11-16 13:49:01', 0);
INSERT INTO `t_loggers` VALUES (33, '添加发布订阅内容->[操作参数：74]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 13:49:01', '2020-11-16 13:49:01', 0);
INSERT INTO `t_loggers` VALUES (34, '添加发布订阅内容->[操作参数：75]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 13:49:02', '2020-11-16 13:49:02', 0);
INSERT INTO `t_loggers` VALUES (35, '添加发布订阅内容->[操作参数：76]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 13:49:37', '2020-11-16 13:49:37', 0);
INSERT INTO `t_loggers` VALUES (36, '添加发布订阅内容->[操作参数：77]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 13:50:06', '2020-11-16 13:50:06', 0);
INSERT INTO `t_loggers` VALUES (37, '添加发布订阅内容->[操作参数：78]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 13:52:26', '2020-11-16 13:52:26', 0);
INSERT INTO `t_loggers` VALUES (38, '添加发布订阅内容->[操作参数：79]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 13:54:19', '2020-11-16 13:54:19', 0);
INSERT INTO `t_loggers` VALUES (39, '添加发布订阅内容->[操作参数：80]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 13:59:48', '2020-11-16 13:59:48', 0);
INSERT INTO `t_loggers` VALUES (40, '添加发布订阅内容->[操作参数：81]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 14:01:44', '2020-11-16 14:01:44', 0);
INSERT INTO `t_loggers` VALUES (41, '添加发布订阅内容->[操作参数：82]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 14:01:48', '2020-11-16 14:01:48', 0);
INSERT INTO `t_loggers` VALUES (42, '添加发布订阅内容->[操作参数：83]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 14:01:50', '2020-11-16 14:01:50', 0);
INSERT INTO `t_loggers` VALUES (43, '添加发布订阅内容->[操作参数：84]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 14:03:55', '2020-11-16 14:03:55', 0);
INSERT INTO `t_loggers` VALUES (44, '添加发布订阅内容->[操作参数：85]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 14:03:58', '2020-11-16 14:03:58', 0);
INSERT INTO `t_loggers` VALUES (45, '添加发布订阅内容->[操作参数：86]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 14:04:00', '2020-11-16 14:04:00', 0);
INSERT INTO `t_loggers` VALUES (46, '添加发布订阅内容->[操作参数：87]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 14:08:28', '2020-11-16 14:08:28', 0);
INSERT INTO `t_loggers` VALUES (47, '添加发布订阅内容->[操作参数：88]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 14:18:00', '2020-11-16 14:18:00', 0);
INSERT INTO `t_loggers` VALUES (48, '添加发布订阅内容->[操作参数：89]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 14:18:01', '2020-11-16 14:18:01', 0);
INSERT INTO `t_loggers` VALUES (49, '添加发布订阅内容->[操作参数：90]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 14:18:03', '2020-11-16 14:18:03', 0);
INSERT INTO `t_loggers` VALUES (50, '修改会员信息->[操作参数：1]->[Class：UserServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-17 10:37:23', '2020-11-17 10:37:23', 0);
INSERT INTO `t_loggers` VALUES (51, '删除会员->[操作参数：4]->[Class：UserServiceImpl]', 1, 1, 3, '0:0:0:0:0:0:0:1', '2020-11-17 10:38:21', '2020-11-17 10:38:21', 0);
INSERT INTO `t_loggers` VALUES (52, '删除评价信息->[操作参数：5]->[Class：AppraisalServiceImpl]', 1, 1, 3, '0:0:0:0:0:0:0:1', '2020-11-18 15:36:47', '2020-11-18 15:36:47', 0);
INSERT INTO `t_loggers` VALUES (53, '添加商品类别->[操作参数：1]->[Class：TypesServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-18 17:13:07', '2020-11-18 17:13:07', 0);
INSERT INTO `t_loggers` VALUES (54, '添加商品类别->[操作参数：2]->[Class：TypesServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-18 17:13:50', '2020-11-18 17:13:50', 0);
INSERT INTO `t_loggers` VALUES (55, '添加商品类别->[操作参数：3]->[Class：TypesServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-18 17:13:54', '2020-11-18 17:13:54', 0);
INSERT INTO `t_loggers` VALUES (56, '添加商品类别->[操作参数：4]->[Class：TypesServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-18 17:13:58', '2020-11-18 17:13:58', 0);
INSERT INTO `t_loggers` VALUES (57, '删除商品类别->[操作参数：1]->[Class：TypesServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-18 17:18:58', '2020-11-18 17:18:58', 0);
INSERT INTO `t_loggers` VALUES (58, '删除商品类别->[操作参数：1]->[Class：TypesServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-18 17:19:24', '2020-11-18 17:19:24', 0);
INSERT INTO `t_loggers` VALUES (59, '添加商品类别->[操作参数：123]->[Class：TypesServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-18 17:22:42', '2020-11-18 17:22:42', 0);
INSERT INTO `t_loggers` VALUES (60, '更新商品类别->[操作参数：2]->[Class：TypesServiceImpl]', 1, 1, 0, '0:0:0:0:0:0:0:1', '2020-11-18 17:35:38', '2020-11-18 17:35:38', 0);
INSERT INTO `t_loggers` VALUES (61, '商品更新->[操作参数：5]->[Class：GoodsServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-20 13:40:39', '2020-11-20 13:40:39', 0);
INSERT INTO `t_loggers` VALUES (62, '商品更新->[操作参数：5]->[Class：GoodsServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-20 13:42:56', '2020-11-20 13:42:56', 0);
INSERT INTO `t_loggers` VALUES (63, '商品删除->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 2, '0:0:0:0:0:0:0:1', '2020-11-20 13:48:46', '2020-11-20 13:48:46', 0);
INSERT INTO `t_loggers` VALUES (64, '商品上架处理->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-20 13:52:52', '2020-11-20 13:52:52', 0);
INSERT INTO `t_loggers` VALUES (65, '商品上架处理->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-20 13:58:27', '2020-11-20 13:58:27', 0);
INSERT INTO `t_loggers` VALUES (66, '商品上架处理->[操作参数：2]->[Class：GoodsServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-20 13:58:32', '2020-11-20 13:58:32', 0);
INSERT INTO `t_loggers` VALUES (67, '商品上架处理->[操作参数：2]->[Class：GoodsServiceImpl]', 1, 0, 1, '0:0:0:0:0:0:0:1', '2020-11-20 13:58:37', '2020-11-20 13:58:37', 0);
INSERT INTO `t_loggers` VALUES (68, '商品上架处理->[操作参数：2]->[Class：GoodsServiceImpl]', 1, 0, 1, '0:0:0:0:0:0:0:1', '2020-11-20 13:58:38', '2020-11-20 13:58:38', 0);
INSERT INTO `t_loggers` VALUES (69, '商品上架处理->[操作参数：2]->[Class：GoodsServiceImpl]', 1, 0, 1, '0:0:0:0:0:0:0:1', '2020-11-20 13:58:38', '2020-11-20 13:58:38', 0);
INSERT INTO `t_loggers` VALUES (70, '商品上架处理->[操作参数：2]->[Class：GoodsServiceImpl]', 1, 0, 1, '0:0:0:0:0:0:0:1', '2020-11-20 13:58:39', '2020-11-20 13:58:39', 0);
INSERT INTO `t_loggers` VALUES (71, '商品上架处理->[操作参数：3]->[Class：GoodsServiceImpl]', 1, 0, 1, '0:0:0:0:0:0:0:1', '2020-11-20 13:58:54', '2020-11-20 13:58:54', 0);
INSERT INTO `t_loggers` VALUES (72, '商品上架处理->[操作参数：3]->[Class：GoodsServiceImpl]', 1, 0, 1, '0:0:0:0:0:0:0:1', '2020-11-20 13:59:55', '2020-11-20 13:59:55', 0);
INSERT INTO `t_loggers` VALUES (73, '商品上架处理->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 0, 1, '0:0:0:0:0:0:0:1', '2020-11-20 14:00:31', '2020-11-20 14:00:31', 0);
INSERT INTO `t_loggers` VALUES (74, '商品上架处理->[操作参数：2]->[Class：GoodsServiceImpl]', 1, 0, 1, '0:0:0:0:0:0:0:1', '2020-11-20 14:00:36', '2020-11-20 14:00:36', 0);
INSERT INTO `t_loggers` VALUES (75, '商品上架处理->[操作参数：2]->[Class：GoodsServiceImpl]', 1, 0, 1, '0:0:0:0:0:0:0:1', '2020-11-20 14:00:57', '2020-11-20 14:00:57', 0);
INSERT INTO `t_loggers` VALUES (76, '商品上架处理->[操作参数：2]->[Class：GoodsServiceImpl]', 1, 0, 1, '0:0:0:0:0:0:0:1', '2020-11-20 14:02:14', '2020-11-20 14:02:14', 0);
INSERT INTO `t_loggers` VALUES (77, '商品上架处理->[操作参数：3]->[Class：GoodsServiceImpl]', 1, 0, 1, '0:0:0:0:0:0:0:1', '2020-11-20 14:02:29', '2020-11-20 14:02:29', 0);
INSERT INTO `t_loggers` VALUES (78, '商品上架处理->[操作参数：3]->[Class：GoodsServiceImpl]', 1, 0, 1, '0:0:0:0:0:0:0:1', '2020-11-20 14:02:36', '2020-11-20 14:02:36', 0);
INSERT INTO `t_loggers` VALUES (79, '商品上架处理->[操作参数：3]->[Class：GoodsServiceImpl]', 1, 0, 1, '0:0:0:0:0:0:0:1', '2020-11-20 14:06:02', '2020-11-20 14:06:02', 0);
INSERT INTO `t_loggers` VALUES (80, '商品上架处理->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 0, 1, '0:0:0:0:0:0:0:1', '2020-11-20 14:06:13', '2020-11-20 14:06:13', 0);
INSERT INTO `t_loggers` VALUES (81, '售后处理->[操作参数：1]->[Class：AfterSalesServiceImpl]', 1, 0, 1, '127.0.0.1', '2020-11-22 18:15:10', '2020-11-22 18:15:10', 0);
INSERT INTO `t_loggers` VALUES (82, '售后处理->[操作参数：1]->[Class：AfterSalesServiceImpl]', 1, 0, 1, '127.0.0.1', '2020-11-22 18:20:21', '2020-11-22 18:20:21', 0);
INSERT INTO `t_loggers` VALUES (83, '售后处理->[操作参数：2]->[Class：AfterSalesServiceImpl]', 1, 0, 1, '0:0:0:0:0:0:0:1', '2020-11-22 22:59:42', '2020-11-22 22:59:42', 0);
INSERT INTO `t_loggers` VALUES (84, '售后处理->[操作参数：2]->[Class：AfterSalesServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-22 23:24:50', '2020-11-22 23:24:50', 0);
INSERT INTO `t_loggers` VALUES (85, '售后处理->[操作参数：1]->[Class：AfterSalesServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-22 23:25:21', '2020-11-22 23:25:21', 0);
INSERT INTO `t_loggers` VALUES (86, '售后处理->[操作参数：3]->[Class：AfterSalesServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-22 23:25:29', '2020-11-22 23:25:29', 0);
INSERT INTO `t_loggers` VALUES (87, '售后处理->[操作参数：3]->[Class：AfterSalesServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-22 23:28:04', '2020-11-22 23:28:04', 0);
INSERT INTO `t_loggers` VALUES (88, '售后处理->[操作参数：1]->[Class：AfterSalesServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-22 23:28:35', '2020-11-22 23:28:35', 0);
INSERT INTO `t_loggers` VALUES (89, '售后处理->[操作参数：1]->[Class：AfterSalesServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-23 20:38:09', '2020-11-23 20:38:09', 0);
INSERT INTO `t_loggers` VALUES (90, '物品出库->[操作参数：1231242888]->[Class：OrderServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-11-27 10:50:42', '2020-11-27 10:50:42', 0);
INSERT INTO `t_loggers` VALUES (91, '物品出库->[操作参数：1231242888]->[Class：OrderServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-11-27 11:00:40', '2020-11-27 11:00:40', 0);
INSERT INTO `t_loggers` VALUES (92, '物品出库->[操作参数：1231242888]->[Class：OrderServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-11-27 11:02:30', '2020-11-27 11:02:30', 0);
INSERT INTO `t_loggers` VALUES (93, '物品出库->[操作参数：1231242888]->[Class：OrderServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-11-27 11:03:00', '2020-11-27 11:03:00', 0);
INSERT INTO `t_loggers` VALUES (94, '删除一个活动->[操作参数：1]->[Class：ActivityServiceImpl]', 1, 1, 3, '0:0:0:0:0:0:0:1', '2020-11-30 18:35:05', '2020-11-30 18:35:05', 0);
INSERT INTO `t_loggers` VALUES (96, '删除一个活动->[操作参数：[Ljava.lang.Long;@61c57130]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 14:06:55', '2020-12-07 14:06:55', 0);
INSERT INTO `t_loggers` VALUES (97, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 15:04:04', '2020-12-07 15:04:04', 0);
INSERT INTO `t_loggers` VALUES (98, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 15:04:57', '2020-12-07 15:04:57', 0);
INSERT INTO `t_loggers` VALUES (99, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 15:05:07', '2020-12-07 15:05:07', 0);
INSERT INTO `t_loggers` VALUES (100, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 15:05:11', '2020-12-07 15:05:11', 0);
INSERT INTO `t_loggers` VALUES (101, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 15:16:43', '2020-12-07 15:16:43', 0);
INSERT INTO `t_loggers` VALUES (102, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 15:17:05', '2020-12-07 15:17:05', 0);
INSERT INTO `t_loggers` VALUES (103, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 15:19:08', '2020-12-07 15:19:08', 0);
INSERT INTO `t_loggers` VALUES (105, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 15:19:20', '2020-12-07 15:19:20', 0);
INSERT INTO `t_loggers` VALUES (106, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 15:21:04', '2020-12-07 15:21:04', 0);
INSERT INTO `t_loggers` VALUES (107, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 15:22:16', '2020-12-07 15:22:16', 0);
INSERT INTO `t_loggers` VALUES (108, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 15:25:54', '2020-12-07 15:25:54', 0);
INSERT INTO `t_loggers` VALUES (109, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 15:28:32', '2020-12-07 15:28:32', 0);
INSERT INTO `t_loggers` VALUES (110, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 15:29:25', '2020-12-07 15:29:25', 0);
INSERT INTO `t_loggers` VALUES (111, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 15:30:02', '2020-12-07 15:30:02', 0);
INSERT INTO `t_loggers` VALUES (112, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-07 15:30:23', '2020-12-07 15:30:23', 0);
INSERT INTO `t_loggers` VALUES (113, '添加/更新一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-08 10:23:57', '2020-12-08 10:23:57', 0);
INSERT INTO `t_loggers` VALUES (114, '添加/更新一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-08 10:30:20', '2020-12-08 10:30:20', 0);
INSERT INTO `t_loggers` VALUES (115, '添加/更新一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-08 10:32:28', '2020-12-08 10:32:28', 0);
INSERT INTO `t_loggers` VALUES (116, '添加/更新一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-08 10:40:12', '2020-12-08 10:40:12', 0);
INSERT INTO `t_loggers` VALUES (117, '添加/更新一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-08 10:44:38', '2020-12-08 10:44:38', 0);
INSERT INTO `t_loggers` VALUES (118, '添加/更新一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-08 10:46:41', '2020-12-08 10:46:41', 0);
INSERT INTO `t_loggers` VALUES (119, '添加/更新一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-08 10:47:21', '2020-12-08 10:47:21', 0);
INSERT INTO `t_loggers` VALUES (120, '添加/更新一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-08 10:53:03', '2020-12-08 10:53:03', 0);
INSERT INTO `t_loggers` VALUES (121, '添加/更新一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-08 10:53:33', '2020-12-08 10:53:33', 0);
INSERT INTO `t_loggers` VALUES (122, '添加/更新一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-08 10:57:17', '2020-12-08 10:57:17', 0);
INSERT INTO `t_loggers` VALUES (123, '添加/更新一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-08 11:04:25', '2020-12-08 11:04:25', 0);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` bigint(20) NOT NULL COMMENT '订单id',
  `user_id` bigint(20) NOT NULL COMMENT '买家id',
  `sku_id` bigint(20) NOT NULL COMMENT 'sku_id',
  `num` int(11) NOT NULL COMMENT '购买数量',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '下单时间',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `pay_type` int(11) NULL DEFAULT NULL COMMENT '支付类型 (0-支付宝 1-微信 2-银行...)',
  `address_id` bigint(20) NULL DEFAULT NULL COMMENT '地址id',
  `pay_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` int(11) NULL DEFAULT NULL COMMENT '订单状态(0-已下单未支付 1-已支付 2-已出库 3-已收货 4-已评价)',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (1231242888, 1, 2, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 1, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 1, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (2312421388, 2, 3, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 2, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 2, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (23124234125, 1, 1, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 3, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 3, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (123124234125, 2, 2, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 4, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 4, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (132345888888, 1, 3, 1, '2020-11-20 23:20:49', '2020-11-20 23:20:51', '2020-11-20 23:20:55', 1, 5, 50.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 2, '2020-11-20 23:21:25', '2020-11-20 23:21:31', 0);
INSERT INTO `t_order` VALUES (231242341251, 2, 4, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 6, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 3, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (2312142341251, 2, 5, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 1, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 4, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (2312423412515, 2, 2, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 2, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 1, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (2468464684186, 2, 1, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 3, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 2, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (23124234125122, 2, 2, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 4, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 3, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (24684646841186, 1, 1, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 5, 100.00, NULL, 4, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (56356345634563, 1, 2, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 4, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 1, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (231242341125122, 2, 3, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 1, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 2, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (246846468446554, 1, 4, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 2, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 1, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (1323456784563546, 2, 5, 1, '2020-11-20 23:20:49', '2020-11-20 23:20:51', '2020-11-20 23:20:55', 1, 4, 50.00, NULL, 3, '2020-11-20 23:21:25', '2020-11-20 23:21:31', 0);
INSERT INTO `t_order` VALUES (1323456789876543, 1, 1, 1, '2020-11-20 23:20:49', '2020-11-20 23:20:51', '2020-11-20 23:20:55', 1, 5, 50.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 4, '2020-11-20 23:21:25', '2020-11-20 23:21:31', 0);
INSERT INTO `t_order` VALUES (2312421113412515, 1, 2, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 1, 100.00, NULL, 5, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (2468464618446554, 1, 3, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 6, 100.00, NULL, 1, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (2468464684186456, 2, 6, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 5, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 2, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (23124234125122456, 2, 4, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 1, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 1, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (231242341125122456, 1, 5, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 2, 100.00, NULL, 3, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (246846468411186456, 1, 1, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 4, 100.00, NULL, 4, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (1323456789876545415, 1, 2, 1, '2020-11-20 23:20:49', '2020-11-20 23:20:51', '2020-11-20 23:20:55', 1, 3, 50.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 1, '2020-11-20 23:21:25', '2020-11-20 23:21:31', 0);
INSERT INTO `t_order` VALUES (2468464684465544564, 1, 2, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 3, 100.00, NULL, 3, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);

-- ----------------------------
-- Table structure for t_sku
-- ----------------------------
DROP TABLE IF EXISTS `t_sku`;
CREATE TABLE `t_sku`  (
  `id` bigint(20) NOT NULL COMMENT 'SKU_ID',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `attribute` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品属性',
  `img_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `real_price` decimal(10, 2) NOT NULL COMMENT '进价',
  `cost_price` decimal(10, 2) NOT NULL COMMENT '成本价',
  `sell_price` decimal(10, 2) NOT NULL COMMENT '售价',
  `num` int(11) NOT NULL COMMENT '数量',
  `warn_num` int(11) NOT NULL DEFAULT 10 COMMENT '预警量（默认10）',
  `exp_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '快递费用',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'SKU（商品的售卖产生的影响属性）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sku
-- ----------------------------
INSERT INTO `t_sku` VALUES (1, 1, '128G 6G内存 红色', 'upload/imigsdg/img.jpeg', 1256.00, 1200.00, 1695.00, 997, 20, 10.00, '2020-11-17 14:33:26', '2020-11-22 23:28:35', 0);
INSERT INTO `t_sku` VALUES (2, 1, '128G 4G内存 白色', 'upload/imigsdg/img.jpeg', 1256.00, 1200.00, 1695.00, 1000, 20, 10.00, '2020-11-17 14:33:26', '2020-11-22 22:59:42', 0);
INSERT INTO `t_sku` VALUES (3, 1, '128G 6G内存 黑色色', 'upload/imigsdg/img.jpeg', 1256.00, 1200.00, 1695.00, 661, 20, 10.00, '2020-11-17 14:33:26', '2020-11-22 23:28:35', 0);
INSERT INTO `t_sku` VALUES (4, 2, '1T硬盘 32G内存 2G独立显卡', 'upload/imigsdg/img.jpeg', 4999.00, 5899.00, 8766.00, 1200, 124, 200.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);
INSERT INTO `t_sku` VALUES (5, 2, '1T硬盘 32G内存 黑色', 'upload/isdfg/img.jpeg', 4999.00, 5899.00, 8766.00, 1200, 124, 200.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);
INSERT INTO `t_sku` VALUES (6, 2, '1T硬盘  内存', 'upload/isdfg/img.jpeg', 4999.00, 5899.00, 8766.00, 1200, 124, 200.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);
INSERT INTO `t_sku` VALUES (7, 3, '白色', 'upload/isdfg/img.jpeg', 59.00, 62.00, 99.00, 200, 5, 5.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);
INSERT INTO `t_sku` VALUES (8, 3, '黑色', 'upload/isdfg/img.jpeg', 59.00, 62.00, 99.00, 200, 5, 5.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);
INSERT INTO `t_sku` VALUES (9, 4, '粉色', 'upload/isdfg/img.jpeg', 59.00, 62.00, 99.00, 200, 5, 5.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);
INSERT INTO `t_sku` VALUES (10, 4, '紫色', 'upload/isdfg/img.jpeg', 59.00, 62.00, 99.00, 200, 5, 5.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);
INSERT INTO `t_sku` VALUES (11, 4, '蓝色', 'upload/isdfg/img.jpeg', 59.00, 62.00, 99.00, 200, 5, 5.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);
INSERT INTO `t_sku` VALUES (12, 4, '白色', 'upload/isdfg/img.jpeg', 59.00, 62.00, 99.00, 200, 5, 5.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);

-- ----------------------------
-- Table structure for t_subscriber
-- ----------------------------
DROP TABLE IF EXISTS `t_subscriber`;
CREATE TABLE `t_subscriber`  (
  `number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订阅者号',
  `type` int(11) NOT NULL COMMENT '订阅类型(1-手机订阅 0-邮件订阅)',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态(0-正常  1-取消)',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订阅者表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_subscriber
-- ----------------------------

-- ----------------------------
-- Table structure for t_subscription_history
-- ----------------------------
DROP TABLE IF EXISTS `t_subscription_history`;
CREATE TABLE `t_subscription_history`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `detail` varchar(999) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订阅内容',
  `type` int(11) NOT NULL COMMENT '类型(0-手机  1-邮件)',
  `admin_id` int(11) NOT NULL COMMENT '发布者id',
  `real_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否成功(0-失败  1-成功)',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间(发布时间)',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订阅历史表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_subscription_history
-- ----------------------------
INSERT INTO `t_subscription_history` VALUES (1, 'ceshibiaoti', '123456sa4d6848we', 0, 0, 0, '2020-11-16 14:38:59', '2020-11-16 14:39:03', 0);
INSERT INTO `t_subscription_history` VALUES (2, '而无法华为啊否和', '沙嗲u送佛送到会分开收到回复啊师傅阿飞阿喀琉斯的回复喀什东路发货阿萨', 0, 0, 0, '2020-11-16 14:39:26', '2020-11-16 14:39:29', 0);
INSERT INTO `t_subscription_history` VALUES (3, '而无法华为啊否和', '沙嗲u送佛送到会分开收到回复啊师傅阿飞阿喀琉斯的回复喀什东路发货阿萨', 0, 0, 0, '2020-11-16 14:39:26', '2020-11-16 14:39:29', 0);
INSERT INTO `t_subscription_history` VALUES (4, '而无法华为啊否和', '沙嗲u送佛送到会分开收到回复啊师傅阿飞阿喀琉斯的回复喀什东路发货阿萨', 0, 0, 0, '2020-11-16 14:39:26', '2020-11-16 14:39:29', 0);
INSERT INTO `t_subscription_history` VALUES (5, '而无法华为啊否和', '沙嗲u送佛送到会分开收到回复啊师傅阿飞阿喀琉斯的回复喀什东路发货阿萨', 0, 0, 0, '2020-11-20 14:39:26', '2020-11-20 14:39:29', 0);

-- ----------------------------
-- Table structure for t_types
-- ----------------------------
DROP TABLE IF EXISTS `t_types`;
CREATE TABLE `t_types`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名字',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品类别表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_types
-- ----------------------------
INSERT INTO `t_types` VALUES (1, '测试分类手机', '2020-11-18 17:13:07', '2020-11-18 17:13:07', 1);
INSERT INTO `t_types` VALUES (2, '456789', '2020-11-18 17:13:50', '2020-11-18 17:35:38', 0);
INSERT INTO `t_types` VALUES (3, '测试分类电脑2', '2020-11-18 17:13:54', '2020-11-18 17:13:54', 0);
INSERT INTO `t_types` VALUES (4, '测试分类电脑6', '2020-11-18 17:13:58', '2020-11-18 17:13:58', 0);
INSERT INTO `t_types` VALUES (5, '123', '2020-11-18 17:22:42', '2020-11-18 17:22:42', 0);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `img` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `signature` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `email` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `real_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `we_chat` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信',
  `apply` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付宝（暂时不用）',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '跟新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'sirwsl', '123', '测试昵称', 'img/ser/img', '0', NULL, '18314263373', 'sirwsl@163.com', '53292319970161916', '王世磊', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 10:37:23', 0);
INSERT INTO `t_user` VALUES (2, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18314263373', 'sirwsl@163.com', '53292319970161916', '王世磊', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);
INSERT INTO `t_user` VALUES (3, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18314263373', 'sirwsl@163.com', '53292319970161916', '王世磊', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);
INSERT INTO `t_user` VALUES (4, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18314263373', 'sirwsl@163.com', '53292319970161916', '王世磊', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 1);
INSERT INTO `t_user` VALUES (5, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18314263373', 'sirwsl@163.com', '53292319970161916', '王世磊', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);
INSERT INTO `t_user` VALUES (6, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18314263373', 'sirwsl@163.com', '53292319970161916', '王世磊', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);
INSERT INTO `t_user` VALUES (7, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18314263373', 'sirwsl@163.com', '53292319970161916', '王世磊', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);
INSERT INTO `t_user` VALUES (8, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18314263373', 'sirwsl@163.com', '53292319970161916', '王世磊', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);
INSERT INTO `t_user` VALUES (9, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18314263373', 'sirwsl@163.com', '53292319970161916', '王世磊', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);
INSERT INTO `t_user` VALUES (10, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18314263373', 'sirwsl@163.com', '53292319970161916', '王世磊', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);

SET FOREIGN_KEY_CHECKS = 1;
