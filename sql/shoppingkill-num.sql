/*
 Navicat Premium Data Transfer

 Source Server         :
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           :
 Source Schema         : shoppingkill

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 18/12/2020 19:03:27
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
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

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
INSERT INTO `t_activity` VALUES (16, 4, 10, 23, 100, 199.00, '2020-12-30 00:00:00', '2021-08-12 12:00:00', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 1);
INSERT INTO `t_activity` VALUES (17, 5, 8, 2, 100, 99.00, '2020-12-30 00:00:00', '2021-08-12 12:00:00', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 1);
INSERT INTO `t_activity` VALUES (18, 6, 6, 2, 100, 129.00, '2020-12-30 00:00:00', '2021-08-12 12:00:00', '2020-11-30 08:48:15', '2020-11-30 08:48:19', 1);
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
INSERT INTO `t_activity` VALUES (40, 4, 0, 0, 600, 8766.00, '2020-12-11 00:00:00', '2020-12-30 00:00:00', '2020-12-08 10:32:28', '2020-12-08 10:32:28', 1);
INSERT INTO `t_activity` VALUES (41, 5, 0, 0, 600, 8766.00, '2020-12-11 00:00:00', '2020-12-30 00:00:00', '2020-12-08 10:32:28', '2020-12-08 10:32:28', 1);
INSERT INTO `t_activity` VALUES (42, 6, 0, 0, 600, 8766.00, '2020-12-11 00:00:00', '2020-12-30 00:00:00', '2020-12-08 10:32:28', '2020-12-08 10:32:28', 1);
INSERT INTO `t_activity` VALUES (43, 1, 0, 0, 498, 1695.00, '2020-12-30 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:40:11', '2020-12-08 10:40:11', 1);
INSERT INTO `t_activity` VALUES (44, 2, 0, 0, 500, 1695.00, '2020-12-30 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:40:11', '2020-12-08 10:40:11', 1);
INSERT INTO `t_activity` VALUES (45, 3, 0, 0, 330, 1695.00, '2020-12-30 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:40:11', '2020-12-08 10:40:11', 1);
INSERT INTO `t_activity` VALUES (46, 1, 0, 0, 498, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:44:38', '2020-12-08 10:44:38', 1);
INSERT INTO `t_activity` VALUES (47, 2, 0, 0, 500, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:44:38', '2020-12-08 10:44:38', 1);
INSERT INTO `t_activity` VALUES (48, 3, 0, 0, 330, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:44:38', '2020-12-08 10:44:38', 1);
INSERT INTO `t_activity` VALUES (49, 1, 0, 0, 249, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:46:40', '2020-12-10 09:35:13', 0);
INSERT INTO `t_activity` VALUES (50, 2, 0, 0, 25, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:46:40', '2020-12-10 09:35:13', 0);
INSERT INTO `t_activity` VALUES (51, 3, 0, 0, 16, 1695.00, '2020-12-16 00:00:00', '2021-12-08 00:00:00', '2020-12-08 10:46:40', '2020-12-10 09:35:13', 0);
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
INSERT INTO `t_activity` VALUES (67, 7, 0, 0, 100, 99.00, '2020-12-16 00:00:00', '2021-12-16 00:00:00', '2020-12-08 16:21:33', '2020-12-08 16:21:33', 1);
INSERT INTO `t_activity` VALUES (68, 8, 0, 0, 100, 99.00, '2020-12-16 00:00:00', '2021-12-16 00:00:00', '2020-12-08 16:21:33', '2020-12-08 16:21:33', 1);
INSERT INTO `t_activity` VALUES (69, 7, 0, 0, 100, 99.00, '2020-12-09 00:00:08', '2021-12-08 00:00:08', '2020-12-08 16:23:54', '2020-12-08 16:23:54', 0);
INSERT INTO `t_activity` VALUES (70, 8, 0, 0, 100, 99.00, '2020-12-09 00:00:08', '2021-12-08 00:00:08', '2020-12-08 16:23:54', '2020-12-08 16:23:54', 0);

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
INSERT INTO `t_address` VALUES (1, 1, 'sirwsl', '1562253547', '云南大力', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);
INSERT INTO `t_address` VALUES (2, 1, '张三', '1562253547', '云南大力', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);
INSERT INTO `t_address` VALUES (3, 1, '李四', '1562253547', '云南大力', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);
INSERT INTO `t_address` VALUES (4, 1, '张八九', '1562253547', '云南大力', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);
INSERT INTO `t_address` VALUES (5, 1, '刘教授', '1562253547', '丽江', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);
INSERT INTO `t_address` VALUES (6, 1, '小姐姐', '1562253547', '山东', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);
INSERT INTO `t_address` VALUES (7, 1, '阿里啥子', '1562253547', '昆明', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);
INSERT INTO `t_address` VALUES (8, 1, 'sirwsl', '1562253547', '云南昆明', 672100, 0, '2020-11-25 14:41:12', '2020-11-25 14:41:15', 0);

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
  `flag` int(10) NULL DEFAULT 0 COMMENT '等级',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '跟新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否离职',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, '小小磊', 'test', 'test', 0, '532923199701266', '1831426337', 'test.jpg', 'sir@163.com', '云南省祥云县刘厂镇王家庄村7组91号', NULL, 0, '2020-11-16 13:17:27', '2020-12-16 23:05:50', 0);
INSERT INTO `t_admin` VALUES (57, '123', 'sirwsl', '123', 0, '532923111101111911', '18888888888', NULL, '123', '123', NULL, 1, '2020-12-10 12:24:05', '2020-12-10 12:24:05', 0);
INSERT INTO `t_admin` VALUES (58, 'test', '0', '0', 1, '532923111101111911', '18888888888', NULL, '123', '123', NULL, 0, '2020-12-10 12:24:35', '2020-12-10 12:24:35', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告内容' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_advertise
-- ----------------------------
INSERT INTO `t_advertise` VALUES (1, 'http://localhost/advertise/0757a41e-a745-461a-89f8-06c0df03eefa.jpg', 'http://www.wslhome.top', '2020-12-08 17:18:12', '2020-12-09 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 1);
INSERT INTO `t_advertise` VALUES (2, 'http://localhost/advertise/timg.jpg', 'http://www.wslhome.top', '2020-12-02 17:18:12', '2020-12-22 17:18:06', '2020-12-09 00:00:00', '2020-12-15 23:42:06', 0);
INSERT INTO `t_advertise` VALUES (3, 'http://localhost/advertise/commend1.jpg', 'http://www.wslhome.top', '2020-12-04 17:18:12', '2020-12-30 17:18:06', '2020-12-09 00:00:00', '2020-12-10 22:48:14', 0);
INSERT INTO `t_advertise` VALUES (4, 'http://localhost/advertise/commend2.jpg', 'http://www.wslhome.top', '2020-12-08 17:18:12', '2020-12-09 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (5, 'http://localhost/advertise/elec1.jpg', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2021-01-29 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (6, 'http://localhost/advertise/good_shop3.png', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2021-01-29 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (7, 'http://localhost/advertise/goods_list2.jpg', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2021-01-29 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (8, 'http://localhost/advertise/goods_list3.jpg', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2021-01-29 17:18:06', '2020-12-10 17:18:12', '2020-12-10 17:18:12', 0);
INSERT INTO `t_advertise` VALUES (9, 'http://localhost/advertise/login-background.jpg', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2020-12-31 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (10, 'http://localhost/advertise/p1.jpg', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2020-12-31 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (11, 'http://localhost/advertise/p2.jpg', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2020-12-17 17:18:00', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (12, 'http://localhost/advertise/vip_shop4.png', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2020-12-17 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (13, 'http://localhost/advertise/vip_shop3.png', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2020-12-29 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (14, 'http://localhost/advertise/vip_shop2.png', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2020-12-27 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (15, 'http://localhost/advertise/vip_shop1.png', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2020-12-31 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (16, 'http://localhost/advertise/shop1.jpg', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2020-12-31 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (17, 'http://localhost/advertise/se_kill_img4.jpg', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2021-01-21 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (18, 'http://localhost/', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2020-12-16 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (19, 'http://localhost/advertise/se_kill_img1.jpg', 'http://www.wslhome.top', '2020-12-01 17:18:12', '2020-12-08 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (20, 'http://localhost/advertise/commend2.jpg', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2020-12-17 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (21, 'http://localhost/advertise/elec1.jpg', 'http://www.wslhome.top', '2020-12-02 17:18:12', '2020-12-10 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (22, 'http://localhost/advertise/good_shop3.png', 'http://www.wslhome.top', '2020-12-10 17:18:12', '2021-01-01 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (23, 'http://localhost/advertise/goods_list2.jpg', 'http://www.wslhome.top', '2020-12-02 17:18:12', '2020-12-10 17:18:06', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (24, 'http://localhost/advertise/goods_list3.jpg', 'http://www.wslhome.top', '2021-01-01 17:18:12', '2021-01-29 00:00:00', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (25, 'http://localhost/advertise/login-background.jpg', 'http://www.wslhome.top', '2021-01-01 17:18:12', '2021-01-29 00:00:00', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (26, 'http://localhost/advertise/p1.jpg', 'http://www.wslhome.top', '2021-01-01 17:18:12', '2021-01-29 00:00:00', '2020-12-10 17:18:12', '2020-12-10 17:18:12', 0);
INSERT INTO `t_advertise` VALUES (27, 'http://localhost/advertise/p2.jpg', 'http://www.wslhome.top', '2021-01-01 17:18:12', '2021-01-29 00:00:00', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (28, 'http://localhost/advertise/vip_shop4.png', 'http://www.wslhome.top', '2021-01-01 17:18:12', '2021-01-29 00:00:00', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (29, 'http://localhost/advertise/vip_shop3.png', 'http://www.wslhome.top', '2021-01-01 17:18:12', '2021-01-29 00:00:00', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (30, 'http://localhost/advertise/vip_shop2.png', 'http://www.wslhome.top', '2021-01-01 17:18:12', '2021-01-29 00:00:00', '2020-12-10 17:18:12', '2020-12-10 17:18:12', 0);
INSERT INTO `t_advertise` VALUES (31, 'http://localhost/advertise/vip_shop1.png', 'http://www.wslhome.top', '2021-01-01 17:18:12', '2021-01-29 00:00:00', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (32, 'http://localhost/advertise/shop2.jpg', 'http://www.wslhome.top', '2021-01-01 17:18:12', '2021-01-29 00:00:00', '2020-12-09 00:00:00', '2020-12-09 00:00:00', 0);
INSERT INTO `t_advertise` VALUES (33, 'http://localhost/advertise/0f2e2fe2-8bbc-4325-acda-c641f593108d.jpg', 'http://my.wslhome.top', '2020-12-16 00:00:00', '2020-12-31 00:00:00', '2020-12-10 22:49:43', '2020-12-10 22:49:43', 0);

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
INSERT INTO `t_after_sales` VALUES (2, '123124234125', 1, '申请退款，因为测试太垃圾', '啊手动阀手动阀手动阀是的', 2, '2020-12-17 17:41:10', 1, '2020-11-20 23:03:14', '2020-12-17 17:41:10', 0);
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
INSERT INTO `t_appraisal` VALUES (6, 6, 6, '测试评价1', NULL, 5, '2020-11-18 13:38:15', '2020-11-18 13:38:19', 1);

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
-- Table structure for t_experience
-- ----------------------------
DROP TABLE IF EXISTS `t_experience`;
CREATE TABLE `t_experience`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '体验账户',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `detail` varchar(999) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '体验账号' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_experience
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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES (1, '手机', 1, 'http://localhost/advertise/0757a41e-a745-461a-89f8-06c0df03eefa.jpg', 0, '这就是一个手机，一个很牛逼的手机', NULL, '2020-11-17 14:27:07', '2020-12-17 00:05:01', 0);
INSERT INTO `t_goods` VALUES (2, '手机', 1, 'http://localhost/advertise/timg.jpg', 1, '电脑电脑电脑电脑电脑', NULL, '2020-11-17 14:28:26', '2020-12-16 22:27:20', 0);
INSERT INTO `t_goods` VALUES (3, '充电宝', 2, 'http://localhost/advertise/commend1.jpg', 1, '充电宝', NULL, '2020-11-19 14:30:05', '2020-12-16 22:35:04', 0);
INSERT INTO `t_goods` VALUES (4, '手机壳', 2, 'http://localhost/advertise/commend2.jpg', 1, '这就是个测试手机壳', NULL, '2020-11-17 14:30:52', '2020-12-16 22:23:54', 0);
INSERT INTO `t_goods` VALUES (5, 'test1', 1, 'http://localhost/advertise/elec1.jpg', 1, '测试描述', NULL, '2020-11-20 11:12:34', '2020-11-20 13:42:56', 0);
INSERT INTO `t_goods` VALUES (6, '电灯泡', 1, 'http://localhost/goods/067c984c-163a-4f52-86aa-38603f6a0641.jpg', 1, 'eceas测试一下', NULL, '2020-12-15 23:51:50', '2020-12-15 23:52:55', 0);
INSERT INTO `t_goods` VALUES (7, '123', 1, 'http://localhost/goods/eaa76633-baeb-4382-9c7f-4ca6d54d6018.jpg', 1, '恶气啊违法', NULL, '2020-12-16 18:24:45', '2020-12-16 18:24:45', 0);

-- ----------------------------
-- Table structure for t_limit_list
-- ----------------------------
DROP TABLE IF EXISTS `t_limit_list`;
CREATE TABLE `t_limit_list`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` int(11) NOT NULL COMMENT '类型(1-手机号 2-ip)',
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '号码',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态（0-黑名单 1-白名单）',
  `start_time` datetime(0) NOT NULL COMMENT '开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '结束时间',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '黑名单与白名单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_limit_list
-- ----------------------------
INSERT INTO `t_limit_list` VALUES (2, 2, '12.12.43.541', 0, '2020-12-08 22:25:20', '2020-12-18 22:25:26', '2020-12-08 22:25:30', '2020-12-08 22:25:33', 0);
INSERT INTO `t_limit_list` VALUES (3, 1, '18314243333', 0, '2020-12-08 22:25:20', '2020-12-18 22:25:26', '2020-12-08 22:25:30', '2020-12-08 22:25:33', 1);
INSERT INTO `t_limit_list` VALUES (4, 2, '212.12.43.54', 0, '2020-12-08 22:25:20', '2020-12-18 22:25:26', '2020-12-08 22:25:30', '2020-12-08 22:25:33', 0);
INSERT INTO `t_limit_list` VALUES (5, 1, '18347382888', 0, '2020-12-08 22:25:20', '2020-12-18 22:25:26', '2020-12-08 22:25:30', '2020-12-08 22:25:33', 1);
INSERT INTO `t_limit_list` VALUES (6, 1, '18314263367', 0, '2020-12-08 22:25:20', '2020-12-18 22:25:26', '2020-12-08 22:25:30', '2020-12-10 15:19:25', 0);
INSERT INTO `t_limit_list` VALUES (7, 1, '18888868868', 0, '2020-12-08 22:25:20', '2020-12-18 22:25:26', '2020-12-08 22:25:30', '2020-12-08 22:25:33', 0);
INSERT INTO `t_limit_list` VALUES (8, 1, '15188878878', 0, '2020-12-08 22:25:20', '2020-12-18 22:25:26', '2020-12-08 22:25:30', '2020-12-08 22:25:33', 0);
INSERT INTO `t_limit_list` VALUES (9, 1, '18489888098', 0, '2020-12-08 22:25:20', '2020-12-18 22:25:26', '2020-12-08 22:25:30', '2020-12-08 22:25:33', 0);
INSERT INTO `t_limit_list` VALUES (10, 1, '15662099878', 0, '2020-12-08 22:25:20', '2020-12-18 22:25:26', '2020-12-08 22:25:30', '2020-12-08 22:25:33', 1);
INSERT INTO `t_limit_list` VALUES (11, 2, '120.98.163.80', 0, '2020-12-08 22:25:20', '2020-12-18 22:25:26', '2020-12-08 22:25:30', '2020-12-08 22:25:33', 1);
INSERT INTO `t_limit_list` VALUES (12, 2, '43.173.10.78', 0, '2020-12-08 22:25:20', '2020-12-18 22:25:26', '2020-12-08 22:25:30', '2020-12-08 22:25:33', 1);
INSERT INTO `t_limit_list` VALUES (13, 2, '120.98.163.80', 0, '2020-12-08 22:25:20', '2020-12-18 22:25:26', '2020-12-08 22:25:30', '2020-12-08 22:25:33', 0);
INSERT INTO `t_limit_list` VALUES (14, 1, '123', 0, '2020-12-09 17:36:00', '2100-12-31 00:00:00', '2020-12-09 17:36:00', '2020-12-09 17:36:00', 0);
INSERT INTO `t_limit_list` VALUES (15, 1, '123', 0, '2020-12-30 00:00:00', '2100-12-31 00:00:00', '2020-12-09 17:36:05', '2020-12-09 17:36:05', 0);
INSERT INTO `t_limit_list` VALUES (16, 2, '127.0.0.2', 0, '2020-12-30 00:00:00', '2021-12-08 00:00:00', '2020-12-09 17:42:31', '2020-12-09 17:42:31', 0);
INSERT INTO `t_limit_list` VALUES (17, 2, '127.0.0.3', 0, '2020-12-30 00:00:00', '2021-12-08 00:00:00', '2020-12-09 17:42:38', '2020-12-09 17:42:38', 0);
INSERT INTO `t_limit_list` VALUES (18, 2, '127.0.0.10', 0, '2020-12-30 00:00:00', '2021-12-08 00:00:00', '2020-12-09 17:42:45', '2020-12-09 17:42:45', 0);
INSERT INTO `t_limit_list` VALUES (19, 1, '18314200000', 0, '2020-12-30 00:00:00', '2021-08-12 12:00:00', '2020-12-09 18:04:17', '2020-12-09 18:04:17', 0);
INSERT INTO `t_limit_list` VALUES (20, 1, '18314200001', 0, '2020-12-30 00:00:00', '2021-12-08 00:00:00', '2020-12-09 18:05:01', '2020-12-09 18:05:01', 0);
INSERT INTO `t_limit_list` VALUES (21, 1, '18343543111', 0, '2020-12-30 00:00:00', '2021-12-08 00:00:00', '2020-12-09 18:09:54', '2020-12-09 18:09:54', 0);
INSERT INTO `t_limit_list` VALUES (22, 1, '18314200000', 0, '2020-12-30 00:00:00', '2021-08-12 12:00:00', '2020-12-09 18:10:56', '2020-12-09 18:10:56', 0);
INSERT INTO `t_limit_list` VALUES (23, 1, '18314263000', 0, '2020-12-23 00:00:00', '2020-12-24 00:00:00', '2020-12-09 18:13:36', '2020-12-09 18:13:36', 0);
INSERT INTO `t_limit_list` VALUES (24, 1, '18314263000', 0, '2020-12-23 00:00:00', '2020-12-24 00:00:00', '2020-12-09 18:13:41', '2020-12-09 18:13:41', 0);
INSERT INTO `t_limit_list` VALUES (25, 1, '18314263000', 0, '2020-12-23 00:00:00', '2020-12-24 00:00:00', '2020-12-09 18:13:45', '2020-12-09 18:13:45', 0);
INSERT INTO `t_limit_list` VALUES (26, 2, '127.0.1.0', 0, '2020-12-09 18:15:30', '2020-12-18 00:00:00', '2020-12-09 18:15:30', '2020-12-09 18:15:30', 0);
INSERT INTO `t_limit_list` VALUES (27, 2, '121.0.1.2', 0, '2020-12-25 00:00:00', '2020-12-28 00:00:00', '2020-12-09 18:16:06', '2020-12-09 18:16:06', 0);
INSERT INTO `t_limit_list` VALUES (28, 1, '18314263336', 0, '2020-12-10 14:44:03', '2020-12-18 22:25:26', '2020-12-10 14:44:03', '2020-12-10 14:44:03', 0);
INSERT INTO `t_limit_list` VALUES (29, 1, '18314266666', 0, '2020-12-10 14:45:00', '2020-12-18 22:25:26', '2020-12-10 14:45:00', '2020-12-10 14:45:00', 0);
INSERT INTO `t_limit_list` VALUES (30, 1, '18900000000', 0, '2020-12-10 14:52:29', '2020-12-29 00:00:00', '2020-12-10 14:52:29', '2020-12-10 14:52:29', 0);
INSERT INTO `t_limit_list` VALUES (31, 1, '18314263333', 0, '2020-12-10 15:11:20', '2020-12-18 22:25:26', '2020-12-10 15:11:20', '2020-12-10 15:11:20', 0);
INSERT INTO `t_limit_list` VALUES (32, 1, '18314263366', 0, '2020-12-10 15:19:56', '2020-12-18 22:25:26', '2020-12-10 15:19:56', '2020-12-10 15:19:56', 0);
INSERT INTO `t_limit_list` VALUES (33, 1, '18888888888', 0, '2020-12-14 19:21:07', '2020-12-30 00:00:00', '2020-12-14 19:21:07', '2020-12-14 19:21:07', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 210 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_loggers
-- ----------------------------
INSERT INTO `t_loggers` VALUES (12, '添加管理员->[操作参数：sirwsl]->[Class：AdminServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-11-16 10:58:28', '2020-11-16 10:58:28', 0);
INSERT INTO `t_loggers` VALUES (13, '添加管理员->[操作参数：sirwsl]->[Class：AdminServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-11-16 11:42:28', '2020-11-16 11:42:28', 0);
INSERT INTO `t_loggers` VALUES (14, '删除管理员->[操作参数：48]->[Class：AdminServiceImpl]', 1, 1, 3, '0:0:0:0:0:0:0:1', '2020-11-16 11:44:29', '2020-11-16 11:44:29', 0);
INSERT INTO `t_loggers` VALUES (15, '删除管理员->[操作参数：48]->[Class：AdminServiceImpl]', 1, 1, 3, '0:0:0:0:0:0:0:1', '2020-11-16 11:47:15', '2020-11-16 11:47:15', 0);
INSERT INTO `t_loggers` VALUES (16, '删除管理员->[操作参数：48]->[Class：AdminServiceImpl]', 1, 1, 3, '0:0:0:0:0:0:0:1', '2020-11-16 11:50:18', '2020-11-16 11:50:18', 0);
INSERT INTO `t_loggers` VALUES (17, '删除管理员->[操作参数：48]->[Class：AdminServiceImpl]', 1, 1, 3, '0:0:0:0:0:0:0:1', '2020-11-16 11:55:02', '2020-11-16 11:55:02', 0);
INSERT INTO `t_loggers` VALUES (18, '删除管理员->[操作参数：48]->[Class：AdminServiceImpl]', 1, 1, 3, '0:0:0:0:0:0:0:1', '2020-11-16 12:36:58', '2020-11-16 12:36:58', 0);
INSERT INTO `t_loggers` VALUES (19, '删除管理员->[操作参数：48]->[Class：AdminServiceImpl]', 1, 1, 3, '0:0:0:0:0:0:0:1', '2020-11-16 12:38:12', '2020-11-16 12:38:12', 0);
INSERT INTO `t_loggers` VALUES (20, '添加发布订阅内容->[操作参数：45]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 12:42:16', '2020-11-16 12:42:16', 0);
INSERT INTO `t_loggers` VALUES (21, '添加发布订阅内容->[操作参数：48]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 12:50:12', '2020-11-16 12:50:12', 0);
INSERT INTO `t_loggers` VALUES (22, '添加管理员->[操作参数：sirwsl]->[Class：AdminServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-11-16 12:51:34', '2020-11-16 12:51:34', 0);
INSERT INTO `t_loggers` VALUES (23, '添加发布订阅内容->[操作参数：49]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 12:54:09', '2020-11-16 12:54:09', 0);
INSERT INTO `t_loggers` VALUES (24, '添加发布订阅内容->[操作参数：52]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 12:56:31', '2020-11-16 12:56:31', 0);
INSERT INTO `t_loggers` VALUES (25, '添加发布订阅内容->[操作参数：56]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 13:00:38', '2020-11-16 13:00:38', 0);
INSERT INTO `t_loggers` VALUES (26, '添加发布订阅内容->[操作参数：63]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '0:0:0:0:0:0:0:1', '2020-11-16 13:15:19', '2020-11-16 13:15:19', 0);
INSERT INTO `t_loggers` VALUES (27, '添加管理员->[操作参数：sirwsl]->[Class：AdminServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-11-16 13:17:15', '2020-11-16 13:17:15', 0);
INSERT INTO `t_loggers` VALUES (28, '添加管理员->[操作参数：sirwsl]->[Class：AdminServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-11-16 13:17:27', '2020-11-16 13:17:27', 0);
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
INSERT INTO `t_loggers` VALUES (124, '添加/更新一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-08 16:21:33', '2020-12-08 16:21:33', 0);
INSERT INTO `t_loggers` VALUES (125, '添加/更新一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-08 16:23:54', '2020-12-08 16:23:54', 0);
INSERT INTO `t_loggers` VALUES (126, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-08 16:24:32', '2020-12-08 16:24:32', 0);
INSERT INTO `t_loggers` VALUES (127, '移除黑名单->[操作参数：[Ljava.lang.Integer;@6db6e028]->[Class：LimitListController]', 1, 1, 3, '127.0.0.1', '2020-12-09 17:02:04', '2020-12-09 17:02:04', 0);
INSERT INTO `t_loggers` VALUES (128, '添加黑名单->[操作参数：18343543111]->[Class：LimitListServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-09 18:09:54', '2020-12-09 18:09:54', 0);
INSERT INTO `t_loggers` VALUES (129, '添加黑名单->[操作参数：18314200000]->[Class：LimitListServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-09 18:10:56', '2020-12-09 18:10:56', 0);
INSERT INTO `t_loggers` VALUES (130, '添加黑名单->[操作参数：18314263000]->[Class：LimitListServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-09 18:13:36', '2020-12-09 18:13:36', 0);
INSERT INTO `t_loggers` VALUES (131, '添加黑名单->[操作参数：18314263000]->[Class：LimitListServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-09 18:13:41', '2020-12-09 18:13:41', 0);
INSERT INTO `t_loggers` VALUES (132, '添加黑名单->[操作参数：18314263000]->[Class：LimitListServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-09 18:13:45', '2020-12-09 18:13:45', 0);
INSERT INTO `t_loggers` VALUES (133, '添加黑名单->[操作参数：127.0.1.0]->[Class：LimitListServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-09 18:15:30', '2020-12-09 18:15:30', 0);
INSERT INTO `t_loggers` VALUES (134, '添加黑名单->[操作参数：121.0.1.2]->[Class：LimitListServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-09 18:16:06', '2020-12-09 18:16:06', 0);
INSERT INTO `t_loggers` VALUES (135, '移除黑名单->[操作参数：[Ljava.lang.Integer;@6a4d3dd0]->[Class：LimitListController]', 1, 1, 3, '127.0.0.1', '2020-12-09 19:12:47', '2020-12-09 19:12:47', 0);
INSERT INTO `t_loggers` VALUES (136, '修改管理员信息->[操作参数：sirwsl]->[Class：AdminServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-10 09:24:54', '2020-12-10 09:24:54', 0);
INSERT INTO `t_loggers` VALUES (137, '修改管理员信息->[操作参数：sirwsl]->[Class：AdminServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-10 09:25:28', '2020-12-10 09:25:28', 0);
INSERT INTO `t_loggers` VALUES (138, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-10 09:29:00', '2020-12-10 09:29:00', 0);
INSERT INTO `t_loggers` VALUES (139, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-10 09:29:16', '2020-12-10 09:29:16', 0);
INSERT INTO `t_loggers` VALUES (140, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-10 09:33:28', '2020-12-10 09:33:28', 0);
INSERT INTO `t_loggers` VALUES (141, '删除一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-10 09:34:55', '2020-12-10 09:34:55', 0);
INSERT INTO `t_loggers` VALUES (142, '添加/更新一个活动->[操作参数：0]->[Class：ActivityServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-10 09:35:13', '2020-12-10 09:35:13', 0);
INSERT INTO `t_loggers` VALUES (143, '修改管理员信息->[操作参数：sirwsl]->[Class：AdminServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-10 11:52:30', '2020-12-10 11:52:30', 0);
INSERT INTO `t_loggers` VALUES (144, '添加管理员->[操作参数：sirwsl]->[Class：AdminServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-10 12:24:05', '2020-12-10 12:24:05', 0);
INSERT INTO `t_loggers` VALUES (145, '添加管理员->[操作参数：sirwsl2]->[Class：AdminServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-10 12:24:35', '2020-12-10 12:24:35', 0);
INSERT INTO `t_loggers` VALUES (146, '删除管理员->[操作参数：58]->[Class：AdminServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-10 12:24:45', '2020-12-10 12:24:45', 0);
INSERT INTO `t_loggers` VALUES (147, '移除黑名单->[操作参数：3]->[Class：LimitListController]', 1, 1, 3, '127.0.0.1', '2020-12-10 14:19:52', '2020-12-10 14:19:52', 0);
INSERT INTO `t_loggers` VALUES (148, '移除黑名单->[操作参数：5]->[Class：LimitListController]', 1, 1, 3, '127.0.0.1', '2020-12-10 14:19:55', '2020-12-10 14:19:55', 0);
INSERT INTO `t_loggers` VALUES (149, '添加黑名单->[操作参数：18314263336]->[Class：LimitListServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-10 14:44:04', '2020-12-10 14:44:04', 0);
INSERT INTO `t_loggers` VALUES (150, '添加黑名单->[操作参数：18314266666]->[Class：LimitListServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-10 14:45:00', '2020-12-10 14:45:00', 0);
INSERT INTO `t_loggers` VALUES (151, '添加黑名单->[操作参数：18900000000]->[Class：LimitListServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-10 14:52:29', '2020-12-10 14:52:29', 0);
INSERT INTO `t_loggers` VALUES (152, '添加黑名单->[操作参数：18314263333]->[Class：LimitListServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-10 15:11:20', '2020-12-10 15:11:20', 0);
INSERT INTO `t_loggers` VALUES (153, '更新黑名单->[操作参数：0]->[Class：LimitListController]', 1, 1, 3, '127.0.0.1', '2020-12-10 15:18:40', '2020-12-10 15:18:40', 0);
INSERT INTO `t_loggers` VALUES (154, '更新黑名单->[操作参数：0]->[Class：LimitListController]', 1, 1, 3, '127.0.0.1', '2020-12-10 15:19:26', '2020-12-10 15:19:26', 0);
INSERT INTO `t_loggers` VALUES (155, '添加黑名单->[操作参数：18314263366]->[Class：LimitListServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-10 15:19:56', '2020-12-10 15:19:56', 0);
INSERT INTO `t_loggers` VALUES (156, '修改管理员信息->[操作参数：sirwsl]->[Class：AdminServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-10 16:27:04', '2020-12-10 16:27:04', 0);
INSERT INTO `t_loggers` VALUES (157, '修改管理员信息->[操作参数：sirwsl]->[Class：AdminServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-10 16:27:27', '2020-12-10 16:27:27', 0);
INSERT INTO `t_loggers` VALUES (158, '修改管理员信息->[操作参数：sirwsl]->[Class：AdminServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-10 16:27:44', '2020-12-10 16:27:44', 0);
INSERT INTO `t_loggers` VALUES (159, '修改管理员信息->[操作参数：sirwsl]->[Class：AdminServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-10 16:36:47', '2020-12-10 16:36:47', 0);
INSERT INTO `t_loggers` VALUES (160, '删除广告->[操作参数：1]->[Class：AdvertiseServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-10 21:41:18', '2020-12-10 21:41:18', 0);
INSERT INTO `t_loggers` VALUES (161, '更新广告->[操作参数：2]->[Class：AdvertiseServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-10 22:46:51', '2020-12-10 22:46:51', 0);
INSERT INTO `t_loggers` VALUES (162, '更新广告->[操作参数：3]->[Class：AdvertiseServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-10 22:48:14', '2020-12-10 22:48:14', 0);
INSERT INTO `t_loggers` VALUES (163, '添加广告->[操作参数：http://my.wslhome.top]->[Class：AdvertiseServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-10 22:49:43', '2020-12-10 22:49:43', 0);
INSERT INTO `t_loggers` VALUES (164, '删除会员->[操作参数：1]->[Class：UserServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-14 18:27:58', '2020-12-14 18:27:58', 0);
INSERT INTO `t_loggers` VALUES (165, '删除会员->[操作参数：1]->[Class：UserServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-14 18:28:11', '2020-12-14 18:28:11', 0);
INSERT INTO `t_loggers` VALUES (166, '删除会员->[操作参数：2]->[Class：UserServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-14 18:31:53', '2020-12-14 18:31:53', 0);
INSERT INTO `t_loggers` VALUES (167, '删除会员->[操作参数：10]->[Class：UserServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-14 18:33:53', '2020-12-14 18:33:53', 0);
INSERT INTO `t_loggers` VALUES (168, '添加黑名单->[操作参数：18888888888]->[Class：LimitListServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-14 19:21:07', '2020-12-14 19:21:07', 0);
INSERT INTO `t_loggers` VALUES (169, '添加发布订阅内容->[操作参数：91]->[Class：SubscriptionHistoryServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-14 20:49:59', '2020-12-14 20:49:59', 0);
INSERT INTO `t_loggers` VALUES (170, '商品删除->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-15 11:56:32', '2020-12-15 11:56:32', 0);
INSERT INTO `t_loggers` VALUES (171, '商品删除->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-15 11:57:01', '2020-12-15 11:57:01', 0);
INSERT INTO `t_loggers` VALUES (172, '商品删除->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-15 11:58:05', '2020-12-15 11:58:05', 0);
INSERT INTO `t_loggers` VALUES (173, '商品删除->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-15 13:28:31', '2020-12-15 13:28:31', 0);
INSERT INTO `t_loggers` VALUES (174, '商品删除->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-15 13:28:49', '2020-12-15 13:28:49', 0);
INSERT INTO `t_loggers` VALUES (175, '商品删除->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-15 13:34:31', '2020-12-15 13:34:31', 0);
INSERT INTO `t_loggers` VALUES (176, '商品删除->[操作参数：5]->[Class：GoodsServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-15 13:34:36', '2020-12-15 13:34:36', 0);
INSERT INTO `t_loggers` VALUES (177, '更新广告->[操作参数：2]->[Class：AdvertiseServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-15 23:39:57', '2020-12-15 23:39:57', 0);
INSERT INTO `t_loggers` VALUES (178, '更新广告->[操作参数：2]->[Class：AdvertiseServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-15 23:42:06', '2020-12-15 23:42:06', 0);
INSERT INTO `t_loggers` VALUES (179, '商品更新->[操作参数：6]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-15 23:52:55', '2020-12-15 23:52:55', 0);
INSERT INTO `t_loggers` VALUES (180, '商品删除->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-16 18:24:24', '2020-12-16 18:24:24', 0);
INSERT INTO `t_loggers` VALUES (181, '商品上架处理->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:23:06', '2020-12-16 22:23:06', 0);
INSERT INTO `t_loggers` VALUES (182, '商品上架处理->[操作参数：2]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:23:11', '2020-12-16 22:23:11', 0);
INSERT INTO `t_loggers` VALUES (183, '商品上架处理->[操作参数：4]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:23:35', '2020-12-16 22:23:35', 0);
INSERT INTO `t_loggers` VALUES (184, '商品上架处理->[操作参数：4]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:23:54', '2020-12-16 22:23:54', 0);
INSERT INTO `t_loggers` VALUES (185, '商品上架处理->[操作参数：3]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:23:59', '2020-12-16 22:23:59', 0);
INSERT INTO `t_loggers` VALUES (186, '商品上架处理->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:24:59', '2020-12-16 22:24:59', 0);
INSERT INTO `t_loggers` VALUES (187, '商品上架处理->[操作参数：2]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:25:02', '2020-12-16 22:25:02', 0);
INSERT INTO `t_loggers` VALUES (188, '商品上架处理->[操作参数：2]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:25:05', '2020-12-16 22:25:05', 0);
INSERT INTO `t_loggers` VALUES (189, '商品上架处理->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:26:19', '2020-12-16 22:26:19', 0);
INSERT INTO `t_loggers` VALUES (190, '商品上架处理->[操作参数：2]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:26:23', '2020-12-16 22:26:23', 0);
INSERT INTO `t_loggers` VALUES (191, '商品上架处理->[操作参数：2]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:26:34', '2020-12-16 22:26:34', 0);
INSERT INTO `t_loggers` VALUES (192, '商品上架处理->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:26:43', '2020-12-16 22:26:43', 0);
INSERT INTO `t_loggers` VALUES (193, '商品上架处理->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:27:11', '2020-12-16 22:27:11', 0);
INSERT INTO `t_loggers` VALUES (194, '商品上架处理->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:27:16', '2020-12-16 22:27:16', 0);
INSERT INTO `t_loggers` VALUES (195, '商品上架处理->[操作参数：2]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:27:21', '2020-12-16 22:27:21', 0);
INSERT INTO `t_loggers` VALUES (196, '商品上架处理->[操作参数：3]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 22:35:05', '2020-12-16 22:35:05', 0);
INSERT INTO `t_loggers` VALUES (197, '修改管理员信息->[操作参数：test]->[Class：AdminServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-16 23:05:50', '2020-12-16 23:05:50', 0);
INSERT INTO `t_loggers` VALUES (198, '更新商品类别->[操作参数：1]->[Class：TypesServiceImpl]', 1, 1, 0, '127.0.0.1', '2020-12-16 23:14:02', '2020-12-16 23:14:02', 0);
INSERT INTO `t_loggers` VALUES (199, '更新商品类别->[操作参数：2]->[Class：TypesServiceImpl]', 1, 1, 0, '127.0.0.1', '2020-12-16 23:14:12', '2020-12-16 23:14:12', 0);
INSERT INTO `t_loggers` VALUES (200, '删除商品类别->[操作参数：1]->[Class：TypesServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 23:18:01', '2020-12-16 23:18:01', 0);
INSERT INTO `t_loggers` VALUES (201, '添加商品类别->[操作参数：测试分类]->[Class：TypesServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 23:45:04', '2020-12-16 23:45:04', 0);
INSERT INTO `t_loggers` VALUES (202, '添加商品类别->[操作参数：再来测试一个]->[Class：TypesServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 23:47:33', '2020-12-16 23:47:33', 0);
INSERT INTO `t_loggers` VALUES (203, '添加商品类别->[操作参数：最后一个]->[Class：TypesServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 23:47:43', '2020-12-16 23:47:43', 0);
INSERT INTO `t_loggers` VALUES (204, '添加商品类别->[操作参数：就这样吧]->[Class：TypesServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-16 23:48:10', '2020-12-16 23:48:10', 0);
INSERT INTO `t_loggers` VALUES (205, '商品上架处理->[操作参数：1]->[Class：GoodsServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-17 00:05:01', '2020-12-17 00:05:01', 0);
INSERT INTO `t_loggers` VALUES (206, '删除评价信息->[操作参数：6]->[Class：AppraisalServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-17 13:30:58', '2020-12-17 13:30:58', 0);
INSERT INTO `t_loggers` VALUES (207, '售后处理->[操作参数：2]->[Class：AfterSalesServiceImpl]', 1, 1, 1, '127.0.0.1', '2020-12-17 17:41:10', '2020-12-17 17:41:10', 0);
INSERT INTO `t_loggers` VALUES (208, '物品出库->[操作参数：2312421388]->[Class：OrderServiceImpl]', 1, 1, 2, '127.0.0.1', '2020-12-17 19:11:35', '2020-12-17 19:11:35', 0);
INSERT INTO `t_loggers` VALUES (209, '修改物品价格->[操作参数：1231242888]->[Class：OrderServiceImpl]', 1, 1, 3, '127.0.0.1', '2020-12-17 20:11:35', '2020-12-17 20:11:35', 0);

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
INSERT INTO `t_order` VALUES (1231242888, 1, 2, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 1, 98.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 1, '2020-11-20 23:22:58', '2020-12-17 20:11:34', 0);
INSERT INTO `t_order` VALUES (2312421388, 2, 3, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 2, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 2, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
INSERT INTO `t_order` VALUES (23124234125, 3, 1, 1, '2020-11-20 23:22:36', '2020-11-20 23:22:39', '2020-11-20 23:22:41', 1, 3, 100.00, '测试订单备注司法后i的撒回复爱德华iOS德国哈皮士大夫', 3, '2020-11-20 23:22:58', '2020-11-20 23:23:01', 0);
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'SKU_ID',
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
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'SKU（商品的售卖产生的影响属性）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sku
-- ----------------------------
INSERT INTO `t_sku` VALUES (1, 1, '128G 6G内存 红色', 'http://localhost/advertise/good_shop3.png', 1256.00, 1200.00, 1695.00, 996, 20, 10.00, '2020-11-17 14:33:26', '2020-12-17 17:41:10', 0);
INSERT INTO `t_sku` VALUES (2, 1, '128G 4G内存 白色', 'http://localhost/sku/e9eb0192-e8e2-453e-810e-3feddc456df8.jpg', 1256.00, 1200.00, 1695.00, 1001, 20, 100.00, '2020-11-17 14:33:26', '2020-12-17 17:41:10', 0);
INSERT INTO `t_sku` VALUES (3, 1, '128G 6G内存 黑色色', 'http://localhost/advertise/goods_list3.jpg', 1256.00, 1200.00, 1695.00, 661, 20, 10.00, '2020-11-17 14:33:26', '2020-11-22 23:28:35', 0);
INSERT INTO `t_sku` VALUES (4, 2, '1T硬盘 32G内存 2G独立显卡', 'http://localhost/advertise/login-background.jpg', 4999.00, 5899.00, 8766.00, 1200, 124, 200.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);
INSERT INTO `t_sku` VALUES (5, 2, '1T硬盘 32G内存 黑色', 'http://localhost/advertise/p1.jpg', 4999.00, 5899.00, 8766.00, 1200, 124, 200.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);
INSERT INTO `t_sku` VALUES (6, 2, '1T硬盘  内存', 'http://localhost/advertise/vip_shop4.png', 4999.00, 5899.00, 8766.00, 1200, 124, 200.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);
INSERT INTO `t_sku` VALUES (7, 3, '白色', 'http://localhost/advertise/vip_shop3.png', 59.00, 62.00, 99.00, 200, 5, 5.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);
INSERT INTO `t_sku` VALUES (8, 3, '黑色', 'http://localhost/advertise/vip_shop2.png', 59.00, 62.00, 99.00, 200, 5, 5.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);
INSERT INTO `t_sku` VALUES (9, 4, '粉色', 'http://localhost/advertise/vip_shop1.png', 59.00, 62.00, 99.00, 200, 5, 5.00, '2020-11-17 14:33:26', '2020-12-16 16:37:02', 0);
INSERT INTO `t_sku` VALUES (10, 4, '紫色', 'http://localhost/advertise/se_kill_img4.jpg', 59.00, 62.00, 99.00, 200, 5, 5.00, '2020-11-17 14:33:26', '2020-12-16 16:21:13', 0);
INSERT INTO `t_sku` VALUES (11, 4, '蓝色', 'http://localhost/advertise/se_kill_img1.jpg', 59.00, 62.00, 99.00, 200, 5, 5.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);
INSERT INTO `t_sku` VALUES (12, 4, '白色', 'http://localhost/advertise/commend2.jpg', 59.00, 62.00, 99.00, 200, 5, 5.00, '2020-11-17 14:33:26', '2020-11-17 14:33:33', 0);
INSERT INTO `t_sku` VALUES (13, 4, 'dsagsdfg sdfgwergsdfg sdfgsd', 'http://localhost/sku/cc1275d6-f3db-464c-bb40-854fa1d22a56.jpg', 345633.00, 345637.00, 45634.00, 689, 67, NULL, '2020-12-16 17:32:03', '2020-12-16 17:32:03', 0);

-- ----------------------------
-- Table structure for t_subscriber
-- ----------------------------
DROP TABLE IF EXISTS `t_subscriber`;
CREATE TABLE `t_subscriber`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订阅者号',
  `type` int(11) NOT NULL COMMENT '订阅类型(1-手机订阅 0-邮件订阅)',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态(0-正常  1-取消)',
  `creat_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订阅者表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_subscriber
-- ----------------------------
INSERT INTO `t_subscriber` VALUES (1, '1830000000', 1, 0, '2020-12-13 10:01:10', '2020-12-13 10:01:12', 1);
INSERT INTO `t_subscriber` VALUES (2, '18314212343', 1, 0, '2020-12-13 10:01:10', '2020-12-13 10:01:12', 1);
INSERT INTO `t_subscriber` VALUES (3, '18314263312', 1, 0, '2020-12-13 10:01:10', '2020-12-13 10:01:12', 1);
INSERT INTO `t_subscriber` VALUES (4, '18314263342', 1, 0, '2020-12-13 10:01:10', '2020-12-13 10:01:12', 0);
INSERT INTO `t_subscriber` VALUES (5, '18888888888', 1, 0, '2020-12-13 10:01:10', '2020-12-13 10:01:12', 0);
INSERT INTO `t_subscriber` VALUES (6, '21343@163.com', 0, 0, '2020-12-13 10:05:03', '2020-12-13 10:05:06', 0);
INSERT INTO `t_subscriber` VALUES (7, '329894357@qq.com', 0, 0, '2020-12-13 10:04:06', '2020-12-13 10:04:08', 0);
INSERT INTO `t_subscriber` VALUES (8, '593894@qq.com', 0, 0, '2020-12-13 10:04:23', '2020-12-13 10:04:26', 0);
INSERT INTO `t_subscriber` VALUES (9, 'sir@163.com', 0, 0, '2020-12-13 10:01:41', '2020-12-13 10:01:43', 0);
INSERT INTO `t_subscriber` VALUES (10, '18618618618', 1, 0, '2020-12-13 11:14:52', '2020-12-13 11:14:52', 0);
INSERT INTO `t_subscriber` VALUES (11, '2572396933@qq.com', 0, 0, '2020-12-13 11:15:50', '2020-12-13 11:15:50', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 92 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订阅历史表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_subscription_history
-- ----------------------------
INSERT INTO `t_subscription_history` VALUES (1, 'ceshibiaoti', '123456sa4d6848we', 0, 0, 0, '2020-11-16 14:38:59', '2020-11-16 14:39:03', 0);
INSERT INTO `t_subscription_history` VALUES (2, '而无法华为啊否和', '沙嗲u送佛送到会分开收到回复啊师傅阿飞阿喀琉斯的回复喀什东路发货阿萨', 0, 0, 0, '2020-11-16 14:39:26', '2020-11-16 14:39:29', 0);
INSERT INTO `t_subscription_history` VALUES (3, '而无法华为啊否和', '沙嗲u送佛送到会分开收到回复啊师傅阿飞阿喀琉斯的回复喀什东路发货阿萨', 0, 0, 0, '2020-11-16 14:39:26', '2020-11-16 14:39:29', 0);
INSERT INTO `t_subscription_history` VALUES (4, '而无法华为啊否和', '沙嗲u送佛送到会分开收到回复啊师傅阿飞阿喀琉斯的回复喀什东路发货阿萨', 0, 0, 0, '2020-11-16 14:39:26', '2020-11-16 14:39:29', 0);
INSERT INTO `t_subscription_history` VALUES (5, '而无法华为啊否和', '沙嗲u送佛送到会分开收到回复啊师傅阿飞阿喀琉斯的回复喀什东路发货阿萨', 0, 0, 0, '2020-11-20 14:39:26', '2020-11-20 14:39:29', 0);
INSERT INTO `t_subscription_history` VALUES (91, '双十二过后的狂欢', '我的结尾发送飞机的饿哦是就哦哦出入境卡放入葱去见哦人', 0, 1, 1, '2020-12-14 20:49:57', '2020-12-14 20:50:04', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品类别表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_types
-- ----------------------------
INSERT INTO `t_types` VALUES (1, '测试分类手', '2020-11-18 17:13:07', '2020-12-16 23:14:02', 1);
INSERT INTO `t_types` VALUES (2, '4567834234234salads', '2020-11-18 17:13:50', '2020-12-16 23:14:12', 0);
INSERT INTO `t_types` VALUES (3, '测试分类电脑2', '2020-11-18 17:13:54', '2020-11-18 17:13:54', 0);
INSERT INTO `t_types` VALUES (4, '测试分类电脑6', '2020-11-18 17:13:58', '2020-11-18 17:13:58', 0);
INSERT INTO `t_types` VALUES (5, '123', '2020-11-18 17:22:42', '2020-11-18 17:22:42', 0);
INSERT INTO `t_types` VALUES (6, '测试分类', '2020-12-16 23:45:04', '2020-12-16 23:45:04', 0);
INSERT INTO `t_types` VALUES (7, '再来测试一个', '2020-12-16 23:47:33', '2020-12-16 23:47:33', 0);
INSERT INTO `t_types` VALUES (8, '最后一个', '2020-12-16 23:47:43', '2020-12-16 23:47:43', 0);
INSERT INTO `t_types` VALUES (9, '就这样吧', '2020-12-16 23:48:10', '2020-12-16 23:48:10', 0);

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
INSERT INTO `t_user` VALUES (1, 'sirwsl', '123', '测试昵称', 'img/ser/img', '0', NULL, '18888888888', 'sir@163.com', '53292312303023421916', 'sirwsl', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 10:37:23', 0);
INSERT INTO `t_user` VALUES (2, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18888888888', 'sir@163.com', '53292312303023421916', 'sirwsl', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);
INSERT INTO `t_user` VALUES (3, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18888888888', 'sir@163.com', '53292312303023421916', 'sirwsl', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);
INSERT INTO `t_user` VALUES (4, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18888888888', 'sir@163.com', '53292312303023421916', 'sirwsl', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);
INSERT INTO `t_user` VALUES (5, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18888888888', 'sir@163.com', '53292312303023421916', 'sirwsl', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);
INSERT INTO `t_user` VALUES (6, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18888888888', 'sir@163.com', '53292312303023421916', 'sirwsl', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);
INSERT INTO `t_user` VALUES (7, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18888888888', 'sir@163.com', '53292312303023421916', 'sirwsl', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);
INSERT INTO `t_user` VALUES (8, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18888888888', 'sir@163.com', '53292312303023421916', 'sirwsl', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);
INSERT INTO `t_user` VALUES (9, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18888888888', 'sir@163.com', '53292312303023421916', 'sirwsl', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);
INSERT INTO `t_user` VALUES (10, '测试姓名', '123456', '18453', 'img/ser/img', '0', NULL, '18888888888', 'sir@163.com', '53292312303023421916', 'sirwsl', '59awasdf', '48asd', '2020-11-17 09:31:14', '2020-11-17 09:31:17', 0);

SET FOREIGN_KEY_CHECKS = 1;
