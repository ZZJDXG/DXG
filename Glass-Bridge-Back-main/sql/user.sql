/*
 Navicat Premium Dump SQL

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 90200 (9.2.0)
 Source Host           : localhost:3306
 Source Schema         : oa

 Target Server Type    : MySQL
 Target Server Version : 90200 (9.2.0)
 File Encoding         : 65001

 Date: 13/01/2026 23:25:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `StaffID` varchar(50) NOT NULL COMMENT '员工编号',

  `StaffName` varchar(50) NOT NULL DEFAULT 'None' COMMENT '员工姓名',

  `StaffGender` tinyint NULL DEFAULT NULL COMMENT '性别 0女 1男',

  `StaffBirthday` date NULL DEFAULT NULL COMMENT '生日',

  `StaffIDCard` varchar(20) NULL DEFAULT NULL COMMENT '身份证号',

  `StaffDept` int NULL DEFAULT NULL COMMENT '部门ID',

  `StaffTel` varchar(20) NOT NULL COMMENT '手机号（登录账号）',

  `StaffPwd` varchar(255) NOT NULL COMMENT '登录密码(SHA-256)',

  `StaffPosition` tinyint NULL DEFAULT 0 COMMENT '角色 0普通员工 1管理员',

  `HireDate` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入职时间',


  PRIMARY KEY (`StaffID`) USING BTREE,
  UNIQUE KEY `uk_tel` (`StaffTel`) USING BTREE
) ENGINE=InnoDB
CHARACTER SET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
ROW_FORMAT=Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

