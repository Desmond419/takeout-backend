use takeout_db;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
  id                    VARCHAR(255) NOT NULL PRIMARY KEY COMMENT '用户id',
  username              VARCHAR(255) NOT NULL COMMENT '用户名',
  password              VARCHAR(255) NOT NULL COMMENT '密码',
  nickname              VARCHAR(255) COMMENT '昵称',
  status                INTEGER DEFAULT 0 COMMENT '账号状态（0正常 1停用）',
  phone                 VARCHAR(255) COMMENT '手机号',
  gender                VARCHAR(255) COMMENT '用户性别（男，女，未知）',
  user_type             VARCHAR(255) NOT NULL DEFAULT '管理员' COMMENT '用户类型（管理员，普通用户，骑手，商家）',
  create_time           TIMESTAMP COMMENT '创建时间',
  update_time           TIMESTAMP COMMENT '更新时间'
);
INSERT INTO user(id, username, password, user_type)
values
('1','admin', '$2a$10$/PDVtIkLEmjjR.3GrLWg6OwBnV7Z1xXgmR11O1m1K6AMcAeqPqrra', '管理员'), -- password: 123
('2','merchant', '$2a$10$/PDVtIkLEmjjR.3GrLWg6OwBnV7Z1xXgmR11O1m1K6AMcAeqPqrra', '商家'), -- password: 123
('3','rider', '$2a$10$/PDVtIkLEmjjR.3GrLWg6OwBnV7Z1xXgmR11O1m1K6AMcAeqPqrra', '骑手'); -- password: 123

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role
(
  user_id       VARCHAR(255) NOT NULL COMMENT '用户id',
  role_id       VARCHAR(255) NOT NULL COMMENT '角色id',
  PRIMARY KEY (user_id,role_id)
) COMMENT='用户与角色关联表';
INSERT INTO user_role(user_id, role_id)
values
('1','1'),
('2','3'),
('3','2');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS permission;
CREATE TABLE permission (
  id                VARCHAR(255) NOT NULL PRIMARY KEY COMMENT '权限id',
  name              VARCHAR(255) NOT NULL COMMENT '权限名',
  status            CHAR(1) DEFAULT '0' COMMENT '权限状态（0正常 1停用）',
  code              VARCHAR(255) COMMENT '权限代码',
  remark            VARCHAR(255) COMMENT '备注',
  create_time       TIMESTAMP DEFAULT NULL,
  update_time       TIMESTAMP DEFAULT NULL
) COMMENT='权限表';
INSERT INTO permission(id, name, code)
values
('1','系统管理权限','system:admin'),
('2','骑手管理权限','system:rider'),
('3','商家管理权限','system:merchant');


-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS role;
CREATE TABLE role (
  id                VARCHAR(255) NOT NULL PRIMARY KEY COMMENT '角色id',
  name              VARCHAR(255) DEFAULT NULL COMMENT '角色名字',
  status            CHAR(1) DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  remark            VARCHAR(255) DEFAULT NULL COMMENT '备注',
  create_time       TIMESTAMP DEFAULT NULL,
  update_time       TIMESTAMP DEFAULT NULL
) COMMENT='角色表';
INSERT INTO role(id, name)
values ('1','Admin'), ('2','Rider'), ('3','Merchant');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS role_permission;
CREATE TABLE role_permission (
  role_id               VARCHAR(255) NOT NULL COMMENT '角色id',
  permission_id         VARCHAR(255) NOT NULL COMMENT '权限id',
  PRIMARY KEY (role_id,permission_id)
) COMMENT='角色与权限关联表';
INSERT INTO role_permission(role_id, permission_id)
values
('1','1'), ('1','2'), ('1','3'), --系统管理员拥有所有权限
('2','2'), --骑手仅拥有骑手管理权限
('3','3'); --商家仅拥有商店管理权限

-- ----------------------------
-- Table structure for address_info
-- ----------------------------
DROP TABLE IF EXISTS address_info;
CREATE TABLE address_info
(
  id                    VARCHAR(255) NOT NULL PRIMARY KEY COMMENT '地址id',
  user_id               VARCHAR(255) NOT NULL COMMENT '用户id',
  contact_name          VARCHAR(255) NOT NULL COMMENT '联系人名字',
  contact_gender        VARCHAR(255) COMMENT '联系人性别（男，女，未知）',
  phone                 VARCHAR(255) COMMENT '联系人手机号',
  postal_code           VARCHAR(255) COMMENT '邮政编码',
  province              VARCHAR(255) COMMENT '省份/州',
  city                  VARCHAR(255) COMMENT '城市名',
  details               VARCHAR(255) COMMENT '详细地址: 门牌，路名',
  create_time           TIMESTAMP COMMENT '创建时间',
  update_time           TIMESTAMP COMMENT '更新时间',

  FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO address_info(id, user_id, contact_name, contact_gender, phone, postal_code, province, city, details)
values ('1','1', '迪迦', '男', '13820248232', '221116', '江苏省', '徐州市', '上海路101号，江苏师范大学');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS shop;
CREATE TABLE shop
(
  id                        VARCHAR(255) NOT NULL PRIMARY KEY COMMENT '商店id',
  user_id                   VARCHAR(255) NOT NULL COMMENT '用户id',
  name                      VARCHAR(255) NOT NULL COMMENT '店名',
  rating                    VARCHAR(255) COMMENT '评分',
  monthly_sale              VARCHAR(255) COMMENT '月售量',
  estimated_delivery_time   VARCHAR(255) COMMENT '预计配送时间',
  description               VARCHAR(255) COMMENT '描述',
  category                  VARCHAR(255) COMMENT '商店种类',
  photo                     VARCHAR(255) COMMENT '商店Logo',
  create_time               TIMESTAMP COMMENT '创建时间',
  update_time               TIMESTAMP COMMENT '更新时间',

  FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO shop(id, user_id, name, rating, monthly_sale, estimated_delivery_time, category)
values
('1','2', '蜜雪冰城', '4.3', '月售3087', '35分钟', '奶茶'),
('2','2', '现烤三明治专卖店', '3.7', '月售935', '20分钟', '面包');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS product;
CREATE TABLE product
(
  id                        VARCHAR(255) NOT NULL PRIMARY KEY COMMENT '商品id',
  shop_id                   VARCHAR(255) NOT NULL COMMENT '商店id',
  name                      VARCHAR(255) NOT NULL COMMENT '食物名字',
  price                     VARCHAR(255) COMMENT '价格',
  description               VARCHAR(255) COMMENT '食物描述',
  photo                     VARCHAR(255) COMMENT '食物照片',
  create_time               TIMESTAMP COMMENT '创建时间',
  update_time               TIMESTAMP COMMENT '更新时间',

  FOREIGN KEY (shop_id) REFERENCES shop (id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO product(id, shop_id, name, price, description)
values
('1','1', '芋泥奶茶', '9', '珍珠，芋泥，椰浆'),
('2','1', '布丁奶茶', '11.8', '布丁，红茶叶'),
('3','2', '全麦培根三明治', '17.8', '培根，鸡蛋，生菜'),
('4','2', '鸡蛋火腿三明治', '20.9', '火腿，鸡蛋，生菜');

SET FOREIGN_KEY_CHECKS = 1;