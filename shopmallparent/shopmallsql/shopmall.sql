/*
Navicat MySQL Data Transfer

Source Server         : 47.94.10.48
Source Server Version : 50721
Source Host           : 47.94.10.48:3306
Source Database       : shopmall

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-03-13 17:17:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for meite_app_info
-- ----------------------------
DROP TABLE IF EXISTS `meite_app_info`;
CREATE TABLE `meite_app_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `app_name` varchar(50) DEFAULT NULL COMMENT '应用名称',
  `app_id` varchar(50) DEFAULT NULL COMMENT '应用id',
  `app_secret` varchar(32) DEFAULT NULL COMMENT '应用密匙',
  `access_token` varchar(50) DEFAULT NULL COMMENT 'token值',
  `availability` varchar(1) DEFAULT NULL COMMENT '是否可用',
  `revision` int(11) DEFAULT NULL COMMENT '乐观锁',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meite_app_info
-- ----------------------------
INSERT INTO `meite_app_info` VALUES ('1', '东朗教育', 'b50c47fb7e414be7bfcf1e78b56c3afa', '72AC3FE58255562886D00154C81D5343', 'auth_66f3266b4cc640bab6d84be0185f0a04', '0', null, null, null, null, '2020-02-21 09:59:50');

-- ----------------------------
-- Table structure for meite_attribute_key
-- ----------------------------
DROP TABLE IF EXISTS `meite_attribute_key`;
CREATE TABLE `meite_attribute_key` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `CATEGORY_ID` int(11) DEFAULT NULL COMMENT '分类ID',
  `ATTRIBUTE_NAME` varchar(32) DEFAULT NULL COMMENT '属性名称',
  `NAME_SORT` varchar(32) DEFAULT NULL COMMENT '名称排序',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='商品规格Key表 ';

-- ----------------------------
-- Records of meite_attribute_key
-- ----------------------------
INSERT INTO `meite_attribute_key` VALUES ('1', '11', '内存', '0', '1', null, '2019-03-02 15:34:35', '2019-03-02 15:34:35', '2019-03-02 15:34:35');
INSERT INTO `meite_attribute_key` VALUES ('2', '11', '颜色', '0', '1', null, '2019-03-02 15:34:35', '2019-03-02 15:34:35', '2019-03-02 15:34:35');
INSERT INTO `meite_attribute_key` VALUES ('3', '11', '年份', '0', '1', null, '2019-03-02 15:34:35', '2019-03-02 15:34:35', '2019-03-02 15:34:35');
INSERT INTO `meite_attribute_key` VALUES ('4', '11', '尺寸', '0', '1', null, '2019-03-02 15:34:35', '2019-03-02 15:34:35', '2019-03-02 15:34:35');

-- ----------------------------
-- Table structure for meite_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `meite_attribute_value`;
CREATE TABLE `meite_attribute_value` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ATTRIBUTE_ID` varchar(32) DEFAULT NULL COMMENT '属性ID',
  `ATTRIBUTE_VALUE` varchar(32) DEFAULT NULL COMMENT '属性值',
  `VALUE_SORT` varchar(32) DEFAULT NULL COMMENT '值排序',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='商品规格值表 ';

-- ----------------------------
-- Records of meite_attribute_value
-- ----------------------------
INSERT INTO `meite_attribute_value` VALUES ('1', '1', '4G', '0', '1', null, '2019-03-02 15:36:27', '2019-03-02 15:36:27', '2019-03-02 15:36:27');
INSERT INTO `meite_attribute_value` VALUES ('2', '1', '8G', '0', '1', null, '2019-03-02 15:36:42', '2019-03-02 15:36:42', '2019-03-02 15:36:42');
INSERT INTO `meite_attribute_value` VALUES ('3', '1', '16G', '0', '1', null, '2019-03-02 15:36:43', '2019-03-02 15:36:43', '2019-03-02 15:36:43');
INSERT INTO `meite_attribute_value` VALUES ('4', '1', '32G', '0', '1', null, '2019-03-02 15:36:43', '2019-03-02 15:36:43', '2019-03-02 15:36:43');
INSERT INTO `meite_attribute_value` VALUES ('5', '2', '白色', '0', '1', null, '2019-03-02 15:38:55', '2019-03-02 15:38:55', '2019-03-02 15:38:55');
INSERT INTO `meite_attribute_value` VALUES ('6', '2', '红色', '0', '1', null, '2019-03-02 15:38:55', '2019-03-02 15:38:55', '2019-03-02 15:38:55');
INSERT INTO `meite_attribute_value` VALUES ('7', '2', '紫色', '0', '1', null, '2019-03-02 15:38:55', '2019-03-02 15:38:55', '2019-03-02 15:38:55');
INSERT INTO `meite_attribute_value` VALUES ('8', '3', '2017', '0', '1', null, '2019-03-02 15:38:57', '2019-03-02 15:38:57', '2019-03-02 15:38:57');
INSERT INTO `meite_attribute_value` VALUES ('9', '3', '2018', '0', '1', null, '2019-03-02 15:38:57', '2019-03-02 15:38:57', '2019-03-02 15:38:57');
INSERT INTO `meite_attribute_value` VALUES ('10', '3', '2019', '0', '1', null, '2019-03-02 15:38:57', '2019-03-02 15:38:57', '2019-03-02 15:38:57');
INSERT INTO `meite_attribute_value` VALUES ('11', '3', '16寸', '0', '1', null, '2019-03-02 15:38:59', '2019-03-02 15:38:59', '2019-03-02 15:38:59');
INSERT INTO `meite_attribute_value` VALUES ('12', '3', '20寸', '0', '1', null, '2019-03-02 15:38:59', '2019-03-02 15:38:59', '2019-03-02 15:38:59');
INSERT INTO `meite_attribute_value` VALUES ('13', '3', '24寸', '0', '1', null, '2019-03-02 15:38:59', '2019-03-02 15:38:59', '2019-03-02 15:38:59');
INSERT INTO `meite_attribute_value` VALUES ('14', '3', '32寸', '0', '1', null, '2019-03-02 15:38:59', '2019-03-02 15:38:59', '2019-03-02 15:38:59');

-- ----------------------------
-- Table structure for meite_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `meite_blacklist`;
CREATE TABLE `meite_blacklist` (
  `ID` int(11) NOT NULL,
  `ip_addres` varchar(100) DEFAULT NULL COMMENT 'ip地址',
  `restriction_type` int(11) DEFAULT NULL COMMENT '限制类型',
  `availability` int(11) DEFAULT NULL COMMENT '是否可用',
  `revision` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '修改人',
  `UPDATED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meite_blacklist
-- ----------------------------
INSERT INTO `meite_blacklist` VALUES ('1', '127.0.0.1', '0', '1', null, null, null, null, null);

-- ----------------------------
-- Table structure for meite_category
-- ----------------------------
DROP TABLE IF EXISTS `meite_category`;
CREATE TABLE `meite_category` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '父ID',
  `NAME` varchar(128) DEFAULT NULL COMMENT '名称',
  `STATUS` int(11) DEFAULT NULL COMMENT '状态',
  `SORT_ORDER` int(11) DEFAULT NULL COMMENT '分类顺序',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='商品分类 商品分类信息表';

-- ----------------------------
-- Records of meite_category
-- ----------------------------
INSERT INTO `meite_category` VALUES ('1', '0', '家用电器', '0', '0', null, null, '2019-03-02 15:00:57', '2019-03-02 15:00:57', '2019-03-02 15:00:57');
INSERT INTO `meite_category` VALUES ('2', '1', '空调', '0', '0', null, null, '2019-03-02 15:02:08', '2019-03-02 15:02:08', '2019-03-02 15:02:08');
INSERT INTO `meite_category` VALUES ('3', '1', '冰箱', '0', '0', null, null, '2019-03-02 15:02:08', '2019-03-02 15:02:08', '2019-03-02 15:02:08');
INSERT INTO `meite_category` VALUES ('4', '1', '洗衣机', '0', '0', null, null, '2019-03-02 15:02:08', '2019-03-02 15:02:08', '2019-03-02 15:02:08');
INSERT INTO `meite_category` VALUES ('5', '1', '生活电器', '0', '0', null, null, '2019-03-02 15:02:08', '2019-03-02 15:02:08', '2019-03-02 15:02:08');
INSERT INTO `meite_category` VALUES ('6', '2', '中央空调', '0', '0', null, null, '2019-03-02 15:03:19', '2019-03-02 15:03:19', '2019-03-02 15:03:19');
INSERT INTO `meite_category` VALUES ('7', '2', '柜式空调', '0', '0', null, null, '2019-03-02 15:03:19', '2019-03-02 15:03:19', '2019-03-02 15:03:19');
INSERT INTO `meite_category` VALUES ('8', '0', '电脑办公', '0', '0', null, null, '2019-03-02 15:28:39', '2019-03-02 15:28:39', '2019-03-02 15:28:39');
INSERT INTO `meite_category` VALUES ('9', '8', '电脑整机', '0', '0', null, null, '2019-03-02 15:28:49', '2019-03-02 15:28:49', '2019-03-02 15:28:49');
INSERT INTO `meite_category` VALUES ('10', '8', '电脑配件', '0', '0', null, null, '2019-03-02 15:28:49', '2019-03-02 15:28:49', '2019-03-02 15:28:49');
INSERT INTO `meite_category` VALUES ('11', '9', '平板电脑', '0', '0', null, null, '2019-03-02 15:30:10', '2019-03-02 15:30:10', '2019-03-02 15:30:10');
INSERT INTO `meite_category` VALUES ('12', '9', '笔记本', '0', '0', null, null, '2019-03-02 15:30:10', '2019-03-02 15:30:10', '2019-03-02 15:30:10');

-- ----------------------------
-- Table structure for meite_integral
-- ----------------------------
DROP TABLE IF EXISTS `meite_integral`;
CREATE TABLE `meite_integral` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '积分id',
  `USER_ID` int(32) DEFAULT NULL COMMENT '用户ID ',
  `PAYMENT_ID` varchar(32) DEFAULT NULL COMMENT '支付ID',
  `INTEGRAL` int(5) DEFAULT NULL COMMENT '积分',
  `AVAILABILITY` int(1) DEFAULT NULL COMMENT '是否可用',
  `REVISION` int(2) DEFAULT NULL COMMENT '乐观锁',
  `createdBy` varchar(10) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `updatedBy` varchar(10) DEFAULT NULL,
  `updatedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meite_integral
-- ----------------------------
INSERT INTO `meite_integral` VALUES ('1', '1', '123445', '100', '1', '0', null, '2020-02-21 00:10:54', null, '2020-02-21 00:10:54');
INSERT INTO `meite_integral` VALUES ('2', '1', '1582215314957', '100', '1', '0', null, '2020-02-21 00:15:17', null, '2020-02-21 00:15:17');
INSERT INTO `meite_integral` VALUES ('3', '1', '1582215349363', '100', '1', '0', null, '2020-02-21 00:15:50', null, '2020-02-21 00:15:50');

-- ----------------------------
-- Table structure for meite_order
-- ----------------------------
DROP TABLE IF EXISTS `meite_order`;
CREATE TABLE `meite_order` (
  `seckill_id` bigint(20) NOT NULL COMMENT '库存商品id',
  `user_phone` varchar(11) NOT NULL COMMENT '用户手机号',
  `state` varchar(2) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meite_order
-- ----------------------------
INSERT INTO `meite_order` VALUES ('1', '13223078468', '1', '2020-02-23 17:26:12');

-- ----------------------------
-- Table structure for meite_product
-- ----------------------------
DROP TABLE IF EXISTS `meite_product`;
CREATE TABLE `meite_product` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `CATEGORY_ID` int(11) DEFAULT NULL COMMENT '类型ID',
  `NAME` varchar(128) DEFAULT NULL COMMENT '名称',
  `SUBTITLE` varchar(128) DEFAULT NULL COMMENT '小标题',
  `MAIN_IMAGE` varchar(128) DEFAULT NULL COMMENT '主图像',
  `SUB_IMAGES` text COMMENT '小标题图像',
  `DETAIL` text COMMENT '描述',
  `ATTRIBUTE_LIST` varchar(128) DEFAULT NULL COMMENT '商品规格',
  `PRICE` decimal(32,8) DEFAULT NULL COMMENT '价格',
  `STOCK` int(11) DEFAULT NULL COMMENT '库存',
  `STATUS` int(11) DEFAULT NULL COMMENT '状态',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Records of meite_product
-- ----------------------------
INSERT INTO `meite_product` VALUES ('1', '11', 'Pad平板电脑无线局域网', 'Pad平板电脑', 'http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg', '{\"imgages\":[{\"http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg\"},{\"http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg\"}]}', '官方授权Pad苹果电脑', '1,2,3', '3200.00000000', '3000', '0', '1', 'admin', '2019-03-02 16:02:40', 'admin', '2019-03-02 16:02:40');
INSERT INTO `meite_product` VALUES ('2', '11', 'iPhone XS Max', '苹果Max', 'http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg', '{\"imgages\":[{\"http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg\"},{\"http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg\"}]}', '官方授权苹果Max', '1,2,3', '5600.00000000', '3000', '0', '1', 'admin', '2020-02-14 18:40:40', 'yaogx', '2020-02-14 18:40:40');
INSERT INTO `meite_product` VALUES ('3', '11', '苹果 max', '苹果Max', 'http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg', '{\"imgages\":[{\"http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg\"},{\"http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg\"}]}', '官方授权苹果Max', '1,2,3', '5288.00000000', '3000', '0', '1', 'admin', '2020-02-14 18:40:40', 'yaogx', '2020-02-14 18:40:40');
INSERT INTO `meite_product` VALUES ('4', '11', '华为 Mate10 手机', '华为 Mate10', 'http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg', '{\"imgages\":[{\"http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg\"},{\"http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg\"}]}', '华为Mate10', '1,2,3', '2800.00000000', '3000', '0', '1', 'admin', '2020-02-14 18:40:40', 'yaogx', '2020-02-14 18:40:40');
INSERT INTO `meite_product` VALUES ('5', '11', '华为 Mate30 智能手机', 'HUAWEI Mate 30', 'http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg', '{\"imgages\":[{\"http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg\"},{\"http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg\"}]}', 'HUAWEI Mate 30 5G 全网通 8GB+128GB 麒麟990 4000万超感光徕卡三摄（亮黑色）', '1,2,3', '4999.00000000', '3000', '0', '1', 'admin', '2020-02-14 18:40:40', 'yaogx', '2020-02-14 18:40:40');
INSERT INTO `meite_product` VALUES ('6', '11', '华为 荣耀V30 ', '荣耀V30 ', 'http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg', '{\"imgages\":[{\"http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg\"},{\"http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg\"}]}', '荣耀V30 双模5G 麒麟990 突破性相机矩阵 6GB+128GB 曙光之橙', '1,2,3', '2999.00000000', '3000', '0', '1', 'admin', '2020-02-14 18:40:40', 'yaogx', '2020-02-14 18:40:40');
INSERT INTO `meite_product` VALUES ('7', '11', '华为 HUAWEI nova 6', 'HUAWEI nova 6', 'http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg', '{\"imgages\":[{\"http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg\"},{\"http://img.iblimg.com/photo-42/3020/1135120490_200x200.jpg\"}]}', 'HUAWEI nova 6 5G双模全网通 8GB+128GB 麒麟990芯片 前置3200万像素自动对焦', '1,2,3', '3999.00000000', '3000', '0', '1', 'admin', '2020-02-14 18:40:40', 'yaogx', '2020-02-14 18:40:40');

-- ----------------------------
-- Table structure for meite_product_specs
-- ----------------------------
DROP TABLE IF EXISTS `meite_product_specs`;
CREATE TABLE `meite_product_specs` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `PRODUCT_ID` int(11) DEFAULT NULL COMMENT '商品ID',
  `PRODUCT_SPECS` text COMMENT '商品规格',
  `SPECS_SEQ` int(11) DEFAULT NULL COMMENT '规格顺序',
  `PRODUCT_STOCK` int(11) DEFAULT NULL COMMENT '商品库存',
  `PRODUCT_PRICE` decimal(32,8) DEFAULT NULL COMMENT '商品价格',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品规格表 ';

-- ----------------------------
-- Records of meite_product_specs
-- ----------------------------
INSERT INTO `meite_product_specs` VALUES ('1', '1', '{\"内存\":\"4G\",\"颜色\":\"红色\",\"年份\":\"2019\",\"尺寸\":\"16寸\"}', '0', '30', '3699.00000000', '1', null, '2019-03-02 15:50:04', '2019-03-02 15:50:04', '2019-03-02 15:50:04');
INSERT INTO `meite_product_specs` VALUES ('2', '1', '{\"内存\":\"8G\",\"颜色\":\"白色\",\"年份\":\"2019\",\"尺寸\":\"16寸\"}', '0', '30', '3899.00000000', '1', null, '2019-03-02 15:50:04', '2019-03-02 15:50:04', '2019-03-02 15:50:04');
INSERT INTO `meite_product_specs` VALUES ('3', '1', '{\"内存\":\"16G\",\"颜色\":\"白色\",\"年份\":\"2019\",\"尺寸\":\"16寸\"}', '0', '30', '4199.00000000', '1', null, '2019-03-02 15:50:04', '2019-03-02 15:50:04', '2019-03-02 15:50:04');

-- ----------------------------
-- Table structure for meite_seckill
-- ----------------------------
DROP TABLE IF EXISTS `meite_seckill`;
CREATE TABLE `meite_seckill` (
  `seckill_id` bigint(20) NOT NULL COMMENT '商品库存id',
  `name` varchar(50) NOT NULL COMMENT '商品名称',
  `inventory` int(11) NOT NULL COMMENT '库存数量',
  `start_time` datetime NOT NULL COMMENT '秒杀开始时间',
  `end_time` datetime NOT NULL COMMENT '秒杀结束时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`seckill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meite_seckill
-- ----------------------------
INSERT INTO `meite_seckill` VALUES ('1', 'iPhone XS', '0', '2020-02-22 17:33:08', '2020-02-29 17:33:12', '2020-02-22 17:33:17', '100');

-- ----------------------------
-- Table structure for meite_user
-- ----------------------------
DROP TABLE IF EXISTS `meite_user`;
CREATE TABLE `meite_user` (
  `USER_ID` int(12) NOT NULL AUTO_INCREMENT COMMENT 'user_id',
  `MOBILE` varchar(11) NOT NULL COMMENT '手机号',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '邮箱号',
  `PASSWORD` varchar(64) NOT NULL COMMENT '密码',
  `USER_NAME` varchar(50) DEFAULT NULL COMMENT '用户名',
  `SEX` tinyint(1) DEFAULT '0' COMMENT '性别  1男  2女',
  `AGE` tinyint(3) DEFAULT '0' COMMENT '年龄',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `IS_AVALIBLE` tinyint(1) DEFAULT '1' COMMENT '是否可用 1正常  2冻结',
  `PIC_IMG` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `QQ_OPENID` varchar(50) DEFAULT NULL COMMENT 'QQ联合登陆id',
  `WX_OPENID` varchar(50) DEFAULT NULL COMMENT '微信公众号关注id',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `MOBILE_UNIQUE` (`MOBILE`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='用户会员表';

-- ----------------------------
-- Records of meite_user
-- ----------------------------
INSERT INTO `meite_user` VALUES ('26', '13223078567', 'string@163.com', 'E10ADC3949BA59ABBE56E057F20F883E', 'yaogx', '1', '23', '2020-02-11 14:46:11', '1', null, null, null);
INSERT INTO `meite_user` VALUES ('27', '15256986709', '15256986709@163.com', 'E10ADC3949BA59ABBE56E057F20F883E', 'yaom896753044', '0', '43', '2020-02-05 14:46:15', '1', null, null, null);
INSERT INTO `meite_user` VALUES ('28', '15256986700', null, 'E10ADC3949BA59ABBE56E057F20F883E', 'yaom1466052580', '1', '12', '2020-02-07 14:46:19', '1', null, null, null);

-- ----------------------------
-- Table structure for meite_user_token
-- ----------------------------
DROP TABLE IF EXISTS `meite_user_token`;
CREATE TABLE `meite_user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT NULL,
  `login_type` varchar(255) DEFAULT NULL,
  `device_infor` varchar(255) DEFAULT NULL,
  `is_availability` int(2) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meite_user_token
-- ----------------------------
INSERT INTO `meite_user_token` VALUES ('5', 'dongl.member.loginf12cc4953e4943fd8955819c7d2dbea1', 'ios', 'iPhone XS Max', '1', '25', '2020-02-07 18:38:51', '2020-02-07 18:43:54');
INSERT INTO `meite_user_token` VALUES ('8', 'dongl.member.login6c2052220cb348f7b0a3c98618a32911', 'ios', 'iPhone XS Max', '1', '25', '2020-02-07 18:42:33', '2020-02-07 18:43:54');
INSERT INTO `meite_user_token` VALUES ('9', 'dongl.member.login10267efdc7354debb6fac3423857bc70', 'ios', 'iPhone XS Max', '1', '25', '2020-02-07 18:43:20', '2020-02-07 18:43:54');
INSERT INTO `meite_user_token` VALUES ('10', 'dongl.member.login62d15b99d4de436997e4690d8099c5b2', 'ios', 'iPhone XS Max', '0', '25', '2020-02-07 18:43:54', null);
INSERT INTO `meite_user_token` VALUES ('11', 'dongl.member.login1ddb08b3acae462e923b3022a2d42a69', 'pc', 'iPhone XS Max', '0', '25', '2020-02-07 18:54:12', null);
INSERT INTO `meite_user_token` VALUES ('14', 'dongl.member.login_20462213562042118bb015ca2c6b3b13', 'Android', '华为 Mate X', '1', '25', '2020-02-07 22:15:28', '2020-02-07 22:16:17');
INSERT INTO `meite_user_token` VALUES ('15', 'dongl.member.login_f030b546e9cf40228a20b6a64eb90155', 'Android', '华为 Mate X', '1', '25', '2020-02-07 22:15:41', '2020-02-07 22:16:17');
INSERT INTO `meite_user_token` VALUES ('16', 'dongl.member.login_a00cef94c6aa489f98c1709fdcfa43b3', 'Android', '华为 Mate X', '0', '25', '2020-02-07 22:16:17', null);
INSERT INTO `meite_user_token` VALUES ('17', 'dongl.member.login_8eac5f638c4d4d8fb46fe324bc4eeda8', 'PC', 'Chrome 8/80.0.3987.87', '1', '26', '2020-02-09 13:55:23', '2020-02-09 17:14:33');
INSERT INTO `meite_user_token` VALUES ('18', 'dongl.member.login_0368bd7d0da14c19a022893c03fd6784', 'PC', 'Chrome 8/80.0.3987.87', '1', '26', '2020-02-09 13:55:38', '2020-02-09 17:14:33');
INSERT INTO `meite_user_token` VALUES ('19', 'dongl.member.login_22b5a9cf4485434e914fb4bdee887f7e', 'PC', 'Chrome 8/80.0.3987.87', '1', '26', '2020-02-09 14:23:41', '2020-02-09 17:14:33');
INSERT INTO `meite_user_token` VALUES ('20', 'dongl.member.login_06558505019d4ba0bd268236908b0ad1', 'PC', 'Chrome 8/80.0.3987.87', '1', '26', '2020-02-09 14:25:13', '2020-02-09 17:14:33');
INSERT INTO `meite_user_token` VALUES ('21', 'dongl.member.login_bd14b0a46cd642e9aceac0bcf8845061', 'PC', 'Chrome 8/80.0.3987.87', '1', '26', '2020-02-09 14:52:35', '2020-02-09 17:14:33');
INSERT INTO `meite_user_token` VALUES ('22', 'dongl.member.login_5ca2f7efaffc4038b8fb96a0aa08079f', 'PC', 'Chrome 8/80.0.3987.87', '1', '26', '2020-02-09 16:08:22', '2020-02-09 17:14:33');
INSERT INTO `meite_user_token` VALUES ('23', 'dongl.member.login_c7ff60e603c44ae6ace8688b25d60108', 'PC', 'Chrome 8/80.0.3987.87', '1', '26', '2020-02-09 16:08:22', '2020-02-09 17:14:33');
INSERT INTO `meite_user_token` VALUES ('24', 'dongl.member.login_4059acb071034f0a9fb391759fb1f9b7', 'PC', 'Chrome 8/80.0.3987.87', '1', '26', '2020-02-09 16:23:57', '2020-02-09 17:14:33');
INSERT INTO `meite_user_token` VALUES ('25', 'dongl.member.login_70be589974b94e00bc0a02ae28fb08cb', 'PC', 'Chrome 8/80.0.3987.87', '1', '26', '2020-02-09 16:56:30', '2020-02-09 17:14:33');
INSERT INTO `meite_user_token` VALUES ('26', 'dongl.member.login_4bf2faff3f7249f68ce6ea0f974569b8', 'PC', 'Chrome 8/80.0.3987.87', '1', '26', '2020-02-09 17:14:33', '2020-02-09 17:26:00');
INSERT INTO `meite_user_token` VALUES ('27', 'dongl.member.login_f4e7176db79d4a1480a274ad5a71ed68', 'PC', 'Chrome 8/80.0.3987.87', '1', '26', '2020-02-09 17:26:30', '2020-02-09 17:26:32');
INSERT INTO `meite_user_token` VALUES ('28', 'dongl.member.login_8103827684504bff8b20c3e4f29d54aa', 'PC', 'Chrome 8/80.0.3987.87', '1', '26', '2020-02-11 20:51:10', '2020-02-11 20:51:35');

-- ----------------------------
-- Table structure for payment_channel
-- ----------------------------
DROP TABLE IF EXISTS `payment_channel`;
CREATE TABLE `payment_channel` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `CHANNEL_NAME` varchar(32) NOT NULL COMMENT '渠道名称',
  `CHANNEL_ID` varchar(32) NOT NULL COMMENT '渠道ID',
  `MERCHANT_ID` varchar(32) NOT NULL COMMENT '商户id',
  `SYNC_URL` text NOT NULL COMMENT '同步回调URL',
  `ASYN_URL` text NOT NULL COMMENT '异步回调URL',
  `PUBLIC_KEY` text COMMENT '公钥',
  `PRIVATE_KEY` text COMMENT '私钥',
  `CHANNEL_STATE` int(11) DEFAULT '0' COMMENT '渠道状态 0开启1关闭',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CLASS_ADDRES` varchar(255) DEFAULT NULL COMMENT '类文件地址',
  `RETRY_BEANID` varchar(32) DEFAULT NULL COMMENT '重试接口beanId',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`,`CHANNEL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='支付渠道 ';

-- ----------------------------
-- Records of payment_channel
-- ----------------------------
INSERT INTO `payment_channel` VALUES ('1', '银联支付', 'yinlian_pay', '777290058178517', 'http://localhost:8080/ACPSample_B2C/frontRcvResponse', 'http://dongl.natapp1.cc/unionPayAsynCallback', '', '', '0', null, 'com.dongl.pay.strategy.impl.UnionPayStrategy', null, '', '2020-02-18 16:08:50', '', '2020-02-18 16:09:03');
INSERT INTO `payment_channel` VALUES ('2', '支付宝支付', 'ali_pay', '777290058110049', 'http://localhost:8080/ACPSample_B2C/frontRcvResponse', 'http://222.222.222.222:8080/ACPSample_B2C/backRcvResponse', '', '', '0', null, 'com.dongl.pay.strategy.impl.AliPayStrategy', null, '', '2020-02-18 16:08:50', '', '2020-02-18 16:09:03');

-- ----------------------------
-- Table structure for payment_transaction
-- ----------------------------
DROP TABLE IF EXISTS `payment_transaction`;
CREATE TABLE `payment_transaction` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `PAY_AMOUNT` int(11) NOT NULL COMMENT '支付金额',
  `PAYMENT_STATUS` int(11) NOT NULL DEFAULT '0' COMMENT '支付状态 0待支付1已经支付2支付超时3支付失败',
  `USER_ID` int(11) NOT NULL COMMENT '用户ID',
  `ORDER_ID` varchar(32) NOT NULL COMMENT '订单号码',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `PARTYPAY_ID` varchar(32) DEFAULT NULL COMMENT '第三方支付ID',
  `PAYMENT_ID` varchar(32) DEFAULT NULL COMMENT '使用雪花算法生成的 支付id',
  `COMMODITYNAME` varchar(100) DEFAULT NULL COMMENT '商品名',
  `PAYMENT_CHANNEL` varchar(50) DEFAULT NULL COMMENT '支付渠道',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='支付交易 ';

-- ----------------------------
-- Records of payment_transaction
-- ----------------------------
INSERT INTO `payment_transaction` VALUES ('1', '1000', '0', '1', '20180302101452', null, '3fc29cdb9aa6', 'abd065fdaec2', 'Dell银联支付', '银联支付', '姚明', '2020-02-18 22:33:13', 'admin', '2020-02-18 22:33:16');
INSERT INTO `payment_transaction` VALUES ('2', '1000', '0', '1', '20180302101453', null, '1bd4cb1bfbba', '2d60a61e-56c92d60a61e', null, null, null, '2019-03-21 19:06:15', null, '2019-03-21 19:06:15');
INSERT INTO `payment_transaction` VALUES ('3', '1000', '0', '1', '1', null, null, '1bd4cb1bfbbe', null, null, null, '2019-03-21 19:31:42', null, '2019-03-21 19:31:42');
INSERT INTO `payment_transaction` VALUES ('4', '1000', '0', '1234', '300077d11', null, null, '389797441442025472', null, null, null, '2020-02-19 01:22:42', null, '2020-02-19 01:22:42');
INSERT INTO `payment_transaction` VALUES ('5', '1000', '0', '1234', '300077d11', null, null, '389798578337484800', null, null, null, '2020-02-19 01:27:13', null, '2020-02-19 01:27:13');
INSERT INTO `payment_transaction` VALUES ('6', '2000', '0', '1234', '1234567', null, null, '390529815884730368', null, null, null, '2020-02-21 01:53:15', null, '2020-02-21 01:53:15');
INSERT INTO `payment_transaction` VALUES ('7', '2000', '0', '1234', '1234567', null, null, '390533623599730688', null, null, null, '2020-02-21 02:08:03', null, '2020-02-21 02:08:03');
INSERT INTO `payment_transaction` VALUES ('8', '2000', '0', '1234', '1234567', null, null, '390535671682568192', null, null, null, '2020-02-21 02:16:31', null, '2020-02-21 02:16:31');
INSERT INTO `payment_transaction` VALUES ('9', '2000', '0', '1234', '1234567', null, null, '390536596652429312', null, null, null, '2020-02-21 02:19:51', null, '2020-02-21 02:19:51');
INSERT INTO `payment_transaction` VALUES ('10', '3000', '0', '12343', '3234567', null, null, '390537907343069184', null, null, null, '2020-02-21 02:25:24', null, '2020-02-21 02:25:24');
INSERT INTO `payment_transaction` VALUES ('11', '300222', '0', '644064', '2019010203501502', null, null, '390765854226255872', null, null, null, '2020-02-21 17:30:52', null, '2020-02-21 17:30:52');
INSERT INTO `payment_transaction` VALUES ('12', '3000', '0', '12343', '3234567', null, null, '390766349032493056', null, null, null, '2020-02-21 17:32:49', null, '2020-02-21 17:32:49');
INSERT INTO `payment_transaction` VALUES ('13', '300222', '0', '644064', '2019010203501502', null, null, '390766586715312128', null, null, null, '2020-02-21 17:33:46', null, '2020-02-21 17:33:46');
INSERT INTO `payment_transaction` VALUES ('14', '300222', '0', '644064', '2019010203501502', null, null, '390766696572522496', null, null, null, '2020-02-21 17:34:12', null, '2020-02-21 17:34:12');
INSERT INTO `payment_transaction` VALUES ('15', '300222', '0', '644064', '2019010203501502', null, null, '390768976352907264', null, null, null, '2020-02-21 17:43:36', null, '2020-02-21 17:43:36');
INSERT INTO `payment_transaction` VALUES ('16', '300222', '0', '644064', '2019010203501502', null, null, '390769491304386560', null, null, null, '2020-02-21 17:45:18', null, '2020-02-21 17:45:18');
INSERT INTO `payment_transaction` VALUES ('17', '300222', '0', '644064', '2019010203501502', null, null, '390769551115161600', null, null, null, '2020-02-21 17:45:33', null, '2020-02-21 17:45:33');
INSERT INTO `payment_transaction` VALUES ('18', '300222', '0', '644064', '2019010203501502', null, null, '390771332956164096', null, null, null, '2020-02-21 17:52:58', null, '2020-02-21 17:52:58');
INSERT INTO `payment_transaction` VALUES ('19', '300222', '0', '644064', '2019010203501502', null, null, '390774699203891200', null, null, null, '2020-02-21 18:06:01', null, '2020-02-21 18:06:01');
INSERT INTO `payment_transaction` VALUES ('20', '300222', '0', '644064', '2019010203501502', null, null, '390778669284593664', null, null, null, '2020-02-21 18:22:07', null, '2020-02-21 18:22:07');
INSERT INTO `payment_transaction` VALUES ('21', '300222', '0', '644064', '2019010203501502', null, null, '390779601405743104', null, null, null, '2020-02-21 18:25:29', null, '2020-02-21 18:25:29');
INSERT INTO `payment_transaction` VALUES ('22', '300222', '0', '644064', '2019010203501502', null, null, '390805594300354560', null, null, null, '2020-02-21 20:09:06', null, '2020-02-21 20:09:06');
INSERT INTO `payment_transaction` VALUES ('23', '300222', '0', '644064', '2019010203501502', null, null, '390810554488459264', null, null, null, '2020-02-21 20:28:49', null, '2020-02-21 20:28:49');
INSERT INTO `payment_transaction` VALUES ('24', '3000', '0', '12343', '3234567', null, null, '390879150770098176', null, null, null, '2020-02-22 01:01:04', null, '2020-02-22 01:01:04');
INSERT INTO `payment_transaction` VALUES ('25', '3000', '0', '12343', '3234567', null, null, '390880616025034752', null, null, null, '2020-02-22 01:07:13', null, '2020-02-22 01:07:13');
INSERT INTO `payment_transaction` VALUES ('26', '3000', '0', '12343', '3234567', null, null, '390885238496169984', null, null, null, '2020-02-22 01:25:15', null, '2020-02-22 01:25:15');

-- ----------------------------
-- Table structure for payment_transaction_log
-- ----------------------------
DROP TABLE IF EXISTS `payment_transaction_log`;
CREATE TABLE `payment_transaction_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `SYNCH_LOG` text COMMENT '同步回调日志',
  `ASYNC_LOG` text COMMENT '异步回调日志',
  `CHANNEL_ID` int(11) DEFAULT NULL COMMENT '支付渠道ID',
  `TRANSACTION_ID` int(11) DEFAULT NULL COMMENT '支付交易ID',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `untitled` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付交易日志表 ';

-- ----------------------------
-- Records of payment_transaction_log
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(10) DEFAULT NULL COMMENT '名字',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '姚明', '2020-02-12 17:56:49');
INSERT INTO `user` VALUES ('2', '华为', '2020-02-14 16:25:01');
INSERT INTO `user` VALUES ('3', 'iPhone XS ', '2020-02-14 17:10:46');
INSERT INTO `user` VALUES ('4', '小米', '2020-02-14 17:26:03');
