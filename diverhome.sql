/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 100110
 Source Host           : localhost
 Source Database       : diverhome

 Target Server Type    : MySQL
 Target Server Version : 100110
 File Encoding         : utf-8

 Date: 04/24/2017 17:17:54 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tb_area_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `tb_area_dictionary`;
CREATE TABLE `tb_area_dictionary` (
  `area_code` char(2) NOT NULL COMMENT '行政区编码',
  `area_name` varchar(8) DEFAULT NULL COMMENT '行政区名称',
  `lng` varchar(32) DEFAULT NULL COMMENT '经度',
  `lat` varchar(32) DEFAULT NULL COMMENT '纬度',
  PRIMARY KEY (`area_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行政区字典表';

-- ----------------------------
--  Table structure for `tb_employee`
-- ----------------------------
DROP TABLE IF EXISTS `tb_employee`;
CREATE TABLE `tb_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `position_id` int(11) DEFAULT NULL COMMENT '职务',
  `state` char(1) DEFAULT NULL COMMENT '在职状态：1 在职 0 离职',
  `type` char(1) DEFAULT NULL COMMENT '员工类型 0 潜水教练 1 其他人员',
  `img_url` varchar(128) DEFAULT NULL COMMENT '头像地址',
  `serial_number` varchar(128) DEFAULT NULL COMMENT '教练证编号',
  `insert_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='员工表';

-- ----------------------------
--  Table structure for `tb_event_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_event_user`;
CREATE TABLE `tb_event_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `wechat` varchar(32) DEFAULT NULL,
  `sex` varchar(8) DEFAULT NULL,
  `qualification` varchar(32) DEFAULT NULL,
  `time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_member_notice`
-- ----------------------------
DROP TABLE IF EXISTS `tb_member_notice`;
CREATE TABLE `tb_member_notice` (
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `notice_id` int(11) NOT NULL DEFAULT '0' COMMENT '已读公告id',
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `INDEX_1` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录用户已读公告';

-- ----------------------------
--  Table structure for `tb_membership`
-- ----------------------------
DROP TABLE IF EXISTS `tb_membership`;
CREATE TABLE `tb_membership` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(32) NOT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `sex` char(1) DEFAULT NULL COMMENT '性别 0：男 1：女',
  `qualification` char(1) DEFAULT NULL COMMENT '潜水资质 0：自由潜水员 1：水肺潜水员 2：非潜水员',
  `area_code` char(2) DEFAULT NULL COMMENT '所在行政区编码',
  `membership_level` char(1) DEFAULT NULL COMMENT '会员级别 0：普通会员 1：vip会员',
  `join_way` char(1) DEFAULT NULL COMMENT '报名方式 0：现场报名 1：微信',
  `openid` varchar(32) DEFAULT NULL COMMENT '微信用户openid',
  `visit_count` int(11) NOT NULL DEFAULT '0' COMMENT '微信访问次数',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '最近一次访问时间',
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX1` (`id`),
  UNIQUE KEY `INDEX2` (`phone`),
  KEY `INDEX3` (`openid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8 COMMENT='会员表';

-- ----------------------------
--  Table structure for `tb_notice`
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `content` varchar(1024) DEFAULT NULL COMMENT '内容',
  `insert_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8 COMMENT='公告表';

-- ----------------------------
--  Table structure for `tb_order`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(32) NOT NULL COMMENT '订单号',
  `state` char(1) NOT NULL DEFAULT '0' COMMENT '订单状态 0：待验证 1：已验证 2：已删除',
  `verification_code` int(6) DEFAULT NULL COMMENT '验证码 6位整数',
  `order_price` decimal(10,0) DEFAULT NULL COMMENT '订单价格 该价格为实际成交价格，可以与产品价格不一致',
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `product_id` int(11) NOT NULL COMMENT '产品id',
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单生成时间',
  `validate_time` timestamp NULL DEFAULT NULL COMMENT '订单验证时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX1` (`id`),
  UNIQUE KEY `INDEX2` (`order_no`),
  UNIQUE KEY `INDEX3` (`verification_code`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
--  Table structure for `tb_position_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `tb_position_dictionary`;
CREATE TABLE `tb_position_dictionary` (
  `id` int(11) NOT NULL,
  `position_id` int(11) DEFAULT NULL COMMENT '职务id',
  `position_desc` varchar(128) DEFAULT NULL COMMENT '职务描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职务字典表';

-- ----------------------------
--  Table structure for `tb_product`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL COMMENT '产品标题',
  `brand` varchar(128) DEFAULT NULL COMMENT '品牌',
  `original_price` decimal(10,2) DEFAULT NULL COMMENT '原价',
  `member_price` decimal(10,2) DEFAULT NULL COMMENT '会员价',
  `img_url` varchar(128) DEFAULT NULL COMMENT '产品图片url',
  `thumbnail_img_url` varchar(128) DEFAULT NULL COMMENT '产品缩略图url',
  `description` varchar(1024) DEFAULT NULL COMMENT '产品描述',
  `quantity` int(11) DEFAULT '1' COMMENT '产品数量',
  `type` char(1) DEFAULT NULL COMMENT '产品类别 0：课程 1：商品',
  `label` varchar(128) DEFAULT '' COMMENT '产品标签',
  `state` char(1) DEFAULT NULL COMMENT '产品状态 0：未上架 1：已上架 2：已删除',
  `insert_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '商品创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='产品表';

-- ----------------------------
--  Table structure for `tb_system_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_user`;
CREATE TABLE `tb_system_user` (
  `id` int(11) NOT NULL,
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `nickname` varchar(32) NOT NULL COMMENT '昵称',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `del` char(1) NOT NULL COMMENT '是否删除 0：删除 1：未删除',
  `rsrv_str1` varchar(64) DEFAULT NULL,
  `rsrv_str2` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
