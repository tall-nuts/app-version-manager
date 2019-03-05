/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : app_version

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 05/03/2019 23:58:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `package_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '包名唯一',
  `app_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'App名称',
  `os` tinyint(1) NULL DEFAULT NULL COMMENT 'App类型：Android、IOS',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'App图标地址',
  `website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '官网地址：IOS填写应用AppStore地址；Andorid可选',
  `logline` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '一句话简介',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '应用简介',
  `create_uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'App创建人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT 'App构建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT 'App更改时间',
  PRIMARY KEY (`id`, `package_name`) USING BTREE,
  UNIQUE INDEX `id_UNIQUE`(`id`) USING BTREE,
  INDEX `apk_os_type`(`os`) USING BTREE,
  INDEX `fk_user_id`(`create_uid`) USING BTREE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`create_uid`) REFERENCES `sys_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for app_upgrade
-- ----------------------------
DROP TABLE IF EXISTS `app_upgrade`;
CREATE TABLE `app_upgrade`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'App Id',
  `version_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '版本名称',
  `version_code` bigint(20) NOT NULL COMMENT '版本号',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `change_log` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '更新日志',
  `env` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环境：\\ndev:开发环境\\ntest:测试环境\\nstaging:演示环境\\nproduction:生产环境',
  `channel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道:\\ne.g:\\ndefault:官网\\nchannel_android_360\\nchannel_android_wandoujia\\nchannel_android_tencent\\nchannel_android_vivo\\nchannel_android_oppo\\nchannel_android_huawei\\nchannel_android_xiaomi\\nchannel_ios_appstore',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '应用状态：0:审核中 1:已上线',
  `resource_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源ID',
  `upgrade_check_period` tinyint(1) NULL DEFAULT NULL COMMENT '更新检查周期：\n0:每次启动检查\n1:1天检查一次',
  `upgrade_is_force` tinyint(1) NULL DEFAULT NULL COMMENT '是否强制更新：\\\\n0：否\\\\n1：是',
  `upgrade_is_silent` tinyint(1) NULL DEFAULT NULL COMMENT '是否静默下载：\n0:否\n1:是',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_UNIQUE`(`id`) USING BTREE,
  INDEX `fk_app_id`(`appid`) USING BTREE,
  INDEX `fk_res_id`(`resource_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级ID',
  `index` int(20) NULL DEFAULT NULL COMMENT '排序',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `object_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'OSS文件名',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件下载地址',
  `mime_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件MIME',
  `size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小',
  `md5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件MD5值',
  `extension` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件扩展名',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `os` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单跳转Url',
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单父级ID',
  `order` int(20) NULL DEFAULT NULL COMMENT '序列ID：用于排序',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单Icon',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('4a8a9ce65fe64149b81922179c66328e', '应用管理', '/', NULL, 1, 'menu-icon fa fa-android');
INSERT INTO `sys_menu` VALUES ('5e2d26e9589b44cf88a6fa5330946a91', '首页', '/', NULL, 1, 'menu-icon fa fa-home');
INSERT INTO `sys_menu` VALUES ('9073b26b67e249cdb7937d3789dd8aa6', '用户管理', '/', NULL, 1, 'menu-icon fa fa-users');
INSERT INTO `sys_menu` VALUES ('99c000987cckd98495a23dcdkeab7ui0', '权限管理', '/manage/permission', '9073b26b67e249cdb7937d3789dd8aa6', 2, NULL);
INSERT INTO `sys_menu` VALUES ('99c000987cckd98495a23dlfd7ab7ui0', '角色管理', '/manage/role', '9073b26b67e249cdb7937d3789dd8aa6', 2, NULL);
INSERT INTO `sys_menu` VALUES ('99c000987cckd98495coc90wkeab7ui0', '日志管理', '/manage/log', '99c006474cdb4c8495a26c9fd7ab4f79', 2, NULL);
INSERT INTO `sys_menu` VALUES ('99c000987cckd98495codwcdkeab7ui0', '菜单管理', '/manage/menu', '99c006474cdb4c8495a26c9fd7ab4f79', 2, NULL);
INSERT INTO `sys_menu` VALUES ('99c006474cckd98495a23dlfd7ab7ui0', '账号管理', '/manage/account', '9073b26b67e249cdb7937d3789dd8aa6', 2, NULL);
INSERT INTO `sys_menu` VALUES ('99c006474cdb4c8495a23dlfd7ab7ui0', '相关文档', '/app/app-doc', '4a8a9ce65fe64149b81922179c66328e', 2, NULL);
INSERT INTO `sys_menu` VALUES ('99c006474cdb4c8495a26c9fd7ab4f79', '系统管理', '/', NULL, 1, 'menu-icon fa fa-desktop');
INSERT INTO `sys_menu` VALUES ('99c006474cdb4c8495a26c9fd7ab7ui0', '创建应用', '/app/app-create', '4a8a9ce65fe64149b81922179c66328e', 2, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '类型：\n0：超级管理员\n1：系统管理员\n2：普通用户',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `crete_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('0799050b3ae84122992d3b5d48c58d19', '超级管理员', 0, '超级管理员（拥有所有权限：应用管理、用户管理、系统管理等）', '2018-12-18 16:16:46');
INSERT INTO `sys_role` VALUES ('22b6331dce0c42119dfaf59ac2873301', '测试人员', 3, '测试人员（拥有应用信息查看权限）', '2018-12-18 16:18:52');
INSERT INTO `sys_role` VALUES ('84c7a7ecd6d74a7eb696b671fc40bff4', '普通管理员', 1, '普通管理员（拥有应用管理、用户管理-账号管理权限）', '2018-12-18 16:17:41');
INSERT INTO `sys_role` VALUES ('cca88204313b4f868e25f5bd7287cc9d', '开发人员', 2, '后端、移动端开发人员（拥有应用管理所有权限）', '2018-12-18 16:18:25');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色ID',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `permission_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '业务主键',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `realname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `sex` tinyint(4) NULL DEFAULT NULL COMMENT '性别：0男 1女',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号：11位',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `status` tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '用户状态\n0：正常\n-1：禁用',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('ec5f0fdafead48f398c0a530eab8520d', 'http://imeina.cn/images/logo.png', 'admin', '高鹏飞', '管理员', '21232f297a57a5a743894a0e4a801fc3', 0, '2018-12-14', '18800000000', 'gaopengfedev@gmail.com', '北京市', '我是一个你可有可无的影子', 1, '2018-12-14 16:52:58', '2018-12-14 16:53:00');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_user_uid`(`user_id`) USING BTREE,
  INDEX `fk_role_id`(`role_id`) USING BTREE,
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_uid` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('3b55b89a0402496c8ba3d039074b0dc5', 'ec5f0fdafead48f398c0a530eab8520d', '0799050b3ae84122992d3b5d48c58d19');

SET FOREIGN_KEY_CHECKS = 1;
