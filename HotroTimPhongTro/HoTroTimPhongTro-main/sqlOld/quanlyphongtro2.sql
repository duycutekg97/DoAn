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
  `show` bit(1) DEFAULT b'0',
  `numberofresidents` int NOT NULL,
  `price` varchar(50) NOT NULL,
  `fkmotel_cityId` int DEFAULT NULL,
  `fkmotel_districtId` int DEFAULT NULL,
  `fkmotel_wardId` int DEFAULT NULL,
  `fkmotel_userId` int DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `description` longtext NOT NULL,
  `acreage` varchar(10) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `motel`
--

LOCK TABLES `motel` WRITE;
/*!40000 ALTER TABLE `motel` DISABLE KEYS */;
INSERT INTO `motel` VALUES (22,'Nhà','LTT','<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15676.350294185033!2d106.61734189999999!3d10.8046046!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752b9535b60699%3A0x4737f3be8bd41d5b!2zw4ZPTiBNQUxMIFTDom4gUGjDug!5e0!3m2!1svi!2s!4v1694690714858!5m2!1svi!2s\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>','0344142375',_binary '\0',5,'350000',2,2,4,1,'Tiêu đề 1','Des 1','30','2023-09-14');
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `motelimage`
--

LOCK TABLES `motelimage` WRITE;
/*!40000 ALTER TABLE `motelimage` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'momo123456','$2a$10$q1E1xYZB8r1diieBtcIxtOfeyYUrY4ItbzMFaeJZCwEz3K.hNe0MK','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1690632388/ixbn7ivt5vyrxq8d4zzx.jpg','Huynh','Thien',1,NULL),(2,'thien2','$2a$10$q1E1xYZB8r1diieBtcIxtOfeyYUrY4ItbzMFaeJZCwEz3K.hNe0MK','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1690632388/ixbn7ivt5vyrxq8d4zzx.jpg','Thien','Huynh',2,NULL),(3,'momo1234562','$2a$10$nhtx6m3Cm2k7Zvo.QKTu8.ZWGQc6DiJDZuzpG5QrI0YWRI5Y2gI0C','nino02022002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694344205/m7x2saq2z1jgicnnuzaw.jpg','thien3','thien3',2,NULL),(7,'thien5','$2a$10$YCu1dE6dgbpuvh34uvz7yuYUaUyT2dmd5N0QOwCzwaOC19FSiu1VK','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694408624/kzbzpruuzk0iqrhgg2jn.jpg','thien5','thien5',3,NULL),(8,'thientest1','$2a$10$lPRon0551HKrKJXirXODTu53hSLEjIbpzPWOY9nC.lyMurks8daTO','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694501627/loy3nrdnb5kertthvlwd.jpg','thientest','thientest',3,NULL),(9,'thientest2','$2a$10$32doulNHl1z3ZnAubQC.LeZ/WMMAsuc/35Qvn/eiejdcw8PfBXUKG','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694501962/ke8sx1lmg7xixyp4h95i.jpg','thientest2','thientest2',3,'2023-09-12'),(10,'thientest3','$2a$10$9JpFlhI5JwlliAl9hKQFqeC3Jy0c0UQ4o/6jghGslQw/CASpNIJoq','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694503468/o1wivj8pxfqymdfwwguz.jpg','thientest3.1','thientest3.1',3,'2023-09-12'),(11,'thien6','$2a$10$L0ToqZz9f87QFmnufYexNeVKHkIttILQthngxXsTST5QI45C2oUNW','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694674841/mzdwhqxg3bqjuzwyn9sk.jpg','thien6','thien6',3,'2023-09-14'),(12,'thien7','$2a$10$ejnCfu0oc/4rFk.j/e0Iqubh.n1mvoK3/Mo05pylr2hD7mSKD1wkK','nino07052002@gmail.com','','thien7','thien7',3,'2023-09-14'),(13,'thien8','$2a$10$0dplz8QDgMShX5EpRdA2m.MwqsFb1U0OxycOH0VxJQ9N2TSRDICZi','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694682569/qin7umnw3yphsk2vuwax.jpg','thien8','thien8',3,'2023-09-14'),(14,'thien9','$2a$10$VQOgsi2yWjtzHS9HUphJWOstX5ENzIVAYYZl1llD3YWQetGadiSni','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694683377/se0rsnqxvjxjyssvs93c.jpg','thien9','thien8',3,'2023-09-14'),(15,'thien10','$2a$10$OodUpTHot/XVSUU4FJuxAO56ON2viUxYOKfQgkUYuNODRzrG3bLM.','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694683471/uozjuhounjluychag6ij.jpg','thien10','thien10',3,'2023-09-14'),(16,'thien888','$2a$10$0dplz8QDgMShX5EpRdA2m.MwqsFb1U0OxycOH0VxJQ9N2TSRDICZi','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694682569/qin7umnw3yphsk2vuwax.jpg','thien8','thien8',3,'2023-09-14'),(17,'thien11','$2a$10$k2zQ14MxQg8D95NAlZ5T7OP1.4tREGgg5nuerqM5w4YrPPxs/.Yei','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694685014/t7qiczluu5i5wof45b3d.jpg','aaaaaaa','thien11',3,'2023-09-14'),(20,'thien19','$2a$10$sLA0N0JhB97TW5ue3EmPOOYUFeJBmThDpZpBY32T4l6GVnxwbhMfK','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694687710/m0bychjlo1uqha7lgxwn.jpg','thien19','thien19',3,'2023-09-14'),(21,'a100','$2a$10$s5OKsqCLVAiqZFLe.CYhiOmEjC6npYEEzXBLVXolXYvkPGftke6Cy','nino07052002@gmail.com','https://res.cloudinary.com/dicxcmntw/image/upload/v1694703260/ambou3wh5ykeaoxhb3oj.jpg','a100','a100',3,'2023-09-14');
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

-- Dump completed on 2023-09-14 22:06:17
