/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : wroms

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 23/04/2023 16:22:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dishes
-- ----------------------------
DROP TABLE IF EXISTS `dishes`;
CREATE TABLE `dishes`  (
  `dish_id` int(11) NOT NULL,
  `dish_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `price` decimal(8, 2) NOT NULL,
  `category` enum('appetizer','entree','dessert','beverage') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`dish_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dishes
-- ----------------------------
INSERT INTO `dishes` VALUES (1, 'Caesar Salad', 8.99, 'appetizer', 'Fresh romaine lettuce, parmesan cheese, and croutons', '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `dishes` VALUES (2, 'Tomato Soup', 5.99, 'appetizer', 'Homemade tomato soup, topped with croutons', '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `dishes` VALUES (3, 'Grilled Salmon', 19.99, 'entree', 'Fresh salmon, grilled to perfection, served with steamed vegetables', '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `dishes` VALUES (4, 'Filet Mignon', 29.99, 'entree', '8 oz. filet mignon, cooked to order, served with mashed potatoes and asparagus', '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `dishes` VALUES (5, 'New York Cheesecake', 7.99, 'dessert', 'Rich and creamy cheesecake, topped with fresh strawberries', '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `dishes` VALUES (6, 'Chocolate Mousse', 6.99, 'dessert', 'Decadent chocolate mousse, served with whipped cream', '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `dishes` VALUES (7, 'Coca-Cola', 2.99, 'beverage', '12 oz. can of Coca-Cola', '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `dishes` VALUES (8, 'Iced Tea', 1.99, 'beverage', 'Freshly brewed iced tea, served with lemon and sugar', '2023-04-17 17:19:41', '2023-04-17 17:19:41');

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details`  (
  `order_detail_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `dish_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`order_detail_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `dish_id`(`dish_id`) USING BTREE,
  CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`dish_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_details
-- ----------------------------
INSERT INTO `order_details` VALUES (1, 1, 1, 2, '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `order_details` VALUES (2, 1, 3, 1, '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `order_details` VALUES (3, 2, 2, 2, '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `order_details` VALUES (4, 2, 4, 2, '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `order_details` VALUES (5, 3, 1, 1, '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `order_details` VALUES (6, 3, 7, 2, '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `order_details` VALUES (7, 4, 3, 2, '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `order_details` VALUES (8, 4, 4, 2, '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `order_details` VALUES (9, 4, 8, 4, '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `order_details` VALUES (10, 5, 1, 1, '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `order_details` VALUES (11, 5, 2, 2, '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `order_details` VALUES (12, 5, 5, 1, '2023-04-17 17:19:41', '2023-04-17 17:19:41');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` int(11) NOT NULL,
  `table_id` int(11) NOT NULL,
  `order_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `total_price` decimal(10, 2) NOT NULL,
  `status` enum('open','closed','cancelled') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'open',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `table_id`(`table_id`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`table_id`) REFERENCES `tables` (`table_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, '2023-04-17 18:30:00', 43.96, 'closed', '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `orders` VALUES (2, 2, '2023-04-17 19:00:00', 62.94, 'closed', '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `orders` VALUES (3, 3, '2023-04-17 19:30:00', 25.97, 'open', '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `orders` VALUES (4, 4, '2023-04-17 20:00:00', 69.96, 'open', '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `orders` VALUES (5, 5, '2023-04-17 20:30:00', 37.96, 'cancelled', '2023-04-17 17:19:41', '2023-04-17 17:19:41');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `target` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标对象',
  `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `business_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '业务类型',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `request_url` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求url',
  `result_code` int(11) NULL DEFAULT NULL COMMENT '请求结果编码',
  `request_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求者',
  `request_time` datetime(0) NULL DEFAULT NULL COMMENT '请求时间',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, '根据ID查询产品', 'edu.scau.mis.web.controller.pos.ProductController', 'get', '查询', 'GET', '/product/1', 200, 'admin', '2022-09-14 11:53:34');
INSERT INTO `sys_log` VALUES (2, '查询所有产品', 'edu.scau.mis.web.controller.pos.ProductController', 'listAll', '查询', 'GET', '/product/listAll', 200, 'admin', '2022-09-14 11:57:31');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `menu_sort` int(11) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '人员管理', 0, 1, 'employee', '', 'M', '0', '0', '', 'el-icon-setting', '', '2022-08-30 21:15:36', '', NULL);
INSERT INTO `sys_menu` VALUES (2, '业务管理', 0, 2, 'business', NULL, 'M', '0', '0', NULL, 'el-icon-s-help', '', '2022-08-30 21:15:40', '', NULL);
INSERT INTO `sys_menu` VALUES (3, '用户', 1, 1, 'user', 'employee/user/index', 'C', '0', '0', 'employee:user:list', 'el-icon-user', '', '2022-08-30 21:15:44', '', NULL);
INSERT INTO `sys_menu` VALUES (4, '角色', 1, 2, 'role', 'employee/role/index', 'C', '0', '0', 'employee:role:list', 'el-icon-lock', '', '2022-08-30 21:16:35', '', NULL);
INSERT INTO `sys_menu` VALUES (5, '菜单', 1, 3, 'menu', 'employee/menu/index', 'C', '0', '0', 'employee:menu:list', 'el-icon-menu', '', '2022-08-30 21:17:22', '', NULL);
INSERT INTO `sys_menu` VALUES (6, '销售', 2, 1, 'sale', 'business/sale/index', 'C', '0', '0', 'business:sale:list', 'el-icon-shopping-cart-full', '', '2022-08-30 21:18:36', '', NULL);
INSERT INTO `sys_menu` VALUES (7, '退货', 2, 2, 'return', 'business/return/index', 'C', '0', '0', 'business:return:list', 'el-icon-box', '', '2022-08-30 21:19:27', '', NULL);
INSERT INTO `sys_menu` VALUES (8, '库存查询', 2, 3, 'repository', 'business/repository/index', 'C', '0', '0', 'business:repository:list', 'el-icon-goods', '', '2022-08-30 21:20:37', '', NULL);
INSERT INTO `sys_menu` VALUES (9, '会员管理', 2, 4, 'vip', 'business/vip/index', 'C', '0', '0', 'business:vip:list', 'el-icon-wallet', '', '2022-08-30 21:21:37', '', NULL);
INSERT INTO `sys_menu` VALUES (10, '新增产品', 7, 1, '', NULL, 'F', '0', '0', 'business:product:add', '#', '', '2022-08-31 13:17:11', '', NULL);
INSERT INTO `sys_menu` VALUES (11, '修改产品', 7, 2, '', NULL, 'F', '0', '0', 'business:product:edit', '#', '', '2022-08-31 13:52:14', '', NULL);
INSERT INTO `sys_menu` VALUES (12, '删除产品', 7, 3, '', NULL, 'F', '0', '0', 'business:product:delete', '#', '', '2022-08-31 13:53:18', '', NULL);
INSERT INTO `sys_menu` VALUES (13, '日志', 1, 4, 'log', 'employee/log/index', 'C', '0', '0', 'employee:log:list', 'el-icon-document', '', '2022-09-14 20:28:38', '', NULL);
INSERT INTO `sys_menu` VALUES (14, '仓储管理', 0, 3, 'repository', NULL, 'M', '0', '0', NULL, 'el-icon-suitcase', '', '2022-09-14 20:28:38', '', NULL);
INSERT INTO `sys_menu` VALUES (15, '营业统计', 0, 4, 'achievement', NULL, 'M', '0', '0', NULL, 'el-icon-pie-chart', '', '2022-09-14 20:28:38', '', NULL);
INSERT INTO `sys_menu` VALUES (16, '进货', 14, 1, 'order', 'repository/order/index', 'C', '0', '0', 'repository:order:list', 'el-icon-shopping-cart-2', '', '2022-09-14 20:28:38', '', NULL);
INSERT INTO `sys_menu` VALUES (17, '退货', 14, 2, 'return', 'repository/return/index', 'C', '0', '0', 'repository:return:list', 'el-icon-delete', '', '2022-09-14 20:28:38', '', NULL);
INSERT INTO `sys_menu` VALUES (18, '库存查询', 14, 3, 'query', 'repository/query/index', 'C', '0', '0', 'repository:query:list', 'el-icon-search', '', '2022-09-14 20:28:38', '', NULL);
INSERT INTO `sys_menu` VALUES (19, '员工业绩', 15, 1, 'employee', 'achievement/employee/index', 'C', '0', '0', 'achievement:employee:list', 'el-icon-tickets', '', '2022-09-14 20:28:38', '', NULL);
INSERT INTO `sys_menu` VALUES (20, '店铺营收', 15, 2, 'store', 'achievement/store/index', 'C', '0', '0', 'achievement:store:list', 'el-icon-finished', '', '2022-09-14 20:28:38', '', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(11) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '状态',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', 1, '0', '0', '', '2022-08-28 12:00:54', '', NULL);
INSERT INTO `sys_role` VALUES (2, '收银员', 'cashier', 2, '0', '0', '', '2022-08-28 12:01:26', '', NULL);
INSERT INTO `sys_role` VALUES (3, '经理', 'manager', 3, '0', '0', '', '2022-08-28 12:06:07', '', NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 6);
INSERT INTO `sys_role_menu` VALUES (2, 7);
INSERT INTO `sys_role_menu` VALUES (2, 8);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 6);
INSERT INTO `sys_role_menu` VALUES (3, 7);
INSERT INTO `sys_role_menu` VALUES (3, 8);
INSERT INTO `sys_role_menu` VALUES (3, 9);
INSERT INTO `sys_role_menu` VALUES (3, 10);
INSERT INTO `sys_role_menu` VALUES (3, 11);
INSERT INTO `sys_role_menu` VALUES (3, 12);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '管理员', '13866668888', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '$2a$10$HM/0q0kPHoSxmoJOgTBrqujJvP5TR.jXY48wXYiZJ84CfeORcbVam', '0', '0', '', '2022-08-28 11:58:02', '', NULL);
INSERT INTO `sys_user` VALUES (2, '李金源', '小源', '13577775555', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '$2a$10$HM/0q0kPHoSxmoJOgTBrqujJvP5TR.jXY48wXYiZJ84CfeORcbVam', '0', '0', '', '2022-08-28 11:58:49', '', NULL);
INSERT INTO `sys_user` VALUES (3, '苏利来', '小苏', '13422223333', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '$2a$10$HM/0q0kPHoSxmoJOgTBrqujJvP5TR.jXY48wXYiZJ84CfeORcbVam', '0', '0', '', '2022-08-28 12:06:59', '', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 2);
INSERT INTO `sys_user_role` VALUES (3, 3);

-- ----------------------------
-- Table structure for tables
-- ----------------------------
DROP TABLE IF EXISTS `tables`;
CREATE TABLE `tables`  (
  `table_id` int(11) NOT NULL,
  `table_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `table_capacity` int(11) NOT NULL,
  `status` enum('available','occupied') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'available',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tables
-- ----------------------------
INSERT INTO `tables` VALUES (1, 'Table 1', 4, 'available', '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `tables` VALUES (2, 'Table 2', 6, 'available', '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `tables` VALUES (3, 'Table 3', 2, 'occupied', '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `tables` VALUES (4, 'Table 4', 8, 'available', '2023-04-17 17:19:41', '2023-04-17 17:19:41');
INSERT INTO `tables` VALUES (5, 'Table 5', 4, 'occupied', '2023-04-17 17:19:41', '2023-04-17 17:19:41');

SET FOREIGN_KEY_CHECKS = 1;
