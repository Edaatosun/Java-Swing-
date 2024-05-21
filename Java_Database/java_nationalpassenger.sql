-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: java
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `nationalpassenger`
--

DROP TABLE IF EXISTS `nationalpassenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nationalpassenger` (
  `passenger_no` int NOT NULL AUTO_INCREMENT,
  `passenger_type` varchar(255) DEFAULT NULL,
  `passenger_price` int DEFAULT NULL,
  `flight_id` int DEFAULT NULL,
  PRIMARY KEY (`passenger_no`),
  KEY `flight_id` (`flight_id`),
  CONSTRAINT `nationalpassenger_ibfk_1` FOREIGN KEY (`flight_id`) REFERENCES `nationalflights` (`flight_id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nationalpassenger`
--

LOCK TABLES `nationalpassenger` WRITE;
/*!40000 ALTER TABLE `nationalpassenger` DISABLE KEYS */;
INSERT INTO `nationalpassenger` VALUES (1,'adult',2000,1),(2,'child',750,1),(3,'baby',500,1),(4,'adult',2000,1),(5,'child',750,1),(6,'baby',500,1),(7,'adult',2000,1),(8,'child',750,1),(9,'baby',500,1),(10,'adult',2000,1),(11,'child',750,1),(12,'baby',500,1),(13,'adult',2000,1),(14,'child',750,1),(15,'adult',2000,1),(16,'child',750,1),(17,'baby',500,1),(18,'adult',2000,1),(19,'child',750,1),(20,'baby',500,1),(21,'adult',2000,1),(22,'adult',2000,1),(23,'adult',2000,1),(24,'adult',2000,1),(25,'adult',2000,1),(26,'child',750,1),(27,'baby',500,1),(28,'adult',2000,1),(29,'child',750,1),(30,'adult',2000,1),(31,'child',750,1),(32,'adult',2000,1),(33,'child',750,1),(34,'adult',2000,1),(35,'child',750,1),(36,'adult',2000,1),(37,'child',750,1),(38,'student',1000,1),(39,'student',1000,1),(40,'student',1000,1),(41,'student',1000,1),(42,'student',1000,1),(43,'student',1000,1),(44,'student',1000,1),(45,'student',1000,1),(46,'student',1000,1),(47,'student',1000,1),(48,'student',1000,1),(49,'student',1000,1),(50,'adult',2000,1),(51,'child',750,1),(52,'student',1000,1),(53,'adult',2000,1),(54,'child',750,1),(55,'adult',2000,1),(56,'child',750,1),(57,'student',1000,1),(58,'student',1000,1),(59,'student',1000,1),(60,'student',1000,1),(61,'student',1000,1),(62,'student',1000,1),(63,'student',1000,1),(64,'student',1000,1),(65,'student',1000,1),(66,'student',1000,1),(67,'student',1000,1),(68,'student',1000,1),(69,'student',1000,1),(70,'student',1000,1),(71,'student',1000,1),(72,'student',1000,1),(73,'student',1000,1),(74,'student',1000,1),(75,'student',1000,1),(76,'student',1000,1),(77,'student',1000,1),(78,'student',1000,1),(79,'student',1000,1),(80,'adult',2000,1),(81,'adult',2000,1),(82,'adult',2000,1),(83,'adult',2000,1),(84,'adult',2000,1),(85,'adult',2000,1),(86,'adult',2000,1),(87,'adult',2000,2),(88,'adult',2000,1),(89,'adult',2000,2),(90,'adult',2000,1),(91,'adult',2000,2),(92,'adult',2000,1),(93,'adult',2000,2),(94,'adult',2000,1),(95,'adult',2000,2),(96,'adult',2000,1),(97,'adult',2000,2),(98,'adult',2000,1),(99,'adult',2000,2),(100,'adult',2000,1),(101,'adult',2000,2),(102,'adult',2000,1),(103,'adult',2000,2),(104,'child',750,1),(105,'child',750,2),(106,'baby',500,1),(107,'baby',500,2),(108,'adult',2000,1),(109,'adult',2000,11),(110,'adult',2000,1),(111,'adult',2000,11),(112,'adult',2000,1),(113,'adult',2000,11),(114,'adult',2000,1),(115,'adult',2000,11),(116,'adult',2000,1),(117,'adult',2000,11),(118,'adult',2000,1),(119,'adult',2000,11),(120,'adult',2000,1),(121,'adult',2000,11),(122,'adult',2000,20),(123,'adult',2000,22),(124,'adult',2000,1),(125,'adult',2000,11),(126,'adult',2000,21),(127,'adult',2000,22),(128,'adult',2000,5),(129,'adult',2000,17),(130,'adult',2000,5),(131,'adult',2000,17);
/*!40000 ALTER TABLE `nationalpassenger` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-19 18:00:37
