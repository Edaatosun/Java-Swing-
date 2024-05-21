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
-- Table structure for table `nationalseats`
--

DROP TABLE IF EXISTS `nationalseats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nationalseats` (
  `seat_number` int NOT NULL,
  `passenger_no` int DEFAULT NULL,
  `is_occupied` tinyint(1) DEFAULT '0',
  `class` varchar(20) DEFAULT NULL,
  `flight_id` int NOT NULL,
  PRIMARY KEY (`seat_number`,`flight_id`),
  KEY `passenger_no` (`passenger_no`),
  KEY `flight_id` (`flight_id`),
  CONSTRAINT `nationalseats_ibfk_1` FOREIGN KEY (`passenger_no`) REFERENCES `nationalpassenger` (`passenger_no`),
  CONSTRAINT `nationalseats_ibfk_2` FOREIGN KEY (`flight_id`) REFERENCES `nationalpassenger` (`flight_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nationalseats`
--

LOCK TABLES `nationalseats` WRITE;
/*!40000 ALTER TABLE `nationalseats` DISABLE KEYS */;
INSERT INTO `nationalseats` VALUES (1000,107,1,'Economy',1),(1000,127,1,'Economy',5),(1000,121,1,'Economy',20),(1000,125,1,'Economy',21),(1001,106,1,'Economy',1),(1001,126,1,'Economy',5),(1002,113,1,'Economy',1),(1005,105,1,'Economy',1),(1006,112,1,'Economy',1),(1007,123,1,'Economy',1),(1010,117,1,'Economy',1),(1011,116,1,'Economy',1),(2000,107,1,'Business',11),(2000,127,1,'Economy',17),(2000,121,1,'Economy',22),(2001,106,1,'Business',11),(2001,126,1,'Economy',17),(2001,125,1,'Economy',22),(2002,105,1,'Business',11),(2005,113,1,'Economy',11),(2006,112,1,'Economy',11),(2007,123,1,'Economy',11),(2010,117,1,'Economy',11),(2011,116,1,'Economy',11);
/*!40000 ALTER TABLE `nationalseats` ENABLE KEYS */;
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
