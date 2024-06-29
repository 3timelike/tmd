/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.33 : Database - floowers
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `f_floowers` */

DROP TABLE IF EXISTS `f_floowers`;

CREATE TABLE `f_floowers` (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '鲜花的id',
  `f_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '鲜花名称',
  `f_color` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '颜色',
  `f_meaning` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '意义',
  `f_exist_time` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '花期',
  `f_product_area` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '产地',
  `f_aroma` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '香味',
  `f_petal_num` int NOT NULL DEFAULT '0' COMMENT '花瓣',
  `f_floower_hign` int NOT NULL DEFAULT '0' COMMENT '高度',
  `is_deleted` int DEFAULT '0' COMMENT '逻辑删除',
  `f_create_date` date DEFAULT NULL COMMENT '上传日期',
  `f_num` int NOT NULL DEFAULT '0' COMMENT '库存',
  `f_avatar` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

/*Data for the table `f_floowers` */

insert  into `f_floowers`(`f_id`,`f_name`,`f_color`,`f_meaning`,`f_exist_time`,`f_product_area`,`f_aroma`,`f_petal_num`,`f_floower_hign`,`is_deleted`,`f_create_date`,`f_num`,`f_avatar`) values (3,'玫瑰','3','3','3','3','3',3,3,0,'2024-05-23',0,NULL),(5,'牡丹','2','2','2','2','2',2,2,0,'2024-05-24',80,'5d6298eb6a9945b5aabf64289865a006.jpg'),(6,'菊花','a','a','a','a','a',0,0,0,'2024-06-17',0,'160c588e8fc149a4957bfe63179d4fa8.jpg'),(7,'荷花','2','2','2','2','2',0,0,0,'2024-06-24',1,NULL),(8,'莲花','4','4','4','4','4',4,4,0,'2024-06-28',4,'823c1f29aaa04c35aac7eea441a1d766.jpg'),(9,'狗尾巴草','5','5','5','5','5',5,5,0,'2024-06-28',5,'ee455029bac84c49ad32d064cc1086c3.jpg');

/*Table structure for table `flower_pile` */

DROP TABLE IF EXISTS `flower_pile`;

CREATE TABLE `flower_pile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `price` varchar(20) DEFAULT NULL,
  `amount` varchar(20) DEFAULT NULL,
  `avatar` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

/*Data for the table `flower_pile` */

insert  into `flower_pile`(`id`,`name`,`price`,`amount`,`avatar`) values (1,'家人推荐','50','17',NULL),(2,'爱人推荐','50','29',NULL),(3,'朋友推荐','100','45',NULL),(5,'店长推荐','50000','520','5d6298eb6a9945b5aabf64289865a006.jpg'),(7,'张顺一推荐','50','498','19976c7d70e34012b55a670c7b46903f.jpg');

/*Table structure for table `flower_pile_flowers` */

DROP TABLE IF EXISTS `flower_pile_flowers`;

CREATE TABLE `flower_pile_flowers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `flower_id` int DEFAULT NULL,
  `flower_pile_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb3;

/*Data for the table `flower_pile_flowers` */

insert  into `flower_pile_flowers`(`id`,`flower_id`,`flower_pile_id`) values (4,7,2),(5,3,2),(17,3,7),(18,5,7),(19,6,7),(20,7,7),(27,3,5),(28,5,5),(29,6,5),(30,5,3),(31,6,3),(32,7,3),(38,6,1),(39,7,1),(40,5,1);

/*Table structure for table `manager` */

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `password` char(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  `gender` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

/*Data for the table `manager` */

insert  into `manager`(`id`,`name`,`username`,`password`,`email`,`address`,`gender`) values (1,'chuan','123','123','123','123','男');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `from` int DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `send_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;

/*Data for the table `message` */

insert  into `message`(`id`,`from`,`content`,`send_date`) values (1,14,'1111','2024-06-29'),(2,14,'22222','2024-06-29'),(3,14,'3333','2024-06-29'),(9,14,'在SQL中插入当前时间可以使用数据库提供的内置函数。以下是一些常见数据库的当前时间函数和相应的插入语句示例：\n\n对于MySQL和MariaDB，使用NOW()函数：\n\nINSERT INTO your','2024-06-29'),(10,14,'诗句，读作：shī jù 就是组成诗词的句子。诗句通常按照诗文的格式体例，限定每句字数的多少。中国最早的诗句为律诗结构，格律要求严格，比如先秦时期的诗一般每句四言，见于《诗经》。后发展为五言或七言律诗','2024-06-29'),(11,14,'色情图片是指以引起性兴奋为目的、展示或描述人类身体或人类性行为的图片表现形式。色情图片是最常见的色情传播方式。色情图片可能引发性唤起，对未成年人具有潜在危险 [1]。作为互联网的主力军，很多未成年人正','2024-06-29'),(12,9,'店长，我想要张顺义的大菊花\n','2024-06-29');

/*Table structure for table `order_back` */

DROP TABLE IF EXISTS `order_back`;

CREATE TABLE `order_back` (
  `b_id` int NOT NULL AUTO_INCREMENT,
  `o_id` int NOT NULL,
  `f_id` int NOT NULL,
  `f_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `uid` int NOT NULL,
  `o_phone_num` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `phone_num` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `o_area` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `o_note` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `o_time` timestamp NULL DEFAULT NULL,
  `b_time` timestamp NULL DEFAULT NULL,
  `is_deleted` int DEFAULT '0',
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;

/*Data for the table `order_back` */

insert  into `order_back`(`b_id`,`o_id`,`f_id`,`f_name`,`uid`,`o_phone_num`,`phone_num`,`o_area`,`o_note`,`o_time`,`b_time`,`is_deleted`) values (1,8,5,'2',10,'18574325831','18574325831','啊啊啊','111111','2024-05-25 14:44:36','2024-05-25 14:53:44',0),(2,9,5,'2',10,'18574325831','18574325831','啊啊啊','zz','2024-05-25 15:03:56','2024-05-25 15:04:06',0),(3,11,5,'2',1,'12345678910','15845615879','123456','123456','2024-06-24 19:46:58','2024-06-24 19:47:14',0),(4,12,5,'2',1,'dasdad','15845615879','sdads','adsad','2024-06-24 19:53:09','2024-06-24 19:53:16',0),(12,17,5,'2',14,'bbb','15654598748','bbb','bbb','2024-06-27 21:17:53','2024-06-27 21:17:57',0),(13,18,5,'2',14,'vvvvv','15654598748','vvvvv','vvvvvvv','2024-06-27 21:20:53','2024-06-27 21:20:58',0),(14,19,5,'2',14,'vvvvv','15654598748','vvvvvv','vvvvv','2024-06-27 21:22:19','2024-06-27 21:22:22',0),(15,20,5,'2',14,'acac','15654598748','acac','acac','2024-06-27 21:23:43','2024-06-27 21:23:46',0),(16,21,5,'2',14,'vvvvvvvvv','15654598748','vvvvvvvvv','vvvvvvvvv','2024-06-27 21:24:21','2024-06-27 21:24:25',0),(17,16,5,'2',14,'dsd','15654598748','dwad','dwad2222222222222','2024-06-27 21:13:20','2024-06-27 21:39:57',0),(18,17,3,'朋友推荐',14,'mmmmmmmmm','15654598748','mmmmmmm','mmmmmmmm','2024-06-27 21:51:43','2024-06-29 12:12:59',0),(19,16,3,'朋友推荐',14,'dsd','15654598748','dwad','dwad','2024-06-27 21:09:26','2024-06-29 12:13:13',0),(20,18,7,'张顺一推荐',14,'123456','15654598748','123456','123456','2024-06-29 12:13:58','2024-06-29 12:14:02',0),(21,19,7,'张顺一推荐',14,'1512005','15654598748','12015','12015','2024-06-29 12:14:58','2024-06-29 12:15:05',0),(22,23,5,'2',14,'bbbb','15654598748','bbbb','bbbbbb','2024-06-27 21:42:08','2024-06-29 12:15:21',0),(23,22,5,'2',14,'ddddddddd','15654598748','dddddddd','dddddddddd','2024-06-27 21:39:52','2024-06-29 12:15:42',0),(24,20,3,'朋友推荐',14,'2121','15654598748','2121','2121','2024-06-29 12:16:05','2024-06-29 12:16:33',0),(25,25,5,'牡丹',14,'ds','15654598748','das','dad','2024-06-29 12:17:11','2024-06-29 12:17:16',0),(26,26,5,'牡丹',14,'424','15654598748','25','425','2024-06-29 12:17:52','2024-06-29 12:18:00',0);

/*Table structure for table `order_floowers` */

DROP TABLE IF EXISTS `order_floowers`;

CREATE TABLE `order_floowers` (
  `o_id` int NOT NULL AUTO_INCREMENT,
  `f_id` int NOT NULL,
  `uid` int NOT NULL,
  `o_phone_num` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone_num` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `o_area` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `o_note` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `o_time` timestamp NULL DEFAULT NULL,
  `is_deleted` int DEFAULT '0',
  `f_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`o_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;

/*Data for the table `order_floowers` */

insert  into `order_floowers`(`o_id`,`f_id`,`uid`,`o_phone_num`,`phone_num`,`o_area`,`o_note`,`o_time`,`is_deleted`,`f_name`) values (1,1,1,'1','1','1','1','2024-05-24 20:40:54',0,NULL),(2,3,10,'18574325831','18574325831','啊啊啊','aaaaaaaa','2024-05-24 20:49:52',0,NULL),(3,3,10,'131454646531','18574325831','啊啊啊','顶顶顶顶顶顶顶顶顶顶','2024-05-24 20:58:17',0,NULL),(4,3,10,'18574325831','18574325831','啊啊啊','顶顶顶顶顶顶顶顶顶顶顶','2024-05-24 20:58:56',0,NULL),(5,3,10,'18574325831','18574325831','啊啊啊','顶顶顶顶顶顶顶顶顶顶顶','2024-05-24 21:01:11',0,NULL),(6,5,10,'18574325831','18574325831','啊啊啊','22222222222222222','2024-05-24 21:25:35',0,NULL),(10,5,10,'18574325831','18574325831','啊啊啊','...','2024-05-25 20:23:57',0,'2'),(24,5,14,'12121','15654598748','2121','2121','2024-06-29 12:15:57',0,'牡丹');

/*Table structure for table `order_pile` */

DROP TABLE IF EXISTS `order_pile`;

CREATE TABLE `order_pile` (
  `o_id` int NOT NULL AUTO_INCREMENT,
  `f_id` int NOT NULL,
  `uid` int NOT NULL,
  `o_phone_num` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone_num` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `o_area` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `o_note` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `o_time` timestamp NULL DEFAULT NULL,
  `is_deleted` int DEFAULT '0',
  `f_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`o_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;

/*Data for the table `order_pile` */

/*Table structure for table `s_sign` */

DROP TABLE IF EXISTS `s_sign`;

CREATE TABLE `s_sign` (
  `sign_id` int NOT NULL AUTO_INCREMENT,
  `sign_username` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sign_date` date NOT NULL,
  `sign_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sign_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`sign_id`,`sign_date`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;

/*Data for the table `s_sign` */

insert  into `s_sign`(`sign_id`,`sign_username`,`sign_date`,`sign_time`,`sign_name`) values (1,'zsyyy','2024-05-21','2024-05-21 13:44:05',''),(2,'zsyyyuuu','2024-05-21','2024-05-21 13:47:48',''),(3,'zsyyyuuu','2024-05-21','2024-05-21 13:47:49',''),(4,'zsyyyuuu','2024-05-21','2024-05-21 13:47:50',''),(5,'zsyyyuuu','2024-05-21','2024-05-21 13:47:51',''),(6,'zsyyyuuu','2024-05-21','2024-05-21 13:47:51',''),(7,'zsyyyuuu','2024-05-21','2024-05-21 13:47:52',''),(8,'zsyyy','2024-05-21','2024-05-21 14:41:46',''),(9,'zsyyy','2024-05-21','2024-05-21 14:50:26',''),(10,'zsyyy','2024-05-21','2024-05-21 14:50:30',''),(11,'zsyyy','2024-05-21','2024-05-21 14:50:58',''),(12,'zsyyy','2024-05-21','2024-05-21 18:12:35',''),(13,'zsyyy','2024-05-21','2024-05-21 18:13:34',''),(14,'zsyyy','2024-05-22','2024-05-22 18:21:31',''),(15,'a','2024-05-22','2024-05-22 19:45:14',''),(16,'d','2024-05-22','2024-05-22 19:47:22',''),(17,'aaa','2024-05-22','2024-05-22 19:52:57',''),(18,'bbb','2024-05-22','2024-05-22 19:56:48',''),(19,'ccc','2024-05-22','2024-05-22 19:57:32','ccc'),(24,'1234','2024-06-22','2024-06-22 19:05:23','可乐乐'),(25,'1234','2024-06-24','2024-06-24 19:47:56','可乐乐');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '姓名',
  `gender` enum('男','女') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '男' COMMENT '性别',
  `age` int NOT NULL COMMENT '年龄',
  `phone_num` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '头像',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `identify` int NOT NULL DEFAULT '0' COMMENT '身份',
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  PRIMARY KEY (`username`,`uid`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

/*Data for the table `user` */

insert  into `user`(`username`,`password`,`name`,`gender`,`age`,`phone_num`,`email`,`avatar`,`is_deleted`,`identify`,`uid`) values ('1212','121212','在这种','男',15,'15654598748','12151551@12132.com','ebeaf8a596d94603b4a87a6096b307ff.jpg',0,0,14),('1234','123456','可乐乐','男',22,'15845615879','2544646464@qq.com','',0,0,1),('a','1','d','男',1,'a','a',NULL,0,0,2),('aaa','2','aaa','男',1,'a','a',NULL,0,0,3),('aaaaa','123456','啊啊啊','男',22,'15645615897','1454564131@qq.com','',0,0,4),('aaaaaa','aaaaaa','啊啊啊啊','男',22,'13131231233','132131311@qq.com','',0,0,5),('adad','23','a','男',1,'34','3232321',NULL,0,0,6),('bbb','2','bbb','男',1,'1','1',NULL,0,0,7),('ccc','2','ccc','男',2,'c','c',NULL,0,0,8),('hmjjjjjjj','123456','呼呼呼','女',33,'18554644563','2424467624@qq.com','02fbb1067e6a440aa7f16c14cf060e0e.jpg',0,0,13),('zsy','123','adada','男',11,'adadad','adadad',NULL,0,1,9),('zsyyy','123456789','张顺一','男',20,'18574325831','2424467624@qq.com','be68d91412524691b9502b4178adf273.jpg',0,0,10),('zsyyyyyy','20221504','哈喽','男',22,NULL,'2424467624@qq.com',NULL,0,0,11),('zyyyyyy','20221504','可乐','男',2,'18574325831','2424467624@qq.com',NULL,0,0,12);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
