/*
 Navicat MySQL Data Transfer

 Source Server         : taobishe
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 1.116.117.222:3306
 Source Schema         : a_book

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 12/12/2022 22:32:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_member
-- ----------------------------
DROP TABLE IF EXISTS `base_member`;
CREATE TABLE `base_member`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `deposit` decimal(10, 2) NULL DEFAULT NULL COMMENT '押金',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_member
-- ----------------------------
INSERT INTO `base_member` VALUES (68, '清风', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK3JVibuZg8wiaKG9ExyVJJT2R4s398eropw2qU7GhJEwgwNB8Y56GWh4dDHPSYTNcJXgmkvz4809SA/132', '0', '微服汇', '18112907714', 'test', '123456', 100.00, '2022-05-16 16:13:19');

-- ----------------------------
-- Table structure for base_member_auth
-- ----------------------------
DROP TABLE IF EXISTS `base_member_auth`;
CREATE TABLE `base_member_auth`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'openid',
  `auth_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '授权类型',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `OPENID_UNIQUE`(`openid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '授权' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_member_auth
-- ----------------------------
INSERT INTO `base_member_auth` VALUES (2, 68, 'o1HEb0bbQgdd1aPNyr2ZXvutSU8U', 'wechat', '2022-05-16 16:13:19');

-- ----------------------------
-- Table structure for book_advert
-- ----------------------------
DROP TABLE IF EXISTS `book_advert`;
CREATE TABLE `book_advert`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pic_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `enable` tinyint(4) NULL DEFAULT NULL COMMENT '是否启用，0：禁用，1：启用',
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_advert
-- ----------------------------
INSERT INTO `book_advert` VALUES (1, 'http://localhost:8080/img/banner-1.jpg', 1, NULL, 1, '2020-10-31 13:44:57');
INSERT INTO `book_advert` VALUES (2, 'http://localhost:8080/img/banner-2.jpg', 1, NULL, 2, '2020-10-31 13:45:13');

-- ----------------------------
-- Table structure for book_book
-- ----------------------------
DROP TABLE IF EXISTS `book_book`;
CREATE TABLE `book_book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书名称',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '分类ID',
  `press` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `pic_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '上下架',
  `stock` int(11) NULL DEFAULT NULL COMMENT '库存',
  `describe` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图书' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_book
-- ----------------------------
INSERT INTO `book_book` VALUES (2, '活着', 1, '南海出版公司', '余华', 10.00, 'http://localhost:8080/img/book-1.jpg', 1, 0, '', '2020-10-20 22:10:38');
INSERT INTO `book_book` VALUES (3, '旋挖', 2, '南海出版公司', '伊藤润二', 10.00, 'http://localhost:8080/img/book-2.jpg', 1, 1, NULL, '2020-10-20 22:12:05');
INSERT INTO `book_book` VALUES (4, '原则', 3, '南海出版公司', '余华', 10.00, 'http://localhost:8080/img/book-3.jpg', 1, 0, NULL, '2020-10-20 22:13:47');
INSERT INTO `book_book` VALUES (5, '布局一个', 1, '南海出版公司', '余华', 20.00, 'http://localhost:8080/img/book-4.jpg', 1, 3, NULL, '2020-10-31 22:39:37');
INSERT INTO `book_book` VALUES (6, '布局一个', 1, '南海出版公司', '余华', 20.00, 'http://localhost:8080/img/book-4.jpg', 1, 1, NULL, '2020-10-31 22:39:40');
INSERT INTO `book_book` VALUES (7, '夜晚的钱穆听', 1, '南海出版公司', '余华', 30.00, 'http://localhost:8080/img/book-3.jpg', 1, 1, '', '2020-10-31 22:39:43');
INSERT INTO `book_book` VALUES (8, '原则', 1, '南海出版公司', '余华', 50.00, 'http://localhost:8080/img/book-3.jpg', 1, 7, '《活着(新版)》讲述了农村人福贵悲惨的人生遭遇。福贵本是个阔少爷，可他嗜赌如命，终于赌光了家业，一贫如洗。他的父亲被他活活气死，母亲则在穷困中患了重病，福贵前去求药，却在途中被国民党抓去当壮丁。经过几番波折回到家里，才知道母亲早已去世，妻子家珍含辛茹苦地养大两个儿女。此后更加悲惨的命运一次又一次降临到福贵身上，他的妻子、儿女和孙子相继死去，最后只剩福贵和一头老牛相依为命，但老人依旧活着，仿佛比往日更加洒脱与坚强。\n\n《活着(新版)》荣获意大利格林扎纳•卡佛文学奖最高奖项（1998年）、台湾《中国时报》10本好书奖（1994年）、香港“博益”15本好书奖（1994年）、第三届世界华文“冰心文学奖”（2002年），入选香港《亚洲周刊》评选的“20世纪中文小说百年百强”、中国百位批评家和文学编辑评选的“20世纪90年代最有影响的10部作品”。', '2020-10-31 22:39:46');

-- ----------------------------
-- Table structure for book_category
-- ----------------------------
DROP TABLE IF EXISTS `book_category`;
CREATE TABLE `book_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `pic_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_category
-- ----------------------------
INSERT INTO `book_category` VALUES (1, '小说', 'http://localhost:10001/img/icon-1.png', 1, '2020-10-17 19:33:12');
INSERT INTO `book_category` VALUES (2, '管理', 'http://localhost:10001/img/icon-2.png', 2, '2020-10-17 19:35:03');
INSERT INTO `book_category` VALUES (3, '漫画', 'http://localhost:10001/img/icon-3.png', 3, '2020-10-17 19:35:23');

-- ----------------------------
-- Table structure for book_history
-- ----------------------------
DROP TABLE IF EXISTS `book_history`;
CREATE TABLE `book_history`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NULL DEFAULT NULL COMMENT '图书id',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '会员id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '浏览记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_history
-- ----------------------------
INSERT INTO `book_history` VALUES (51, 8, 68, '2022-05-16 16:31:29');
INSERT INTO `book_history` VALUES (52, 2, 68, '2022-05-17 23:13:32');

-- ----------------------------
-- Table structure for book_order
-- ----------------------------
DROP TABLE IF EXISTS `book_order`;
CREATE TABLE `book_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `order_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单金额',
  `order_status` tinyint(3) NULL DEFAULT NULL COMMENT '订单状态，0：已取消，1：借阅中，2：已归还',
  `start_date` datetime(0) NULL DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '结束日期',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_order
-- ----------------------------
INSERT INTO `book_order` VALUES (62, 68, '20221026102236', NULL, 3, '2022-10-26 18:00:34', '2022-11-09 18:00:34', NULL, '2022-10-26 18:00:34');
INSERT INTO `book_order` VALUES (63, 68, '20221026791106', NULL, 3, '2022-10-26 18:03:02', '2022-11-09 18:03:02', NULL, '2022-10-26 18:03:02');
INSERT INTO `book_order` VALUES (64, 68, '20221026179794', NULL, 2, '2022-10-26 18:05:32', '2022-11-09 18:05:32', NULL, '2022-10-26 18:05:32');
INSERT INTO `book_order` VALUES (65, 68, '20221026450753', NULL, 3, '2022-10-26 18:13:00', '2022-11-09 18:13:00', NULL, '2022-10-26 18:13:00');
INSERT INTO `book_order` VALUES (66, 68, '20221026548090', 50.00, 1, '2022-10-26 18:52:37', '2022-11-09 18:52:37', NULL, '2022-10-26 18:52:37');
INSERT INTO `book_order` VALUES (67, 68, '20221026418492', 50.00, 1, '2022-10-26 19:03:17', '2022-11-09 19:03:17', NULL, '2022-10-26 19:03:17');
INSERT INTO `book_order` VALUES (68, 68, '20221031560739', NULL, 1, '2022-10-31 18:33:14', '2022-11-14 18:33:14', '', '2022-10-31 18:33:14');
INSERT INTO `book_order` VALUES (69, 68, '20221031942741', NULL, 2, '2022-10-31 18:36:07', '2022-11-14 18:36:07', '', '2022-10-31 18:36:07');
INSERT INTO `book_order` VALUES (70, 68, '20221103752657', 0.00, 1, '2022-11-03 16:52:28', '2022-11-17 16:52:28', '', '2022-11-03 16:52:28');
INSERT INTO `book_order` VALUES (71, 68, '20221103639081', 0.00, 1, '2022-11-03 16:54:04', '2022-11-17 16:54:04', '', '2022-11-03 16:54:04');
INSERT INTO `book_order` VALUES (72, 68, '20221103566093', 0.00, 2, '2022-11-03 17:07:09', '2022-11-17 17:07:09', '', '2022-11-03 17:07:09');
INSERT INTO `book_order` VALUES (73, 68, '20221107083232', 50.00, 1, '2022-11-07 15:33:03', '2022-11-21 15:33:03', NULL, '2022-11-07 15:33:03');
INSERT INTO `book_order` VALUES (74, 68, '20221107652354', 50.00, 1, '2022-11-07 15:34:29', '2022-11-21 15:34:29', NULL, '2022-11-07 15:34:29');
INSERT INTO `book_order` VALUES (75, 68, '20221107122570', 50.00, 2, '2022-11-07 15:37:36', '2022-11-21 15:37:36', NULL, '2022-11-07 15:37:36');
INSERT INTO `book_order` VALUES (76, 68, '20221107001477', 0.00, 1, '2022-11-07 15:56:07', '2022-11-21 15:56:07', NULL, '2022-11-07 15:56:07');
INSERT INTO `book_order` VALUES (77, 68, '20221108239874', 0.00, 1, '2022-11-08 12:27:11', '2022-11-22 12:27:11', '', '2022-11-08 12:27:11');
INSERT INTO `book_order` VALUES (78, 68, '20221108236474', 0.00, 2, '2022-11-08 12:35:45', '2022-11-22 12:35:45', '', '2022-11-08 12:35:45');
INSERT INTO `book_order` VALUES (79, 68, '20221122203802', 0.00, 1, '2022-11-22 01:29:03', '2022-12-06 01:29:03', NULL, '2022-11-22 01:29:03');
INSERT INTO `book_order` VALUES (80, 68, '20221122481577', 0.00, 2, '2022-11-22 01:31:36', '2022-12-06 01:31:36', NULL, '2022-11-22 01:31:36');

-- ----------------------------
-- Table structure for book_order_book
-- ----------------------------
DROP TABLE IF EXISTS `book_order_book`;
CREATE TABLE `book_order_book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单ID',
  `book_id` int(11) NULL DEFAULT NULL COMMENT '图书ID',
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书名称',
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `pic_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书图片',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_order_book
-- ----------------------------
INSERT INTO `book_order_book` VALUES (57, 62, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', 50.00, '余华');
INSERT INTO `book_order_book` VALUES (58, 63, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', 50.00, '余华');
INSERT INTO `book_order_book` VALUES (59, 64, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', 50.00, '余华');
INSERT INTO `book_order_book` VALUES (60, 65, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', 50.00, '余华');
INSERT INTO `book_order_book` VALUES (61, 66, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', 50.00, '余华');
INSERT INTO `book_order_book` VALUES (62, 67, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', 50.00, '余华');
INSERT INTO `book_order_book` VALUES (63, 68, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', NULL, '余华');
INSERT INTO `book_order_book` VALUES (64, 69, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', NULL, '余华');
INSERT INTO `book_order_book` VALUES (65, 70, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', NULL, '余华');
INSERT INTO `book_order_book` VALUES (66, 71, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', NULL, '余华');
INSERT INTO `book_order_book` VALUES (67, 72, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', NULL, '余华');
INSERT INTO `book_order_book` VALUES (68, 73, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', 50.00, '余华');
INSERT INTO `book_order_book` VALUES (69, 74, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', 50.00, '余华');
INSERT INTO `book_order_book` VALUES (70, 75, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', 50.00, '余华');
INSERT INTO `book_order_book` VALUES (71, 76, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', 50.00, '余华');
INSERT INTO `book_order_book` VALUES (72, 77, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', NULL, '余华');
INSERT INTO `book_order_book` VALUES (73, 78, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', 50.00, '余华');
INSERT INTO `book_order_book` VALUES (74, 79, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', 50.00, '余华');
INSERT INTO `book_order_book` VALUES (75, 80, 8, '原则', 1, 'http://localhost:8080/img/book-3.jpg', 50.00, '余华');

-- ----------------------------
-- Table structure for book_order_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `book_order_evaluation`;
CREATE TABLE `book_order_evaluation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单id',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价内容',
  `star` int(11) NULL DEFAULT NULL,
  `book_id` int(11) NULL DEFAULT NULL COMMENT '图书id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '评价时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单评价' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_order_evaluation
-- ----------------------------
INSERT INTO `book_order_evaluation` VALUES (9, 62, 68, '作者讲解很详细', NULL, 2, '2022-05-22 11:47:20');
INSERT INTO `book_order_evaluation` VALUES (10, 63, 68, '内容很好，值得阅读', NULL, 8, '2022-05-30 12:03:58');
INSERT INTO `book_order_evaluation` VALUES (11, 65, 68, '技术到位', NULL, 8, '2022-10-26 19:33:52');

-- ----------------------------
-- Table structure for book_shelf
-- ----------------------------
DROP TABLE IF EXISTS `book_shelf`;
CREATE TABLE `book_shelf`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `book_id` int(11) NULL DEFAULT NULL COMMENT '图书id',
  `book_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图书名称',
  `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `author` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '作者',
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '书架' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_shelf
-- ----------------------------
INSERT INTO `book_shelf` VALUES (3, 68, 7, '夜晚的钱穆听', 'http://localhost:8080/img/book-3.jpg', 30.00, '余华', 1);
INSERT INTO `book_shelf` VALUES (4, 68, 8, '原则', 'http://localhost:8080/img/book-3.jpg', 50.00, '余华', 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', '2803180149@qq.com', '18021418906', 1, '2021-08-14 11:11:11');

SET FOREIGN_KEY_CHECKS = 1;
