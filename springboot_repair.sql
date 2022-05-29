/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : 127.0.0.1:3306
Source Database       : springboot_repair

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2022-05-29 19:39:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '公告标题',
  `content` text COMMENT '公告内容',
  `user_id` int(200) DEFAULT NULL COMMENT '发布人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(4) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '鼠药投放', '接x环卫所通知，近期将统- -对x片 进行大面积投放鼠药，进行大面积灭鼠行动，我们x花园\n投放鼠药的时间为: 5月25日 下午，投放的主要区域为:小区的公共绿化带、小区楼宇的公共区域、小区楼层内的公共区域、垃圾桶。\n', null, '2022-05-24 10:56:19', '2022-05-24 10:56:19', '1');
INSERT INTO `notice` VALUES ('2', '暴雨天气', '根据气象部门的预测，25日晚到26日有大到暴雨，随着雨季的到来，夏天暴雨天气也增多，提醒大家提前做好预防措施。', null, '2022-05-24 10:58:22', '2022-05-24 10:58:22', '0');
INSERT INTO `notice` VALUES ('3', '停水维修', '由于小区管网维修，本小区将在2022 年6月1日上午9点到下午5点停水，请各位业主及住户提前做好储水储备，由此给您带来的不便，敬请谅解，谢谢合作!\n', null, '2022-05-24 11:00:01', '2022-05-24 11:00:01', '0');
INSERT INTO `notice` VALUES ('4', '文明规范停车', '1、请不要冲闯出入口岗，不要占用他人固定地下停车位，临停车辆请您将车辆停靠在临时停车位上;\n2、不在非停车位区域停车，不在各组团出入口，公共通道、消防通道上停车;\n3、骑线、压线停车等于一车占两位，致使他人车辆无法停靠，属侵犯他人利益的行为，请您将车辆按照车位停放整齐;\n4、如您发现有车阻碍通行，或其他xx停车现象，请您及时与服务中心取得联系，我们将尽快联系该业主移车。\n5、车辆停靠完毕请及时关好门窗，车卡分离，以防物品丢失;让我们携起手来，从我做起、从现在做起，倡导文明停车、规范停车，拒绝违停，共同创建和谐的社区!\n', null, '2022-05-24 11:01:20', '2022-05-24 11:01:20', '0');
INSERT INTO `notice` VALUES ('5', '消防演练', '根据上级机关文件指示精神，保障小区设施设备及业主人身、财产的安全;为增强我管理区\n域护卫人员素质及全体员工的消防意识，提高员工抗御初期火灾的能力，我管理处特定\n于xxxx年xx月xx日在本小区停车场举行消防演习，欢迎各位业主/住户到场观摩。\n特此通知!\n', null, '2022-05-24 11:05:35', '2022-05-24 11:05:35', '0');
INSERT INTO `notice` VALUES ('14', '测试', '测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容', null, '2022-05-24 15:58:54', '2022-05-24 15:58:54', '1');
INSERT INTO `notice` VALUES ('15', '测试', '测试内容', null, '2022-05-25 08:32:12', '2022-05-25 08:32:12', '1');
INSERT INTO `notice` VALUES ('16', 'ceasf', 'sdfsadfsdfsda', '2', '2022-05-26 10:26:11', '2022-05-26 10:26:11', '1');
INSERT INTO `notice` VALUES ('17', 'ewr', 'wer', '1', '2022-05-29 19:37:16', '2022-05-29 19:37:16', '1');

-- ----------------------------
-- Table structure for repair_classify
-- ----------------------------
DROP TABLE IF EXISTS `repair_classify`;
CREATE TABLE `repair_classify` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（分类id）',
  `category_name` varchar(255) DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='报修分类表';

-- ----------------------------
-- Records of repair_classify
-- ----------------------------
INSERT INTO `repair_classify` VALUES ('1', '电力问题');
INSERT INTO `repair_classify` VALUES ('2', '供水问题');
INSERT INTO `repair_classify` VALUES ('3', '燃气问题');

-- ----------------------------
-- Table structure for repair_content
-- ----------------------------
DROP TABLE IF EXISTS `repair_content`;
CREATE TABLE `repair_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '报修用户id',
  `classify_id` int(11) DEFAULT NULL COMMENT '分类id',
  `describe` varchar(255) DEFAULT NULL COMMENT '报修内容描述',
  `address` varchar(255) DEFAULT NULL COMMENT '报修地址',
  `liaison_name` varchar(255) DEFAULT NULL COMMENT '联系人姓名',
  `liaison_phone` varchar(255) DEFAULT NULL COMMENT '联系人电话',
  `state` int(4) DEFAULT '1' COMMENT '处理状态（1：未处理，2：处理中，3：处理完成）',
  `sys_user_id` int(11) DEFAULT NULL COMMENT '处理管理员id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='报修内容表';

-- ----------------------------
-- Records of repair_content
-- ----------------------------
INSERT INTO `repair_content` VALUES ('1', '2', '3', '需更换燃气报警器', '2号楼1单元101', '王路飞', '15026985468', '3', null, '2022-05-14 12:59:42', '2022-05-18 17:03:51', '0');
INSERT INTO `repair_content` VALUES ('2', null, '3', '燃气泄漏', '5号楼2单元401', '王明', '18854695685', '3', '2', '2022-05-24 11:31:36', '2022-05-26 10:25:49', '0');
INSERT INTO `repair_content` VALUES ('3', null, '3', '燃气灶打不着', '2号楼3单元202', '张翼德', '18854692635', '3', null, '2022-05-24 11:36:05', '2022-05-25 08:31:31', '0');
INSERT INTO `repair_content` VALUES ('4', null, '2', '水质浑浊', '2号楼1单元501', '王洋', '13125648895', '1', null, '2022-05-18 11:36:01', '2022-05-24 11:36:09', '1');
INSERT INTO `repair_content` VALUES ('5', null, '2', '水管停水', '4号楼1单元203', '张之洞', '13125648489', '3', null, '2022-05-24 11:37:27', '2022-05-24 11:48:10', '0');
INSERT INTO `repair_content` VALUES ('6', null, '2', '水表自转', '1号楼1单元601', '张之维', '15015498856', '3', null, '2022-05-24 11:38:28', '2022-05-24 11:48:11', '0');
INSERT INTO `repair_content` VALUES ('7', null, '2', '水压小', '1号楼4单元702', '李海生', '13125654889', '1', null, '2022-05-24 11:39:41', '2022-05-24 11:39:45', '0');
INSERT INTO `repair_content` VALUES ('8', null, '2', '水管流水慢', '5号楼2单元202', '小明', '15546695598', '2', null, '2022-05-24 11:42:02', '2022-05-24 11:48:07', '0');
INSERT INTO `repair_content` VALUES ('9', null, '1', '停电', '5号楼3单元302', '维嘟嘟', '18845682648', '3', null, '2022-05-20 11:43:07', '2022-05-24 11:48:13', '0');
INSERT INTO `repair_content` VALUES ('10', null, '1', '怎样缴纳电费', '3号楼1单元202', '唐僧', '18854695685', '1', null, '2022-05-18 11:45:41', '2022-05-19 11:45:44', '0');
INSERT INTO `repair_content` VALUES ('11', null, '2', '水表自转', '4号楼4单元602', '唐三', '15026589845', '1', null, '2022-05-19 11:47:36', '2022-05-20 11:47:41', '0');
INSERT INTO `repair_content` VALUES ('12', null, '1', '停电', '5号楼3单元301', '小光', '13125659854', '1', null, '2022-05-24 11:49:28', '2022-05-24 11:49:31', '0');
INSERT INTO `repair_content` VALUES ('13', null, '3', '燃气泄漏', '6楼号1单元302', '胖子', '15025666594', '2', null, '2022-05-24 11:50:37', '2022-05-24 11:53:09', '0');
INSERT INTO `repair_content` VALUES ('14', null, '1', '怎样缴纳电费', '6号楼2单元101', '张楚岚', '15023659489', '2', '1', '2022-05-24 11:52:01', '2022-05-29 19:37:25', '0');
INSERT INTO `repair_content` VALUES ('15', null, '2', '水质浑浊', '6号楼2单元103', '甚平', '18856595684', '2', null, '2022-05-24 11:52:56', '2022-05-24 15:59:46', '0');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '菜单名',
  `path` varchar(200) DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT '0' COMMENT '是否删除（0未删除 1已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '报修列表', 'repair/repairList', 'RepairList', 'system:repair:list', 'icon-cat-skuQuery', null, null, '0');
INSERT INTO `sys_menu` VALUES ('2', '公告管理', 'notice/notice', 'notice', 'system:notice', 'icon-order-manage', null, null, '0');
INSERT INTO `sys_menu` VALUES ('3', '用户管理', 'system/user', 'user', 'system:user', 'icon-cus-manage', null, null, '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `role_key` varchar(100) DEFAULT NULL COMMENT '角色权限字符串',
  `del_flag` int(1) DEFAULT '0' COMMENT 'del_flag',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('2', '管理员', 'admin', '0', null, null);
INSERT INTO `sys_role` VALUES ('3', '普通用户', 'user', '0', null, null);
INSERT INTO `sys_role` VALUES ('4', '超级管理员', 'super', '0', null, null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(200) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(200) DEFAULT NULL COMMENT '菜单id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('4', '1');
INSERT INTO `sys_role_menu` VALUES ('4', '2');
INSERT INTO `sys_role_menu` VALUES ('4', '3');
INSERT INTO `sys_role_menu` VALUES ('3', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `status` int(4) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` int(4) DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `user_type` int(4) NOT NULL DEFAULT '1' COMMENT '用户类型（0管理员，1普通用户）',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '管理员', '$2a$10$.B1qyTBnl9jnnzw0J1V0tOWjn3PIk/.AFYw7lLyTr8QwIiJFd8BrO', '0', '123467@qq.com', '18875954268', '1', '0', null, '2022-05-16 11:10:18', null, '2022-05-17 11:10:31', '0');
INSERT INTO `sys_user` VALUES ('2', 'zwk', '文阔', '$2a$10$WZm.sj8.hdMckFQoGBnIfOy43onIefG2qBmwpJdK6bq5CX2dlOTmC', '0', '2134549@qq.com', '15059965485', '1', '0', null, '2022-05-17 11:23:10', null, '2022-05-25 08:25:32', '0');
INSERT INTO `sys_user` VALUES ('4', 'hzh', '侯梓航', '$2a$10$AYaln4WdHS3XCJtWGiZ9XeKOs3qo1UW0PWG1tJrHJWyKmxyF53Fba', '0', '6512228@qq.com', '18859545698', '0', '0', null, '2022-05-17 11:23:14', null, '2022-05-24 16:01:17', '1');
INSERT INTO `sys_user` VALUES ('11', 'xcg', '徐晨光', '$2a$10$.B1qyTBnl9jnnzw0J1V0tOWjn3PIk/.AFYw7lLyTr8QwIiJFd8BrO', '0', '32136548@qq.com', '18875698546', '1', '1', null, '2022-05-17 11:23:17', null, '2022-05-18 11:23:48', '0');
INSERT INTO `sys_user` VALUES ('12', 'dxf', '杜肖帆', '$2a$10$.B1qyTBnl9jnnzw0J1V0tOWjn3PIk/.AFYw7lLyTr8QwIiJFd8BrO', '0', '5616845@qq.com', '15056985469', '0', '1', null, '2022-05-17 11:23:21', null, '2022-05-24 11:20:05', '0');
INSERT INTO `sys_user` VALUES ('13', 'lcl', '刘春龙', '$2a$10$88YCoVD8HxO/dOnXkMzRkui3FlLQ9m1nAe8FV9j5/0tNchino3Ooi', '0', '66559184Qqq.com', '13156498548', '1', '1', null, '2022-05-17 11:23:24', null, '2022-05-25 08:35:47', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(200) DEFAULT NULL COMMENT '用户id',
  `role_id` int(200) DEFAULT '0' COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('1', '4');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('11', '2');
INSERT INTO `sys_user_role` VALUES ('14', '2');
INSERT INTO `sys_user_role` VALUES ('15', '2');
INSERT INTO `sys_user_role` VALUES ('12', '2');
INSERT INTO `sys_user_role` VALUES ('12', '3');
INSERT INTO `sys_user_role` VALUES ('13', '3');
INSERT INTO `sys_user_role` VALUES ('13', '2');
INSERT INTO `sys_user_role` VALUES ('4', '3');
INSERT INTO `sys_user_role` VALUES ('4', '2');
INSERT INTO `sys_user_role` VALUES ('17', '2');
