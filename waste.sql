/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : waste

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-06-24 14:28:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1为后台admin\r\n; 2为volun_admin\r\n; 0为super_admin',
  `tel` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0为已删除; 1为未删除;',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin', '1', null, 'AsteriskAzurain310@outlook.com', '1');
INSERT INTO `admin` VALUES ('2', 'admin1', '123', '1', null, null, '1');
INSERT INTO `admin` VALUES ('3', 'deleteme', '111111', '1', null, null, '0');
INSERT INTO `admin` VALUES ('4', 'deletemee', 'updated', '1', 'string', 'string', '0');
INSERT INTO `admin` VALUES ('5', 'test04171', 'updated', '0', 'string', 'string', '1');
INSERT INTO `admin` VALUES ('6', 'test04172', '2346mbh', '1', 'string', null, '1');
INSERT INTO `admin` VALUES ('7', 'test04173', '2347', '0', null, null, '1');

-- ----------------------------
-- Table structure for ip_address
-- ----------------------------
DROP TABLE IF EXISTS `ip_address`;
CREATE TABLE `ip_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `ipaddr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `del_flag` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_ip_user` (`userid`),
  CONSTRAINT `fk_ip_user` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of ip_address
-- ----------------------------

-- ----------------------------
-- Table structure for point_detail
-- ----------------------------
DROP TABLE IF EXISTS `point_detail`;
CREATE TABLE `point_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `num` int(11) NOT NULL,
  `source` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_point_user` (`userid`),
  CONSTRAINT `fk_point_user` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of point_detail
-- ----------------------------
INSERT INTO `point_detail` VALUES ('1', '6', '2020-06-01 19:58:18', '100', '下单奖励');
INSERT INTO `point_detail` VALUES ('2', '6', '2020-06-02 19:59:17', '100', '下单奖励');
INSERT INTO `point_detail` VALUES ('3', '6', '2020-06-03 19:59:18', '100', '下单奖励');
INSERT INTO `point_detail` VALUES ('4', '6', '2020-06-04 19:59:18', '100', '下单奖励');
INSERT INTO `point_detail` VALUES ('5', '6', '2020-06-05 19:59:19', '100', '下单奖励');
INSERT INTO `point_detail` VALUES ('6', '6', '2020-06-10 19:59:19', '100', '下单奖励');
INSERT INTO `point_detail` VALUES ('7', '6', '2020-06-11 19:59:20', '100', '下单奖励');
INSERT INTO `point_detail` VALUES ('8', '6', '2020-06-13 19:59:20', '100', '下单奖励');
INSERT INTO `point_detail` VALUES ('9', '6', '2020-06-17 19:59:21', '100', '下单奖励');
INSERT INTO `point_detail` VALUES ('10', '6', '2020-06-18 19:59:21', '100', '下单奖励');
INSERT INTO `point_detail` VALUES ('11', '6', '2020-06-21 19:59:22', '100', '下单奖励');
INSERT INTO `point_detail` VALUES ('12', '6', '2020-06-21 12:07:28', '100', '下单奖励');
INSERT INTO `point_detail` VALUES ('13', '6', '2020-06-21 14:46:30', '100', '下单奖励');
INSERT INTO `point_detail` VALUES ('14', '6', '2020-06-23 19:30:30', '100', '下单奖励');
INSERT INTO `point_detail` VALUES ('15', '5', '2020-06-24 05:55:20', '100', '下单奖励');
INSERT INTO `point_detail` VALUES ('16', '7', '2020-06-24 06:16:26', '100', '下单奖励');

-- ----------------------------
-- Table structure for point_question
-- ----------------------------
DROP TABLE IF EXISTS `point_question`;
CREATE TABLE `point_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `text` varchar(8192) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_q_user` (`userid`),
  CONSTRAINT `fk_q_user` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of point_question
-- ----------------------------
INSERT INTO `point_question` VALUES ('1', '1', '类别一', '测试数据', null);

-- ----------------------------
-- Table structure for recycle_order
-- ----------------------------
DROP TABLE IF EXISTS `recycle_order`;
CREATE TABLE `recycle_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `siteid` int(11) DEFAULT NULL,
  `pointnumber` float(10,0) DEFAULT NULL,
  `totalnumber` int(11) NOT NULL,
  `totalprice` float(10,2) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `del_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0为已删除; 1为未删除;',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_order_user` (`userid`),
  KEY `fk_order_site` (`siteid`),
  CONSTRAINT `fk_order_site` FOREIGN KEY (`siteid`) REFERENCES `recycle_site` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_order_user` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of recycle_order
-- ----------------------------
INSERT INTO `recycle_order` VALUES ('1', '6', '1', '100', '5', '12.00', '2020-06-21 12:32:27', '1');
INSERT INTO `recycle_order` VALUES ('2', '6', '6', '100', '3', '12.00', '2020-06-21 12:33:04', '1');
INSERT INTO `recycle_order` VALUES ('5', '6', '3', '100', '12', '28.00', '2020-06-21 10:07:45', '1');
INSERT INTO `recycle_order` VALUES ('6', '6', '3', '100', '12', '36.00', '2020-06-21 10:16:36', '1');
INSERT INTO `recycle_order` VALUES ('7', '6', '3', '100', '9', '13.00', '2020-06-21 10:36:48', '1');
INSERT INTO `recycle_order` VALUES ('8', '6', '3', '100', '9', '13.00', '2020-06-21 10:38:16', '1');
INSERT INTO `recycle_order` VALUES ('9', '6', '7', '100', '6', '16.00', '2020-06-21 10:49:45', '1');
INSERT INTO `recycle_order` VALUES ('10', '6', '7', '100', '5', '13.00', '2020-06-21 10:51:09', '1');
INSERT INTO `recycle_order` VALUES ('11', '6', '11', '100', '6', '12.00', '2020-06-21 10:53:15', '1');
INSERT INTO `recycle_order` VALUES ('12', '6', '15', '100', '2', '6.00', '2020-06-21 10:55:11', '1');
INSERT INTO `recycle_order` VALUES ('13', '6', '3', '100', '3', '9.00', '2020-06-21 11:22:57', '1');
INSERT INTO `recycle_order` VALUES ('14', '6', '4', '100', '3', '9.00', '2020-06-21 12:07:28', '1');
INSERT INTO `recycle_order` VALUES ('15', '6', '4', '100', '5', '13.00', '2020-06-21 14:46:30', '1');
INSERT INTO `recycle_order` VALUES ('16', '6', '5', '100', '1', '2.00', '2020-06-23 19:30:30', '1');
INSERT INTO `recycle_order` VALUES ('18', '1', '1', '100', '2', '4.00', '2020-06-24 10:21:30', '1');
INSERT INTO `recycle_order` VALUES ('19', '1', '2', '100', '2', '4.00', '2020-06-24 10:21:30', '1');
INSERT INTO `recycle_order` VALUES ('20', '2', '1', '100', '2', '4.00', '2020-06-24 10:21:30', '1');
INSERT INTO `recycle_order` VALUES ('21', '2', '2', '100', '2', '4.00', '2020-06-24 10:21:30', '1');
INSERT INTO `recycle_order` VALUES ('22', '3', '1', '100', '2', '4.00', '2020-06-24 10:21:30', '1');
INSERT INTO `recycle_order` VALUES ('23', '3', '2', '100', '2', '4.00', '2020-06-24 10:21:30', '1');
INSERT INTO `recycle_order` VALUES ('24', '4', '1', '100', '2', '4.00', '2020-06-24 10:21:30', '1');
INSERT INTO `recycle_order` VALUES ('25', '4', '2', '100', '2', '4.00', '2020-06-24 10:21:30', '1');
INSERT INTO `recycle_order` VALUES ('26', '5', '1', '100', '2', '4.00', '2020-06-24 10:21:30', '1');
INSERT INTO `recycle_order` VALUES ('27', '5', '2', '100', '2', '4.00', '2020-06-24 10:21:30', '1');
INSERT INTO `recycle_order` VALUES ('28', '5', '8', '100', '3', '9.00', '2020-06-24 05:55:20', '1');
INSERT INTO `recycle_order` VALUES ('29', '7', '9', '100', '4', '12.00', '2020-06-24 06:16:26', '1');

-- ----------------------------
-- Table structure for recycle_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `recycle_order_detail`;
CREATE TABLE `recycle_order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` int(11) NOT NULL,
  `rubbishid` int(11) NOT NULL,
  `del_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0为已删除; 1为未删除;',
  PRIMARY KEY (`id`),
  KEY `fk_order` (`orderid`),
  KEY `fk_detail_rb` (`rubbishid`),
  CONSTRAINT `fk_detail_rb` FOREIGN KEY (`rubbishid`) REFERENCES `rubbish` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_order` FOREIGN KEY (`orderid`) REFERENCES `recycle_order` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of recycle_order_detail
-- ----------------------------
INSERT INTO `recycle_order_detail` VALUES ('1', '1', '3', '1');
INSERT INTO `recycle_order_detail` VALUES ('2', '1', '2', '1');
INSERT INTO `recycle_order_detail` VALUES ('3', '1', '5', '1');
INSERT INTO `recycle_order_detail` VALUES ('4', '1', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('5', '1', '3', '1');
INSERT INTO `recycle_order_detail` VALUES ('6', '2', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('7', '2', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('8', '2', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('9', '8', '2', '1');
INSERT INTO `recycle_order_detail` VALUES ('10', '8', '2', '1');
INSERT INTO `recycle_order_detail` VALUES ('11', '8', '2', '1');
INSERT INTO `recycle_order_detail` VALUES ('12', '8', '2', '1');
INSERT INTO `recycle_order_detail` VALUES ('13', '8', '2', '1');
INSERT INTO `recycle_order_detail` VALUES ('14', '8', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('15', '8', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('16', '8', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('17', '8', '1', '1');
INSERT INTO `recycle_order_detail` VALUES ('18', '1', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('19', '1', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('20', '1', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('21', '1', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('22', '1', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('23', '1', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('24', '1', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('25', '1', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('26', '1', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('27', '1', '1', '1');
INSERT INTO `recycle_order_detail` VALUES ('28', '1', '1', '1');
INSERT INTO `recycle_order_detail` VALUES ('29', '11', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('30', '11', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('31', '11', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('32', '11', '2', '1');
INSERT INTO `recycle_order_detail` VALUES ('33', '11', '2', '1');
INSERT INTO `recycle_order_detail` VALUES ('34', '11', '2', '1');
INSERT INTO `recycle_order_detail` VALUES ('35', '12', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('36', '12', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('37', '13', '5', '1');
INSERT INTO `recycle_order_detail` VALUES ('38', '13', '5', '1');
INSERT INTO `recycle_order_detail` VALUES ('39', '13', '5', '1');
INSERT INTO `recycle_order_detail` VALUES ('40', '14', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('41', '14', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('42', '14', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('43', '15', '3', '1');
INSERT INTO `recycle_order_detail` VALUES ('44', '15', '3', '1');
INSERT INTO `recycle_order_detail` VALUES ('45', '15', '3', '1');
INSERT INTO `recycle_order_detail` VALUES ('46', '15', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('47', '15', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('48', '16', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('49', '18', '1', '1');
INSERT INTO `recycle_order_detail` VALUES ('50', '18', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('51', '19', '1', '1');
INSERT INTO `recycle_order_detail` VALUES ('52', '19', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('53', '20', '1', '1');
INSERT INTO `recycle_order_detail` VALUES ('54', '20', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('55', '21', '1', '1');
INSERT INTO `recycle_order_detail` VALUES ('56', '21', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('57', '22', '1', '1');
INSERT INTO `recycle_order_detail` VALUES ('58', '22', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('59', '23', '1', '1');
INSERT INTO `recycle_order_detail` VALUES ('61', '23', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('62', '24', '1', '1');
INSERT INTO `recycle_order_detail` VALUES ('63', '24', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('64', '25', '1', '1');
INSERT INTO `recycle_order_detail` VALUES ('65', '25', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('66', '26', '1', '1');
INSERT INTO `recycle_order_detail` VALUES ('67', '26', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('68', '27', '1', '1');
INSERT INTO `recycle_order_detail` VALUES ('69', '27', '6', '1');
INSERT INTO `recycle_order_detail` VALUES ('70', '28', '5', '1');
INSERT INTO `recycle_order_detail` VALUES ('71', '28', '5', '1');
INSERT INTO `recycle_order_detail` VALUES ('72', '28', '5', '1');
INSERT INTO `recycle_order_detail` VALUES ('73', '29', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('74', '29', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('75', '29', '4', '1');
INSERT INTO `recycle_order_detail` VALUES ('76', '29', '3', '1');

-- ----------------------------
-- Table structure for recycle_site
-- ----------------------------
DROP TABLE IF EXISTS `recycle_site`;
CREATE TABLE `recycle_site` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sitename` varchar(255) NOT NULL,
  `area` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0为已删除; 1为未删除;',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of recycle_site
-- ----------------------------
INSERT INTO `recycle_site` VALUES ('1', '测试站点1', '奉贤区', '奉贤区站点1', '1');
INSERT INTO `recycle_site` VALUES ('2', '测试站点2', '浦东新区', '浦东新区站点1', '1');
INSERT INTO `recycle_site` VALUES ('3', '测试站点3', '虹口区', '虹口区站点1', '1');
INSERT INTO `recycle_site` VALUES ('4', '测试站点4', '奉贤区', '奉贤区站点2', '1');
INSERT INTO `recycle_site` VALUES ('5', '测试站点5', '奉贤区', '奉贤区站点3', '1');
INSERT INTO `recycle_site` VALUES ('6', '测试站点6', '杨浦区', '杨浦区站点1', '1');
INSERT INTO `recycle_site` VALUES ('7', '测试站点7', '虹口区', '虹口区站点2', '1');
INSERT INTO `recycle_site` VALUES ('8', '测试站点8', '闵行区', '闵行区站点1', '1');
INSERT INTO `recycle_site` VALUES ('9', '测试站点9', '徐汇区', '徐汇区站点1', '1');
INSERT INTO `recycle_site` VALUES ('10', '测试站点10', '宝山区', '宝山区站点1', '1');
INSERT INTO `recycle_site` VALUES ('11', '测试站点11', '崇明岛', '崇明岛站点1', '1');
INSERT INTO `recycle_site` VALUES ('12', '测试站点12', '宝山区', '宝山区站点2', '1');
INSERT INTO `recycle_site` VALUES ('13', '测试站点13', '徐汇区', '徐汇区站点2', '1');
INSERT INTO `recycle_site` VALUES ('14', '测试站点14', '黄浦区', '黄浦区站点1', '1');
INSERT INTO `recycle_site` VALUES ('15', '测试站点15', '奉贤区', '奉贤区站点4', '1');

-- ----------------------------
-- Table structure for rubbish
-- ----------------------------
DROP TABLE IF EXISTS `rubbish`;
CREATE TABLE `rubbish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `typeid` int(11) NOT NULL,
  `price` float(10,2) DEFAULT NULL,
  `del_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0为已删除; 1为未删除;',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_rb_type` (`typeid`),
  CONSTRAINT `fk_rb_type` FOREIGN KEY (`typeid`) REFERENCES `rubbish_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of rubbish
-- ----------------------------
INSERT INTO `rubbish` VALUES ('1', '废纸张', '2', '2.00', '1');
INSERT INTO `rubbish` VALUES ('2', '废塑料', '2', '1.00', '1');
INSERT INTO `rubbish` VALUES ('3', '废玻璃', '2', '3.00', '1');
INSERT INTO `rubbish` VALUES ('4', '废金属', '2', '3.00', '1');
INSERT INTO `rubbish` VALUES ('5', '废织品', '2', '3.00', '1');
INSERT INTO `rubbish` VALUES ('6', '其他可回收物', '2', '2.00', '1');

-- ----------------------------
-- Table structure for rubbish_type
-- ----------------------------
DROP TABLE IF EXISTS `rubbish_type`;
CREATE TABLE `rubbish_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `del_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0为已删除; 1为未删除;',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of rubbish_type
-- ----------------------------
INSERT INTO `rubbish_type` VALUES ('1', '干垃圾', '1');
INSERT INTO `rubbish_type` VALUES ('2', '可回收物', '1');
INSERT INTO `rubbish_type` VALUES ('3', '湿垃圾', '1');
INSERT INTO `rubbish_type` VALUES ('4', '有害垃圾', '1');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `id` int(11) NOT NULL,
  `rubbishid` int(11) NOT NULL,
  `whsename` varchar(255) NOT NULL,
  `num` int(11) NOT NULL,
  `del_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0为已删除; 1为未删除;',
  PRIMARY KEY (`id`),
  KEY `fb_whse` (`whsename`),
  KEY `fb_store_rb` (`rubbishid`),
  CONSTRAINT `fb_store_rb` FOREIGN KEY (`rubbishid`) REFERENCES `rubbish` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of store
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `realname` varchar(20) DEFAULT NULL,
  `tel` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `role` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1为普通业主; 2为志愿者;',
  `point` int(11) DEFAULT '0',
  `del_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0为已删除; 1为未删除;',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'firstuser', 'ppssdd', 'zhang', '04180342', '1', '0', '1');
INSERT INTO `user` VALUES ('2', 'seconduser', 'update12', 'chi', '04180345', '1', '0', '0');
INSERT INTO `user` VALUES ('3', 'test0619', '123456', null, '138123456', '1', '0', '1');
INSERT INTO `user` VALUES ('4', 'xiaoming', '123456', '', '138159456', '1', '0', '1');
INSERT INTO `user` VALUES ('5', '小红', 'redred', '', '1564', '2', '100', '1');
INSERT INTO `user` VALUES ('6', 'user', 'user', 'user', '1385555', '1', '1400', '1');
INSERT INTO `user` VALUES ('7', '小明', '121212', '王明', '13817777777', '2', '100', '1');

-- ----------------------------
-- Table structure for user_total_data
-- ----------------------------
DROP TABLE IF EXISTS `user_total_data`;
CREATE TABLE `user_total_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `rubbishid` int(11) NOT NULL,
  `rubbishtype` int(11) DEFAULT NULL,
  `number` int(11) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '1' COMMENT '0为已删除; 1为未删除;',
  PRIMARY KEY (`id`),
  KEY `fk_total_user` (`userid`),
  KEY `fk_total_rb` (`rubbishid`),
  CONSTRAINT `fk_total_rb` FOREIGN KEY (`rubbishid`) REFERENCES `rubbish` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_total_user` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_total_data
-- ----------------------------
INSERT INTO `user_total_data` VALUES ('1', '1', '3', '2', '27', null, '1');
INSERT INTO `user_total_data` VALUES ('2', '1', '2', '2', '6', null, '1');
INSERT INTO `user_total_data` VALUES ('3', '1', '1', '2', '7', null, '1');
INSERT INTO `user_total_data` VALUES ('4', '1', '2', '2', '22', null, '1');
INSERT INTO `user_total_data` VALUES ('5', '1', '3', '2', '35', null, '1');
INSERT INTO `user_total_data` VALUES ('6', '1', '1', '2', '40', null, '1');

-- ----------------------------
-- Procedure structure for add2order
-- ----------------------------
DROP PROCEDURE IF EXISTS `add2order`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add2order`()
BEGIN
  DECLARE i INT DEFAULT 1;
	DECLARE i1 INT DEFAULT 1;

  WHILE i1 < 6 DO
		INSERT INTO `recycle_order` (`userid`, `siteid`, `pointnumber`, `totalnumber`, `totalprice`, `createtime`) VALUES (i1, '1', '100', '2', '4', NOW());
		INSERT INTO `recycle_order` (`userid`, `siteid`, `pointnumber`, `totalnumber`, `totalprice`, `createtime`) VALUES (i1, '2', '100', '2', '4', NOW());
		SET i1=i1+1;	
  END WHILE;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for add2orderdetail
-- ----------------------------
DROP PROCEDURE IF EXISTS `add2orderdetail`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add2orderdetail`()
BEGIN
  DECLARE i INT DEFAULT 24;
	DECLARE i1 INT DEFAULT 1;

  WHILE i < 28 DO
		INSERT INTO `recycle_order_detail` (`orderid`, `rubbishid`) VALUES (i, '1');
		INSERT INTO `recycle_order_detail` (`orderid`, `rubbishid`) VALUES (i, '6');
		SET i=i+1;	
  END WHILE;

END
;;
DELIMITER ;
