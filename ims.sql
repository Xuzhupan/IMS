/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : ims

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-06-03 17:54:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) NOT NULL COMMENT '客户名称',
  `messenger` varchar(10) DEFAULT NULL COMMENT '联系人姓名',
  `tel` varchar(12) DEFAULT NULL COMMENT '练系方式',
  `create_date` date NOT NULL COMMENT '创建日期',
  `information` varchar(128) DEFAULT NULL COMMENT '备注',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态0不可用 1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '建设银行', '', '', '2016-11-29', '', '1');
INSERT INTO `customer` VALUES ('2', '工商银行', null, null, '2016-11-29', '', '1');
INSERT INTO `customer` VALUES ('3', '农业银行', '农民伯伯', '100', '2016-11-29', '', '1');
INSERT INTO `customer` VALUES ('4', '啊啊啊啊地', '', '0771-2222222', '2016-11-04', '', '0');
INSERT INTO `customer` VALUES ('5', '阿飞发啊', '', '0771-2222222', '2016-11-29', '', '0');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) NOT NULL COMMENT '部门名称',
  `manager` varchar(10) DEFAULT NULL COMMENT '部门负责人',
  `tel` varchar(12) DEFAULT NULL COMMENT '部门联系方式',
  `status` int(1) DEFAULT NULL COMMENT '状态0不可用 1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '采购部', '采购部', '0771-2222222', '1');
INSERT INTO `department` VALUES ('2', '销售部', '销售部', '022-1008622', '1');
INSERT INTO `department` VALUES ('3', '人力资源部', '人力资源', '010-1000100', '1');
INSERT INTO `department` VALUES ('4', '仓管部', '仓库', '13877100000', '1');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `sex` int(1) unsigned NOT NULL COMMENT '性别 1男 2女',
  `birthday` date NOT NULL COMMENT '生日',
  `entryday` date NOT NULL COMMENT '入职时间',
  `tel` varchar(12) DEFAULT NULL COMMENT '联系方式',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门号FK',
  `status` int(1) unsigned NOT NULL COMMENT '状态0离职 1在职 2审核中',
  PRIMARY KEY (`id`),
  KEY `dept_id` (`dept_id`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '我', '1', '2016-11-25', '2016-11-25', null, '1', '1');
INSERT INTO `employee` VALUES ('2', '张', '1', '2016-12-14', '2016-12-14', '', '2', '1');
INSERT INTO `employee` VALUES ('3', '李', '1', '2016-12-15', '2016-12-15', '', '3', '1');
INSERT INTO `employee` VALUES ('4', '王大爷', '1', '2016-12-13', '2016-12-18', '', '4', '1');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) NOT NULL COMMENT '物品名称',
  `type` varchar(20) DEFAULT NULL COMMENT '规格型号',
  `unit` varchar(2) NOT NULL COMMENT '单位',
  `information` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态0不可用 1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '洗发水', '清扬', '瓶', '', '1');
INSERT INTO `goods` VALUES ('2', '洗洁精', '立白', '瓶', '', '1');
INSERT INTO `goods` VALUES ('3', '洗衣粉', '雕牌', '袋', '', '1');
INSERT INTO `goods` VALUES ('4', '牛奶', '500ML', '盒', '蒙牛', '1');
INSERT INTO `goods` VALUES ('5', '牛奶', '伊利纯牛奶350ML装', '盒', '', '1');
INSERT INTO `goods` VALUES ('7', '洗衣机', '小天鹅', '台', '', '1');
INSERT INTO `goods` VALUES ('8', '打印机', '爱普生LQ-730', '台', '', '1');
INSERT INTO `goods` VALUES ('10', '牙膏', '黑人', '支', '', '1');
INSERT INTO `goods` VALUES ('11', '牙膏', '高露洁', '支', '', '1');
INSERT INTO `goods` VALUES ('12', '牙膏', '牙博士', '支', '', '1');
INSERT INTO `goods` VALUES ('13', '圆珠笔', '晨光', '支', '', '1');
INSERT INTO `goods` VALUES ('14', '圆珠笔', '爱好', '支', '', '1');
INSERT INTO `goods` VALUES ('15', '钢笔', '英雄100', '支', '', '1');
INSERT INTO `goods` VALUES ('16', '钢笔', '派克卓尔', '支', '', '1');
INSERT INTO `goods` VALUES ('17', '钢笔', '凌美狩猎者', '支', '', '1');
INSERT INTO `goods` VALUES ('18', '方格本', '', '本', '', '1');

-- ----------------------------
-- Table structure for instock_note
-- ----------------------------
DROP TABLE IF EXISTS `instock_note`;
CREATE TABLE `instock_note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_id` int(11) NOT NULL COMMENT '采购计划id',
  `employee_id` int(11) NOT NULL COMMENT '入库人',
  `date` datetime NOT NULL COMMENT '入库时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '入库状态0撤销 1新增',
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  KEY `purchase_id` (`purchase_id`),
  CONSTRAINT `instock_note_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `instock_note_ibfk_3` FOREIGN KEY (`purchase_id`) REFERENCES `purchase_plan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of instock_note
-- ----------------------------
INSERT INTO `instock_note` VALUES ('141', '18', '1', '2017-06-02 23:40:14', '1');
INSERT INTO `instock_note` VALUES ('142', '18', '1', '2017-06-02 23:40:33', '0');
INSERT INTO `instock_note` VALUES ('143', '18', '1', '2017-06-02 23:40:58', '1');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) NOT NULL COMMENT '菜单名称',
  `url` varchar(20) DEFAULT NULL COMMENT '菜单路径',
  `icon` varchar(20) DEFAULT NULL COMMENT '菜单图标',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单',
  `level` int(1) DEFAULT NULL COMMENT '菜单级别 0顶级菜单 1二级菜单 2功能按钮',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 0停用 1可用',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '系统菜单', null, null, null, null, '1');
INSERT INTO `menu` VALUES ('2', '用户管理', 'userIndex', null, '1', null, '1');
INSERT INTO `menu` VALUES ('3', '员工管理', 'employeeIndex', null, '1', null, '1');
INSERT INTO `menu` VALUES ('4', '部门管理', 'departmentIndex', null, '1', null, '1');
INSERT INTO `menu` VALUES ('5', '菜单管理', 'menuIndex', null, '1', null, '1');
INSERT INTO `menu` VALUES ('6', '角色管理', 'roleIndex', null, '1', null, '1');
INSERT INTO `menu` VALUES ('7', '功能菜单', null, null, null, null, '1');
INSERT INTO `menu` VALUES ('8', '货物管理', 'goodsIndex', null, '7', null, '1');
INSERT INTO `menu` VALUES ('9', '客户管理', 'customerIndex', null, '7', null, '1');
INSERT INTO `menu` VALUES ('10', '供应商管理', 'supplierIndex', null, '7', null, '1');
INSERT INTO `menu` VALUES ('11', '库存管理', 'repertoryIndex', null, '7', null, '1');
INSERT INTO `menu` VALUES ('12', '货物库存容量管理', 'repertoryLimitIndex', null, '7', null, '1');
INSERT INTO `menu` VALUES ('13', '采购计划管理', 'purchasePlanIndex', null, '7', null, '1');
INSERT INTO `menu` VALUES ('14', '销售订单管理', 'sellPlanIndex', null, '7', null, '1');
INSERT INTO `menu` VALUES ('15', '出库管理', 'outstockNoteIndex', null, '7', null, '1');
INSERT INTO `menu` VALUES ('16', '入库管理', 'instockNoteIndex', null, '7', null, '1');

-- ----------------------------
-- Table structure for outstock_note
-- ----------------------------
DROP TABLE IF EXISTS `outstock_note`;
CREATE TABLE `outstock_note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sell_id` int(11) NOT NULL COMMENT '销售（提货）计划id',
  `employee_id` int(11) NOT NULL COMMENT '出库人',
  `date` datetime NOT NULL COMMENT '出库时间',
  `status` int(1) DEFAULT NULL COMMENT '状态0操作被撤销 1出库成功',
  PRIMARY KEY (`id`),
  KEY `sell_id` (`sell_id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `outstock_note_ibfk_1` FOREIGN KEY (`sell_id`) REFERENCES `sell_plan` (`id`),
  CONSTRAINT `outstock_note_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of outstock_note
-- ----------------------------
INSERT INTO `outstock_note` VALUES ('35', '3', '1', '2017-06-03 00:48:05', '1');

-- ----------------------------
-- Table structure for purchase_info
-- ----------------------------
DROP TABLE IF EXISTS `purchase_info`;
CREATE TABLE `purchase_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `purchase_id` int(11) NOT NULL COMMENT '采购计划id',
  `goods_id` int(11) NOT NULL COMMENT '物品id',
  `supplier_id` int(11) DEFAULT NULL COMMENT '供应商id',
  `unit_price` double NOT NULL COMMENT '单价',
  `number` int(11) NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`),
  KEY `purchase_info_ibfk_1` (`purchase_id`),
  KEY `purchase_info_ibfk_2` (`goods_id`),
  KEY `purchase_info_ibfk_3` (`supplier_id`),
  CONSTRAINT `purchase_info_ibfk_1` FOREIGN KEY (`purchase_id`) REFERENCES `purchase_plan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `purchase_info_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `purchase_info_ibfk_3` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchase_info
-- ----------------------------
INSERT INTO `purchase_info` VALUES ('56', '18', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for purchase_plan
-- ----------------------------
DROP TABLE IF EXISTS `purchase_plan`;
CREATE TABLE `purchase_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `plan_name` varchar(64) DEFAULT NULL COMMENT '计划名称',
  `employee_id` int(11) NOT NULL COMMENT '计划人 FK',
  `create_date` date NOT NULL COMMENT '创建日期',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态 0未生效 1生效 2完成入库 3计划作废',
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `purchase_plan_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchase_plan
-- ----------------------------
INSERT INTO `purchase_plan` VALUES ('18', '', '1', '2017-06-02', '2');

-- ----------------------------
-- Table structure for repertory
-- ----------------------------
DROP TABLE IF EXISTS `repertory`;
CREATE TABLE `repertory` (
  `goods_id` int(11) NOT NULL COMMENT '物品ID',
  `number` int(11) NOT NULL COMMENT '物品数量',
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `repertory_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repertory
-- ----------------------------
INSERT INTO `repertory` VALUES ('1', '0');
INSERT INTO `repertory` VALUES ('2', '0');
INSERT INTO `repertory` VALUES ('3', '0');
INSERT INTO `repertory` VALUES ('4', '0');
INSERT INTO `repertory` VALUES ('5', '0');
INSERT INTO `repertory` VALUES ('7', '0');
INSERT INTO `repertory` VALUES ('8', '0');
INSERT INTO `repertory` VALUES ('10', '0');
INSERT INTO `repertory` VALUES ('11', '0');
INSERT INTO `repertory` VALUES ('12', '0');
INSERT INTO `repertory` VALUES ('13', '0');
INSERT INTO `repertory` VALUES ('14', '0');
INSERT INTO `repertory` VALUES ('15', '0');
INSERT INTO `repertory` VALUES ('16', '0');
INSERT INTO `repertory` VALUES ('17', '0');
INSERT INTO `repertory` VALUES ('18', '0');

-- ----------------------------
-- Table structure for repertory_limit
-- ----------------------------
DROP TABLE IF EXISTS `repertory_limit`;
CREATE TABLE `repertory_limit` (
  `goods_id` int(11) NOT NULL COMMENT '物品ID',
  `max_number` int(11) DEFAULT NULL COMMENT '最大库存容量',
  `min_number` int(11) DEFAULT NULL COMMENT '最小库存容量',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0未启用 1启用',
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `repertory_limit_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repertory_limit
-- ----------------------------
INSERT INTO `repertory_limit` VALUES ('1', '200', '50', '1');
INSERT INTO `repertory_limit` VALUES ('2', '200', '30', '1');
INSERT INTO `repertory_limit` VALUES ('3', '100', '20', '1');
INSERT INTO `repertory_limit` VALUES ('4', '100', '3', '1');
INSERT INTO `repertory_limit` VALUES ('5', '100', '5', '1');
INSERT INTO `repertory_limit` VALUES ('7', '200', '10', '1');
INSERT INTO `repertory_limit` VALUES ('8', '100', '5', '1');
INSERT INTO `repertory_limit` VALUES ('10', '0', '0', '0');
INSERT INTO `repertory_limit` VALUES ('11', '0', '0', '0');
INSERT INTO `repertory_limit` VALUES ('12', '8', '0', '0');
INSERT INTO `repertory_limit` VALUES ('13', '0', '0', '0');
INSERT INTO `repertory_limit` VALUES ('14', '0', '0', '0');
INSERT INTO `repertory_limit` VALUES ('15', '0', '0', '0');
INSERT INTO `repertory_limit` VALUES ('16', '0', '0', '0');
INSERT INTO `repertory_limit` VALUES ('18', '0', '0', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) NOT NULL COMMENT '角色名',
  `user_Permission` int(10) unsigned NOT NULL COMMENT '用户权限',
  `employee_Permission` int(10) unsigned NOT NULL COMMENT '员工权限',
  `role_Permission` int(10) unsigned NOT NULL COMMENT '角色权限',
  `department_Permission` int(10) unsigned NOT NULL COMMENT '部门权限',
  `goods_Permission` int(10) unsigned NOT NULL COMMENT '商品权限',
  `customer_Permission` int(10) unsigned NOT NULL COMMENT '客户权限',
  `supplier_Permission` int(10) unsigned NOT NULL COMMENT '供应商权限',
  `purchase_Plan_Permission` int(10) unsigned NOT NULL COMMENT '采购订单权限',
  `sell_Plan_Permission` int(10) unsigned NOT NULL COMMENT '销售订单权限',
  `repertory_Permission` int(10) unsigned NOT NULL COMMENT '库存权限',
  `repertory_Limit_Permission` int(10) unsigned NOT NULL COMMENT '设置库存容量权限',
  `instock_Note_Permission` int(10) unsigned NOT NULL COMMENT '入库权限',
  `outstock_Note_Permission` int(10) unsigned NOT NULL COMMENT '出库权限',
  `menu_Permission` int(10) unsigned NOT NULL COMMENT '菜单权限',
  `status` int(1) unsigned NOT NULL COMMENT '状态0不可用 1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '127', '31', '31', '31', '31', '31', '31', '31', '31', '1', '1', '6', '6', '3', '1');
INSERT INTO `role` VALUES ('2', '系统管理员', '63', '15', '15', '15', '15', '15', '15', '15', '15', '1', '1', '6', '6', '3', '1');
INSERT INTO `role` VALUES ('3', '人事', '0', '15', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `role` VALUES ('4', '采购', '0', '0', '0', '0', '15', '0', '15', '15', '0', '1', '0', '0', '0', '0', '1');
INSERT INTO `role` VALUES ('5', '销售', '0', '0', '0', '0', '1', '15', '0', '0', '15', '1', '1', '0', '0', '0', '1');
INSERT INTO `role` VALUES ('6', '仓管', '0', '0', '0', '0', '1', '0', '0', '0', '0', '1', '0', '6', '6', '0', '1');

-- ----------------------------
-- Table structure for sell_info
-- ----------------------------
DROP TABLE IF EXISTS `sell_info`;
CREATE TABLE `sell_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sell_id` int(11) NOT NULL COMMENT '销售（提货）计划ID',
  `goods_id` int(11) NOT NULL COMMENT '物品id',
  `unit_price` double NOT NULL COMMENT '单价',
  `number` int(11) NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`),
  KEY `sell_info_ibfk_1` (`sell_id`),
  KEY `sell_info_ibfk_2` (`goods_id`),
  CONSTRAINT `sell_info_ibfk_1` FOREIGN KEY (`sell_id`) REFERENCES `sell_plan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sell_info_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sell_info
-- ----------------------------
INSERT INTO `sell_info` VALUES ('5', '3', '1', '1', '1');

-- ----------------------------
-- Table structure for sell_plan
-- ----------------------------
DROP TABLE IF EXISTS `sell_plan`;
CREATE TABLE `sell_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `plan_name` varchar(64) DEFAULT NULL COMMENT '计划标题',
  `employee_id` int(11) NOT NULL COMMENT '计划人',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户ID',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  `status` int(1) DEFAULT NULL COMMENT '状态0未生效 1生效 2订单出库完成 3订单作废',
  PRIMARY KEY (`id`),
  KEY `sell_plan_ibfk_1` (`employee_id`),
  KEY `sell_plan_ibfk_2` (`customer_id`),
  CONSTRAINT `sell_plan_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `sell_plan_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sell_plan
-- ----------------------------
INSERT INTO `sell_plan` VALUES ('3', '', '1', '1', '2017-06-03', '2');

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) NOT NULL COMMENT '供应商名称',
  `messenger` varchar(10) DEFAULT NULL COMMENT '联系人名称',
  `tel` varchar(12) DEFAULT NULL COMMENT '联系方式',
  `taxpayer_identity` varchar(15) NOT NULL COMMENT '纳税识别码',
  `bank_name` varchar(20) NOT NULL COMMENT '开户银行名称',
  `bank_account` varchar(20) NOT NULL COMMENT '开户银行帐号',
  `register_tel` varchar(12) NOT NULL COMMENT '公司注册电话',
  `register_address` varchar(32) NOT NULL COMMENT '公司注册地址',
  `create_date` date NOT NULL COMMENT '记录创建日期',
  `information` varchar(64) DEFAULT NULL COMMENT '备注',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态0不可用 1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('1', 'XX市XX省XX百货有限公司', 'A某某', '18666008888', 'saz123213342143', 'XX银行', '888888', '0771-6666666', '帝都胡同', '2016-11-27', '', '1');
INSERT INTO `supplier` VALUES ('2', '中央人民银行', '中国公民', '0771-2222222', 'AX000000000000', '中央人民银行分行', '000000', '0771-8888888', ' XX路100号央行大厦', '2016-11-29', '备注', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(12) NOT NULL COMMENT '用户登陆名',
  `password` varchar(12) NOT NULL COMMENT '密码',
  `employee_id` int(11) DEFAULT NULL COMMENT '员工号（外键1:1）',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `status` int(1) unsigned NOT NULL COMMENT '0-不可用 1-可用',
  PRIMARY KEY (`username`),
  KEY `employee_id` (`employee_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `user_ibfk_3` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('cgbjl', 'cgbjl', '2', '4', '1');
INSERT INTO `user` VALUES ('cjgly', 'cjgly', '1', '1', '1');
INSERT INTO `user` VALUES ('ckgly', 'ckgly', '1', '6', '1');
INSERT INTO `user` VALUES ('rlzybjl', 'rlzybjl', '1', '3', '1');
INSERT INTO `user` VALUES ('xsbjl', 'xsbjl', '1', '5', '1');
INSERT INTO `user` VALUES ('xtgly', 'xtgly', '1', '2', '1');
