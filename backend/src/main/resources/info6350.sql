/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : info6350

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 28/02/2022 17:51:16
*/

CREATE DATABASE `info6350`;
USE `info6350`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_expense
-- ----------------------------
DROP TABLE IF EXISTS `t_expense`;
CREATE TABLE `t_expense`
(
    `id`             int NOT NULL,
    `name`           varchar(255) DEFAULT NULL,
    `description`    varchar(255) DEFAULT NULL,
    `category`       varchar(255) DEFAULT NULL,
    `cost_in_dollar` double       DEFAULT NULL,
    `user_id`        int NOT NULL,
    `expense_date`   datetime(6)  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_group
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group`
(
    `id`      int NOT NULL,
    `name`    varchar(255) DEFAULT NULL,
    `user_id` int          DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_todo
-- ----------------------------
DROP TABLE IF EXISTS `t_todo`;
CREATE TABLE `t_todo`
(
    `id`           varchar(32) DEFAULT NULL,
    `name`         int         DEFAULT NULL,
    `description`  int         DEFAULT NULL,
    `status`       tinyint(1)  DEFAULT NULL,
    `user_id`      int         DEFAULT NULL,
    `created_time` int         DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`           varchar(32) NOT NULL,
    `first_name`   varchar(255) DEFAULT NULL,
    `last_name`    varchar(255) DEFAULT NULL,
    `email`        varchar(255) DEFAULT NULL,
    `password`     varchar(255) DEFAULT NULL,
    `created_time` datetime(6)  DEFAULT NULL,
    `updated_time` datetime(6)  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user`
VALUES ('1498419533789097985', 'aasd', 'asda', NULL, '$2a$10$xaJN0k6oBKi.ry1nxz0Ck.PJ/izaWgsXAiEE0ZniwDdgDlcCzYBNa',
        '2022-02-28 17:07:10.715000', '2022-02-28 17:07:10.716000');
INSERT INTO `t_user`
VALUES ('1498419651888066561', 'aasd', 'asda', '213', '$2a$10$quGulbew1z1I8diHQTRSjOxBnC6xXPPFIDvso0MsoNUbHvrsCL6G.',
        '2022-02-28 17:07:38.870000', '2022-02-28 17:07:38.871000');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
