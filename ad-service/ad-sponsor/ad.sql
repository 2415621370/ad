/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : ad

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-09-03 11:47:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ad_creative
-- ----------------------------
DROP TABLE IF EXISTS `ad_creative`;
CREATE TABLE `ad_creative` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ''自增主键'',
  `name` varchar(48) NOT NULL COMMENT ''创意名称'',
  `type` tinyint(4) NOT NULL DEFAULT ''0'' COMMENT ''物料类型(图片, 视频)'',
  `material_type` tinyint(4) NOT NULL DEFAULT ''0'' COMMENT ''物料子类型(图片: bmp, jpg 等等)'',
  `height` int(10) NOT NULL DEFAULT ''0'' COMMENT ''高度'',
  `width` int(10) NOT NULL DEFAULT ''0'' COMMENT ''宽度'',
  `size` bigint(20) NOT NULL DEFAULT ''0'' COMMENT ''物料大小, 单位是 KB'',
  `duration` int(10) NOT NULL DEFAULT ''0'' COMMENT ''持续时长, 只有视频才不为 0'',
  `audit_status` tinyint(4) NOT NULL DEFAULT ''0'' COMMENT ''审核状态'',
  `user_id` bigint(20) NOT NULL DEFAULT ''0'' COMMENT ''标记当前记录所属用户'',
  `url` varchar(256) NOT NULL COMMENT ''物料地址'',
  `create_time` datetime NOT NULL DEFAULT ''1970-01-01 00:00:00'' COMMENT ''创建时间'',
  `update_time` datetime NOT NULL DEFAULT ''1970-01-01 00:00:00'' COMMENT ''更新时间'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT=''创意表'';

-- ----------------------------
-- Records of ad_creative
-- ----------------------------

-- ----------------------------
-- Table structure for ad_plan
-- ----------------------------
DROP TABLE IF EXISTS `ad_plan`;
CREATE TABLE `ad_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ''自增主键'',
  `user_id` bigint(20) NOT NULL DEFAULT ''0'' COMMENT ''标记当前记录所属用户'',
  `plan_name` varchar(48) NOT NULL COMMENT ''推广计划名称'',
  `plan_status` tinyint(4) NOT NULL DEFAULT ''0'' COMMENT ''推广计划状态'',
  `start_date` datetime NOT NULL COMMENT ''推广计划开始时间；'',
  `end_date` datetime NOT NULL COMMENT ''推广计划结束时间；'',
  `create_time` datetime NOT NULL DEFAULT ''1970-01-01 00:00:00'' COMMENT ''创建时间'',
  `update_time` datetime NOT NULL DEFAULT ''1970-01-01 00:00:00'' COMMENT ''更新时间'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT=''推广计划表'';

-- ----------------------------
-- Records of ad_plan
-- ----------------------------
INSERT INTO `ad_plan` VALUES (''10'', ''10'', ''中秋节不放假了'', ''0'', ''2019-09-02 16:00:00'', ''2019-09-05 16:00:00'', ''2019-09-02 07:43:19'', ''2019-09-03 03:27:14'');
INSERT INTO `ad_plan` VALUES (''11'', ''10'', ''中秋节好好学习'', ''1'', ''2019-09-16 16:00:00'', ''2019-09-13 16:00:00'', ''2019-09-02 07:51:56'', ''2019-09-02 07:51:56'');
INSERT INTO `ad_plan` VALUES (''12'', ''10'', ''中秋节好好学习'', ''1'', ''2019-09-16 16:00:00'', ''2019-09-13 16:00:00'', ''2019-09-02 08:46:52'', ''2019-09-02 08:46:52'');
INSERT INTO `ad_plan` VALUES (''13'', ''10'', ''过年了'', ''1'', ''2019-09-16 16:00:00'', ''2019-09-13 16:00:00'', ''2019-09-02 08:55:06'', ''2019-09-02 08:55:07'');
INSERT INTO `ad_plan` VALUES (''14'', ''10'', ''国庆节'', ''1'', ''2019-09-16 16:00:00'', ''2019-09-13 16:00:00'', ''2019-09-03 02:22:51'', ''2019-09-03 02:22:51'');

-- ----------------------------
-- Table structure for ad_unit
-- ----------------------------
DROP TABLE IF EXISTS `ad_unit`;
CREATE TABLE `ad_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ''自增主键'',
  `plan_id` bigint(20) NOT NULL DEFAULT ''0'' COMMENT ''关联推广计划 id'',
  `unit_name` varchar(48) NOT NULL COMMENT ''推广单元名称'',
  `unit_status` tinyint(4) NOT NULL DEFAULT ''0'' COMMENT ''推广单元状态'',
  `position_type` tinyint(4) NOT NULL DEFAULT ''0'' COMMENT ''广告位类型(开屏, 贴片, 中贴, 暂停帖, 后贴)'',
  `budget` bigint(20) NOT NULL COMMENT ''预算'',
  `create_time` datetime NOT NULL DEFAULT ''1970-01-01 00:00:00'' COMMENT ''创建时间'',
  `update_time` datetime NOT NULL DEFAULT ''1970-01-01 00:00:00'' COMMENT ''更新时间'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT=''推广单元表'';

-- ----------------------------
-- Records of ad_unit
-- ----------------------------

-- ----------------------------
-- Table structure for ad_user
-- ----------------------------
DROP TABLE IF EXISTS `ad_user`;
CREATE TABLE `ad_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ''自增主键'',
  `username` varchar(128) NOT NULL DEFAULT '''' COMMENT ''用户名'',
  `token` varchar(256) NOT NULL DEFAULT '''' COMMENT ''给用户生成的 token'',
  `user_status` tinyint(4) NOT NULL DEFAULT ''0'' COMMENT ''用户状态'',
  `create_time` datetime NOT NULL DEFAULT ''1970-01-01 00:00:00'' COMMENT ''创建时间'',
  `update_time` datetime NOT NULL DEFAULT ''1970-01-01 00:00:00'' COMMENT ''更新时间'',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT=''用户信息表'';

-- ----------------------------
-- Records of ad_user
-- ----------------------------
INSERT INTO `ad_user` VALUES (''10'', ''1701B'', ''C4F09F1559C62F3F741203A86A44619D'', ''1'', ''2019-09-02 06:42:41'', ''2019-09-02 06:42:41'');
INSERT INTO `ad_user` VALUES (''11'', ''1701A'', ''55B877306AFF55CE36091ECCA0B8C68F'', ''1'', ''2019-09-02 06:58:00'', ''2019-09-02 06:58:00'');
