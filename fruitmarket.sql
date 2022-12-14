/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : fruitmarket

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2022-11-15 21:48:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fruit
-- ----------------------------
DROP TABLE IF EXISTS `fruit`;
CREATE TABLE `fruit` (
  `number` varchar(20) NOT NULL,
  `fruitname` varchar(50) DEFAULT NULL,
  `price` double(10,0) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fruit
-- ----------------------------
INSERT INTO `fruit` VALUES ('001', '哈密瓜', '10', 'kg');
INSERT INTO `fruit` VALUES ('002', '橘子', '3', '斤');
INSERT INTO `fruit` VALUES ('003', '香蕉', '4', '斤');
INSERT INTO `fruit` VALUES ('004', 'xx', '11', 'kg');
INSERT INTO `fruit` VALUES ('006', '苹果', '2', 'kg');
INSERT INTO `fruit` VALUES ('007', '橘子', '2', '斤');
INSERT INTO `fruit` VALUES ('008', '22', '22', '22');
INSERT INTO `fruit` VALUES ('009', '22', '22', '22');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin');
INSERT INTO `user` VALUES ('2', 'aa', 'aa');
INSERT INTO `user` VALUES ('3', 'aaaa', '1234');

