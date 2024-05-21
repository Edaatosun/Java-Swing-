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
-- Table structure for table `internationalflights`
--

DROP TABLE IF EXISTS `internationalflights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `internationalflights` (
  `flight_id` int NOT NULL,
  `point_of_departure` varchar(100) DEFAULT NULL,
  `point_of_destination` varchar(100) DEFAULT NULL,
  `departure_date` date DEFAULT NULL,
  `departure_time` time DEFAULT NULL,
  `destination_time` time DEFAULT NULL,
  PRIMARY KEY (`flight_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internationalflights`
--

LOCK TABLES `internationalflights` WRITE;
/*!40000 ALTER TABLE `internationalflights` DISABLE KEYS */;
INSERT INTO `internationalflights` VALUES (1,'Ankara','Fransa','2024-05-21','09:00:00','12:00:00'),(2,'Ankara','İngiltere','2024-05-22','10:00:00','13:00:00'),(3,'Ankara','İtalya','2024-05-23','11:00:00','14:00:00'),(4,'İstanbul','Fransa','2024-05-28','16:00:00','19:00:00'),(5,'İstanbul','İngiltere','2024-05-29','17:00:00','20:00:00'),(6,'İstanbul','İtalya','2024-05-30','18:00:00','21:00:00'),(7,'İzmir','Almanya','2024-06-03','22:00:00','01:00:00'),(8,'İzmir','Fransa','2024-06-04','23:00:00','02:00:00'),(9,'Almanya','İstanbul','2024-06-09','08:00:00','11:00:00'),(10,'Fransa','Ankara','2024-06-10','09:00:00','12:00:00'),(11,'İngiltere','İzmir','2024-06-11','10:00:00','13:00:00'),(12,'İtalya','Antalya','2024-06-12','11:00:00','14:00:00'),(13,'İspanya','Trabzon','2024-06-13','12:00:00','15:00:00'),(14,'Rusya','İstanbul','2024-06-14','13:00:00','16:00:00'),(15,'Çin','Ankara','2024-06-15','14:00:00','17:00:00'),(16,'Almanya','İzmir','2024-06-16','15:00:00','18:00:00'),(17,'Fransa','Antalya','2024-06-17','16:00:00','19:00:00'),(18,'İngiltere','Trabzon','2024-06-18','17:00:00','20:00:00');
/*!40000 ALTER TABLE `internationalflights` ENABLE KEYS */;
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
