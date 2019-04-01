CREATE DATABASE  IF NOT EXISTS `balance` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `balance`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: balance
-- ------------------------------------------------------
-- Server version	5.5.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expense` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `note` text,
  `category` text,
  `date` date DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense`
--

LOCK TABLES `expense` WRITE;
/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
INSERT INTO `expense` VALUES (2,5,'rewerew','Telefon','2018-12-20',1),(3,14,'eew','Ev dekorları','2018-12-06',1),(5,90,'Ipoteka','Ev','2019-01-18',1),(12,20,'','Telefon','2019-03-25',1),(13,100,'','Maşın','2019-03-27',1),(15,50,'','Ev','2019-01-23',1),(16,70,'','Diger','2019-03-28',1),(20,40,'','Ev dekorları','2019-03-29',1),(21,40,'','Ev','2018-12-13',1),(22,20,'','Maşın','2018-12-11',1),(23,60,'','Yemək','2018-12-27',1),(24,30,'','Maşın','2019-01-08',1),(25,90,'','Yemək','2019-01-02',1),(26,25,'','Telefon','2019-01-17',1),(27,60,'','Ev','2019-02-27',1),(28,80,'','Maşın','2019-02-25',1),(29,100,'','Yemək','2019-02-24',1),(30,70,'','Diger','2019-02-20',1),(31,15,'','Telefon','2019-02-27',1),(32,50,'','Ev','2019-03-30',1),(33,60,'','Yemək','2019-03-30',1),(34,150,'Yeni xerc elave ','555','2019-04-01',1),(35,40,'','Ev','2019-04-02',1),(36,60,'','Maşın','2019-04-18',1),(37,20,'','Ev dekorları','2019-04-16',1),(38,40,'','Yemək','2019-04-13',1),(39,4,'','Telefon','2019-04-22',1),(40,20,'','Diger','2019-04-24',1);
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `help`
--

DROP TABLE IF EXISTS `help`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `help` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `texts` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `help`
--

LOCK TABLES `help` WRITE;
/*!40000 ALTER TABLE `help` DISABLE KEYS */;
/*!40000 ALTER TABLE `help` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income`
--

DROP TABLE IF EXISTS `income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `income` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,2) DEFAULT NULL,
  `note` text,
  `category` text,
  `date` date DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='bu cedvelde gelirlerin yadda saxlanilmasi ucundur';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income`
--

LOCK TABLES `income` WRITE;
/*!40000 ALTER TABLE `income` DISABLE KEYS */;
INSERT INTO `income` VALUES (41,1100.00,'','Maaş','2018-12-10',1),(42,550.00,'','Kirayə','2018-12-10',1),(43,100.00,'','Alınan borc','2018-12-19',1),(44,50.00,'','Depozit','2018-12-25',1),(45,800.00,'','Diger','2019-12-30',1),(46,1000.00,'','Maaş','2019-01-10',1),(47,850.00,'','Maaş','2019-02-10',1),(48,800.00,'','Maaş','2019-03-10',1),(49,550.00,'','Kirayə','2019-01-10',1),(50,550.00,'','Kirayə','2019-02-10',1),(51,550.00,'','Kirayə','2019-03-10',1),(52,50.00,'','Depozit','2019-01-25',1),(53,50.00,'','Depozit','2019-02-25',1),(54,50.00,'','Depozit','2019-03-25',1),(55,200.00,'','Alınan borc','2019-02-20',1),(56,150.00,'','Diger','2019-01-31',1),(57,50.00,'','Diger','2019-02-28',1),(58,60.00,'','Diger','2019-03-29',1),(60,120.00,'Yeni gelir qeydiyyati daxil edilir','555','2019-03-31',1);
/*!40000 ALTER TABLE `income` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `info`
--

DROP TABLE IF EXISTS `info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `cem` decimal(10,2) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `userId_UNIQUE` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='bu cevdelde melumatlar saxlamaq ucundur';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `info`
--

LOCK TABLES `info` WRITE;
/*!40000 ALTER TABLE `info` DISABLE KEYS */;
INSERT INTO `info` VALUES (1,'Serxan',6207.00,1),(3,'Novruz',1400.00,5),(4,'Alik',0.00,6);
/*!40000 ALTER TABLE `info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kateqoriya`
--

DROP TABLE IF EXISTS `kateqoriya`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kateqoriya` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kategory` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kateqoriya`
--

LOCK TABLES `kateqoriya` WRITE;
/*!40000 ALTER TABLE `kateqoriya` DISABLE KEYS */;
INSERT INTO `kateqoriya` VALUES (1,'Maaş','gəlir',1),(2,'Kirayə','gəlir',1),(3,'Alınan borc','gəlir',1),(4,'Depozit','gəlir',1),(5,'Ev','xərclər',1),(6,'Maşın','xərclər',1),(7,'Ev dekorları','xərclər',1),(9,'Yemək','xərclər',1),(10,'Telefon','xərclər',1),(21,'Diger','gəlir',1),(37,'Maas','gəlir',5),(38,'Diger','gəlir',5),(39,'Ev','xərclər',5),(40,'Diger','xərclər',5),(41,'Diger','xərclər',1),(42,'555','gəlir',1),(43,'555','xərclər',1);
/*!40000 ALTER TABLE `kateqoriya` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `totalAmount` double DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='bu cedvelde planlarin adi , ve tarixlerii olacaq, amma, planlarin , xerc bolusdurmeleri, burada ola bilmez, plandetails, cedveline yazmaq meqsede uygundur,';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (6,'Plan 1','2018-12-01','2019-01-01',590,1),(7,'Plan 2','2019-01-01','2019-02-01',460,1),(8,'Plan 3','2019-02-01','2019-03-01',410,1),(9,'Plan 4','2019-03-01','2019-04-01',400,1),(10,'Plan 5','2019-04-01','2019-04-30',390,1);
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plans`
--

DROP TABLE IF EXISTS `plans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `planId` int(11) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='bu cedvelde her plana uygun , olan planlasdirimis xerclerin siyahisi qeydiyyat olunacaq,';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plans`
--

LOCK TABLES `plans` WRITE;
/*!40000 ALTER TABLE `plans` DISABLE KEYS */;
INSERT INTO `plans` VALUES (79,6,'Diger',70,1),(80,6,'Ev',200,1),(81,6,'Ev dekorları',50,1),(82,6,'Maşın',150,1),(83,6,'Telefon',20,1),(84,6,'Yemək',100,1),(85,7,'Diger',70,1),(86,7,'Ev',100,1),(87,7,'Ev dekorları',50,1),(88,7,'Maşın',100,1),(89,7,'Telefon',20,1),(90,7,'Yemək',120,1),(91,8,'Diger',90,1),(92,8,'Ev',150,1),(93,8,'Ev dekorları',20,1),(94,8,'Maşın',50,1),(95,8,'Telefon',20,1),(96,8,'Yemək',80,1),(97,9,'Diger',70,1),(98,9,'Ev',120,1),(99,9,'Ev dekorları',10,1),(100,9,'Maşın',80,1),(101,9,'Telefon',30,1),(102,9,'Yemək',90,1),(103,10,'555',60,1),(104,10,'Diger',20,1),(105,10,'Ev',100,1),(106,10,'Ev dekorları',60,1),(107,10,'Maşın',70,1),(108,10,'Telefon',20,1),(109,10,'Yemək',60,1);
/*!40000 ALTER TABLE `plans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='bu cedvelde sisteme girmeli olan adamin istifadeci adi ve sifresi yadda saxlanacaq';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Serxan','Semedbeyli','serxan555','555ss777ss','ssmdbyli@gmail.com'),(5,'Novruz','Alexandro','alex','123','alex@gmail.com'),(6,'Alik','Alexandr','alik555','555ss777ss','alik555@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-01 11:05:48
