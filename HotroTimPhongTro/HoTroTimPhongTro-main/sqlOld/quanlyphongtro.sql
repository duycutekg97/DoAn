-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlyphongtro
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Hồ Chí Minh'),(2,'Hồ Nội'),(3,'Không');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `district` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` VALUES (1,'Tân Phú'),(2,'Bình Tân'),(3,'Không');
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `motel`
--

DROP TABLE IF EXISTS `motel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `motel` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  `map` longtext NOT NULL,
  `phone` varchar(50) NOT NULL,
  `show` tinyint DEFAULT '0',
  `numberofresidents` int DEFAULT '0',
  `price` double DEFAULT '0',
  `fkmotel_cityId` int DEFAULT NULL,
  `fkmotel_districtId` int DEFAULT NULL,
  `fkmotel_wardId` int DEFAULT NULL,
  `fkmotel_userId` int DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `description` longtext,
  `acreage` varchar(10) DEFAULT NULL,
  `createdDate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fkmotel_cityId_idx` (`fkmotel_cityId`),
  KEY `fkmotel_districtId_idx` (`fkmotel_districtId`),
  KEY `fkmotel_wardId_idx` (`fkmotel_wardId`),
  KEY `fkmotel_userId_idx` (`fkmotel_userId`),
  CONSTRAINT `fkmotel_cityId` FOREIGN KEY (`fkmotel_cityId`) REFERENCES `city` (`id`),
  CONSTRAINT `fkmotel_districtId` FOREIGN KEY (`fkmotel_districtId`) REFERENCES `district` (`id`),
  CONSTRAINT `fkmotel_userId` FOREIGN KEY (`fkmotel_userId`) REFERENCES `user` (`id`),
  CONSTRAINT `fkmotel_wardId` FOREIGN KEY (`fkmotel_wardId`) REFERENCES `ward` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `motel`
--

LOCK TABLES `motel` WRITE;
/*!40000 ALTER TABLE `motel` DISABLE KEYS */;
INSERT INTO `motel` VALUES (1,'Nhà trọ 1','638 Lê Trọng Tấn','<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10925.886842476992!2d106.61807016170827!3d10.805856462028508!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752be4e07610ff%3A0x7e9f16c6f9f42dff!2zU8OibiBCw7NuZyDEkMOhIENlbGFkb24gV2VTcG9ydA!5e0!3m2!1svi!2s!4v1694504787583!5m2!1svi!2s\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>','344142375',0,10,350000,1,1,1,2,'Cho thuê nhà riêng một trệt 2 lầu làm văn phòng, đào tạo, hộ gia đình thẩm mỹ, hiệu thuốc','Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...','120','2023-09-13'),(2,'Nhà trọ 2','638 Lê Lợi','<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10925.886842476992!2d106.61807016170827!3d10.805856462028508!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752be4e07610ff%3A0x7e9f16c6f9f42dff!2zU8OibiBCw7NuZyDEkMOhIENlbGFkb24gV2VTcG9ydA!5e0!3m2!1svi!2s!4v1694504787583!5m2!1svi!2s\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>','344142375',0,10,350000,1,1,1,2,'Cho thuê nhà riêng một trệt 2 lầu làm văn phòng, đào tạo, hộ gia đình thẩm mỹ, hiệu thuốc','Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...','120','2023-09-13'),(3,'Nhà trọ 3','638 Lê Trọng Tấn','<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10925.886842476992!2d106.61807016170827!3d10.805856462028508!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752be4e07610ff%3A0x7e9f16c6f9f42dff!2zU8OibiBCw7NuZyDEkMOhIENlbGFkb24gV2VTcG9ydA!5e0!3m2!1svi!2s!4v1694504787583!5m2!1svi!2s\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>','344142375',0,10,350000,2,1,1,3,'Cho thuê nhà riêng một trệt 2 lầu làm văn phòng, đào tạo, hộ gia đình thẩm mỹ, hiệu thuốc','Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...','120','2023-09-13'),(4,'Nhà trọ 4','638 Lê Trọng Tấn','<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10925.886842476992!2d106.61807016170827!3d10.805856462028508!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752be4e07610ff%3A0x7e9f16c6f9f42dff!2zU8OibiBCw7NuZyDEkMOhIENlbGFkb24gV2VTcG9ydA!5e0!3m2!1svi!2s!4v1694504787583!5m2!1svi!2s\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>','344142375',0,10,350000,1,1,1,3,'Cho thuê nhà riêng một trệt 2 lầu làm văn phòng, đào tạo, hộ gia đình thẩm mỹ, hiệu thuốc','Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...','120','2023-09-13'),(5,'Nhà trọ 5','638 Lê Trọng Tấn','<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10925.886842476992!2d106.61807016170827!3d10.805856462028508!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752be4e07610ff%3A0x7e9f16c6f9f42dff!2zU8OibiBCw7NuZyDEkMOhIENlbGFkb24gV2VTcG9ydA!5e0!3m2!1svi!2s!4v1694504787583!5m2!1svi!2s\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>','344142375',0,10,350000,1,1,1,3,'Cho thuê nhà riêng một trệt 2 lầu làm văn phòng, đào tạo, hộ gia đình thẩm mỹ, hiệu thuốc','Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...','120','2023-09-13'),(6,'Nhà trọ 6','638 Lê Trọng Tấn','<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10925.886842476992!2d106.61807016170827!3d10.805856462028508!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752be4e07610ff%3A0x7e9f16c6f9f42dff!2zU8OibiBCw7NuZyDEkMOhIENlbGFkb24gV2VTcG9ydA!5e0!3m2!1svi!2s!4v1694504787583!5m2!1svi!2s\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>','344142375',0,10,350000,2,2,1,3,'Cho thuê nhà riêng một trệt 2 lầu làm văn phòng, đào tạo, hộ gia đình thẩm mỹ, hiệu thuốc','Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...','120','2023-09-13'),(7,'Nhà trọ 7','638 Lê Lai','<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10925.886842476992!2d106.61807016170827!3d10.805856462028508!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752be4e07610ff%3A0x7e9f16c6f9f42dff!2zU8OibiBCw7NuZyDEkMOhIENlbGFkb24gV2VTcG9ydA!5e0!3m2!1svi!2s!4v1694504787583!5m2!1svi!2s\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>','344142375',0,10,350000,1,1,4,3,'Cho thuê nhà riêng một trệt 2 lầu làm văn phòng, đào tạo, hộ gia đình thẩm mỹ, hiệu thuốc','Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...','120','2023-09-13'),(8,'Nhà trọ 8','638 Lê Trọng Tấn','<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10925.886842476992!2d106.61807016170827!3d10.805856462028508!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752be4e07610ff%3A0x7e9f16c6f9f42dff!2zU8OibiBCw7NuZyDEkMOhIENlbGFkb24gV2VTcG9ydA!5e0!3m2!1svi!2s!4v1694504787583!5m2!1svi!2s\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>','344142375',0,10,350000,1,1,1,3,'Cho thuê nhà riêng một trệt 2 lầu làm văn phòng, đào tạo, hộ gia đình thẩm mỹ, hiệu thuốc','Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...','120','2023-09-13'),(9,'Nhà trọ 9','638 Lê Trọng Tấn','<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10925.886842476992!2d106.61807016170827!3d10.805856462028508!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752be4e07610ff%3A0x7e9f16c6f9f42dff!2zU8OibiBCw7NuZyDEkMOhIENlbGFkb24gV2VTcG9ydA!5e0!3m2!1svi!2s!4v1694504787583!5m2!1svi!2s\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>','344142375',0,10,350000,1,1,1,3,'Cho thuê nhà riêng một trệt 2 lầu làm văn phòng, đào tạo, hộ gia đình thẩm mỹ, hiệu thuốc','Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...Cho thuê lâu dài 3 - 5 năm nhà riêng, thoáng mát, đường lớn, tiện lợi đỗ xe. Phù hợp văn phòng, đào tạo, thẩm mỹ, phòng khám, hiệu thuốc, siêu thị...','120','2023-09-13');
/*!40000 ALTER TABLE `motel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `motelimage`
--

DROP TABLE IF EXISTS `motelimage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `motelimage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `fkmotelimage_motelId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fkmotelimage_motelId_idx` (`fkmotelimage_motelId`),
  CONSTRAINT `fkmotelimage_motelId` FOREIGN KEY (`fkmotelimage_motelId`) REFERENCES `motel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `motelimage`
--

LOCK TABLES `motelimage` WRITE;
/*!40000 ALTER TABLE `motelimage` DISABLE KEYS */;
INSERT INTO `motelimage` VALUES (1,'https://res.cloudinary.com/dicxcmntw/image/upload/v1691652152/s1uatwrmv06vn9n8ep4w.jpg',1),(2,'https://res.cloudinary.com/dicxcmntw/image/upload/v1690868836/waeuvtqdcsjrcuioak9n.jpg',1),(3,'https://res.cloudinary.com/dicxcmntw/image/upload/v1692688307/qsoqidt7nix3lsocxerc.jpg',1),(4,'https://res.cloudinary.com/dicxcmntw/image/upload/v1693034295/flj1dxlsuhlcnzbumdel.jpg',1),(5,'https://res.cloudinary.com/dicxcmntw/image/upload/v1693034295/flj1dxlsuhlcnzbumdel.jpg',2),(6,'https://res.cloudinary.com/dicxcmntw/image/upload/v1693034295/flj1dxlsuhlcnzbumdel.jpg',2),(7,'https://res.cloudinary.com/dicxcmntw/image/upload/v1693034295/flj1dxlsuhlcnzbumdel.jpg',2),(8,'https://res.cloudinary.com/dicxcmntw/image/upload/v1693034295/flj1dxlsuhlcnzbumdel.jpg',2),(9,'https://res.cloudinary.com/dicxcmntw/image/upload/v1693034295/flj1dxlsuhlcnzbumdel.jpg',3),(10,'https://res.cloudinary.com/dicxcmntw/image/upload/v1693034295/flj1dxlsuhlcnzbumdel.jpg',3),(11,'https://res.cloudinary.com/dicxcmntw/image/upload/v1693034295/flj1dxlsuhlcnzbumdel.jpg',3),(12,'https://res.cloudinary.com/dicxcmntw/image/upload/v1693034295/flj1dxlsuhlcnzbumdel.jpg',3),(13,'https://res.cloudinary.com/dicxcmntw/image/upload/v1690868836/waeuvtqdcsjrcuioak9n.jpg',4),(14,'https://res.cloudinary.com/dicxcmntw/image/upload/v1690868836/waeuvtqdcsjrcuioak9n.jpg',5),(15,'https://res.cloudinary.com/dicxcmntw/image/upload/v1690868836/waeuvtqdcsjrcuioak9n.jpg',6),(16,'https://res.cloudinary.com/dicxcmntw/image/upload/v1690868836/waeuvtqdcsjrcuioak9n.jpg',7),(17,'https://res.cloudinary.com/dicxcmntw/image/upload/v1690868836/waeuvtqdcsjrcuioak9n.jpg',8),(18,'https://res.cloudinary.com/dicxcmntw/image/upload/v1690868836/waeuvtqdcsjrcuioak9n.jpg',9),(19,'https://res.cloudinary.com/dicxcmntw/image/upload/v1690868836/waeuvtqdcsjrcuioak9n.jpg',9),(20,'https://res.cloudinary.com/dicxcmntw/image/upload/v1690868836/waeuvtqdcsjrcuioak9n.jpg',9),(21,'https://res.cloudinary.com/dicxcmntw/image/upload/v1690868836/waeuvtqdcsjrcuioak9n.jpg',9),(22,'https://res.cloudinary.com/dicxcmntw/image/upload/v1690868836/waeuvtqdcsjrcuioak9n.jpg',9),(23,'https://res.cloudinary.com/dicxcmntw/image/upload/v1690868836/waeuvtqdcsjrcuioak9n.jpg',9),(24,'https://res.cloudinary.com/dicxcmntw/image/upload/v1690868836/waeuvtqdcsjrcuioak9n.jpg',9),(25,'https://res.cloudinary.com/dicxcmntw/image/upload/v1691652152/s1uatwrmv06vn9n8ep4w.jpg',7),(26,'https://res.cloudinary.com/dicxcmntw/image/upload/v1691652152/s1uatwrmv06vn9n8ep4w.jpg',8);
/*!40000 ALTER TABLE `motelimage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roleuser`
--

DROP TABLE IF EXISTS `roleuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roleuser` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roleuser`
--

LOCK TABLES `roleuser` WRITE;
/*!40000 ALTER TABLE `roleuser` DISABLE KEYS */;
INSERT INTO `roleuser` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_HOST'),(3,'ROLE_RENTER');
/*!40000 ALTER TABLE `roleuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `fkuser_roleuserId` int DEFAULT NULL,
  `createdDate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fkuser_roleuserId_idx` (`fkuser_roleuserId`),
  CONSTRAINT `fkuser_roleuserId` FOREIGN KEY (`fkuser_roleuserId`) REFERENCES `roleuser` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'momo123456','$2a$10$q1E1xYZB8r1diieBtcIxtOfeyYUrY4ItbzMFaeJZCwEz3K.hNe0MK','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1690632388/ixbn7ivt5vyrxq8d4zzx.jpg','Huynh','Thien',1,NULL),(2,'thien2','$2a$10$q1E1xYZB8r1diieBtcIxtOfeyYUrY4ItbzMFaeJZCwEz3K.hNe0MK','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1690632388/ixbn7ivt5vyrxq8d4zzx.jpg','Thien','Huynh',2,NULL),(3,'momo1234562','$2a$10$nhtx6m3Cm2k7Zvo.QKTu8.ZWGQc6DiJDZuzpG5QrI0YWRI5Y2gI0C','nino02022002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694344205/m7x2saq2z1jgicnnuzaw.jpg','thien3','thien3',2,NULL),(7,'thien5','$2a$10$YCu1dE6dgbpuvh34uvz7yuYUaUyT2dmd5N0QOwCzwaOC19FSiu1VK','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694408624/kzbzpruuzk0iqrhgg2jn.jpg','thien5','thien5',3,NULL),(8,'thientest1','$2a$10$lPRon0551HKrKJXirXODTu53hSLEjIbpzPWOY9nC.lyMurks8daTO','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694501627/loy3nrdnb5kertthvlwd.jpg','thientest','thientest',3,NULL),(9,'thientest2','$2a$10$32doulNHl1z3ZnAubQC.LeZ/WMMAsuc/35Qvn/eiejdcw8PfBXUKG','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694501962/ke8sx1lmg7xixyp4h95i.jpg','thientest2','thientest2',3,'2023-09-12'),(10,'thientest3','$2a$10$9JpFlhI5JwlliAl9hKQFqeC3Jy0c0UQ4o/6jghGslQw/CASpNIJoq','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694503468/o1wivj8pxfqymdfwwguz.jpg','thientest3.1','thientest3.1',3,'2023-09-12');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ward`
--

DROP TABLE IF EXISTS `ward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ward` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward`
--

LOCK TABLES `ward` WRITE;
/*!40000 ALTER TABLE `ward` DISABLE KEYS */;
INSERT INTO `ward` VALUES (1,'Bình Hưng Hòa '),(2,'Bình Hưng Hòa A'),(3,'Bình Hưng Hòa B'),(4,'Bình Hưng Hòa C'),(5,'Không');
/*!40000 ALTER TABLE `ward` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-13 17:34:48
