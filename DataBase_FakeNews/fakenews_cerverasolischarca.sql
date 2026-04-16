-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: fakenews_cerverasolischarca
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `idCategorias` int NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`idCategorias`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Sátira o Parodia'),(2,'Conexion falsa'),(3,'Contenido Engañoso'),(4,'Contexto Falso'),(5,'Contenido Impostor'),(6,'Contenido Manipulado'),(7,'Contenido Inventado');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diario`
--

DROP TABLE IF EXISTS `diario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diario` (
  `iddiario` int NOT NULL AUTO_INCREMENT,
  `diario` varchar(45) NOT NULL,
  PRIMARY KEY (`iddiario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diario`
--

LOCK TABLES `diario` WRITE;
/*!40000 ALTER TABLE `diario` DISABLE KEYS */;
INSERT INTO `diario` VALUES (1,'Clarín'),(2,'La Nación'),(3,'Infobae');
/*!40000 ALTER TABLE `diario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fakenews`
--

DROP TABLE IF EXISTS `fakenews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fakenews` (
  `titulo` varchar(50) NOT NULL,
  `descripcion` tinytext NOT NULL,
  `creador` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  `medios` int NOT NULL,
  `categoria` int NOT NULL,
  `id_fakenews` int NOT NULL AUTO_INCREMENT,
  `id_refutacion` int NOT NULL,
  `legajo` int NOT NULL,
  PRIMARY KEY (`id_fakenews`),
  UNIQUE KEY `un_legajo` (`legajo`),
  KEY `fk_id_refutacion_idx` (`id_refutacion`),
  KEY `medios_idx` (`medios`),
  KEY `categoria_idx` (`categoria`),
  CONSTRAINT `categoria` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`idCategorias`),
  CONSTRAINT `fk_medios` FOREIGN KEY (`medios`) REFERENCES `tipomedios` (`idtipomedios`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fakenews`
--

LOCK TABLES `fakenews` WRITE;
/*!40000 ALTER TABLE `fakenews` DISABLE KEYS */;
INSERT INTO `fakenews` VALUES ('Mi Mami','Mi Mami','Mich','1982-09-13',1,4,41,38,324),('Margarita te amo','Margarita te amo y te la re pongo','Mordecai','2002-10-12',1,4,42,40,342),('agre','agrrae','errg','1987-09-09',1,1,43,0,51445);
/*!40000 ALTER TABLE `fakenews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medios`
--

DROP TABLE IF EXISTS `medios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medios` (
  `usuario` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  `retractador` varchar(45) NOT NULL,
  `id_medios` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `id_RedSocial` int NOT NULL,
  `idTA` int NOT NULL,
  `idTE` int NOT NULL,
  `id_fakenews` int NOT NULL,
  `medios` int NOT NULL,
  PRIMARY KEY (`id_medios`),
  KEY `fk_idTA_idx` (`idTA`),
  KEY `fk_idRedSocial_idx` (`id_RedSocial`),
  KEY `fk_idTE_idx` (`idTE`),
  KEY `fk_idfakenews_idx` (`id_fakenews`),
  KEY `medios_idx` (`medios`),
  CONSTRAINT `fk_idfakenews` FOREIGN KEY (`id_fakenews`) REFERENCES `fakenews` (`id_fakenews`),
  CONSTRAINT `fk_idRedSocial` FOREIGN KEY (`id_RedSocial`) REFERENCES `redsocial` (`idRedSocial`),
  CONSTRAINT `fk_idTA` FOREIGN KEY (`idTA`) REFERENCES `traduccionaudiovisual` (`idTA`),
  CONSTRAINT `fk_idTE` FOREIGN KEY (`idTE`) REFERENCES `traduccionesescritas` (`idTE`),
  CONSTRAINT `medios` FOREIGN KEY (`medios`) REFERENCES `tipomedios` (`idtipomedios`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medios`
--

LOCK TABLES `medios` WRITE;
/*!40000 ALTER TABLE `medios` DISABLE KEYS */;
/*!40000 ALTER TABLE `medios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plataforma`
--

DROP TABLE IF EXISTS `plataforma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plataforma` (
  `idplataforma` int NOT NULL AUTO_INCREMENT,
  `plataforma` varchar(45) NOT NULL,
  PRIMARY KEY (`idplataforma`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plataforma`
--

LOCK TABLES `plataforma` WRITE;
/*!40000 ALTER TABLE `plataforma` DISABLE KEYS */;
INSERT INTO `plataforma` VALUES (1,'TikTok'),(2,'Twitter'),(3,'Instagram'),(4,'Facebook'),(5,'YouTube');
/*!40000 ALTER TABLE `plataforma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programa`
--

DROP TABLE IF EXISTS `programa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programa` (
  `idprograma` int NOT NULL AUTO_INCREMENT,
  `programa` varchar(45) NOT NULL,
  PRIMARY KEY (`idprograma`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programa`
--

LOCK TABLES `programa` WRITE;
/*!40000 ALTER TABLE `programa` DISABLE KEYS */;
INSERT INTO `programa` VALUES (1,'A24'),(2,'La Nación'),(3,'Radio Mitre '),(4,'TN');
/*!40000 ALTER TABLE `programa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `redsocial`
--

DROP TABLE IF EXISTS `redsocial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `redsocial` (
  `idRedSocial` int NOT NULL AUTO_INCREMENT,
  `plataforma` varchar(45) NOT NULL,
  `ususario` varchar(45) NOT NULL,
  `numeroDeInteracciones` int NOT NULL,
  PRIMARY KEY (`idRedSocial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `redsocial`
--

LOCK TABLES `redsocial` WRITE;
/*!40000 ALTER TABLE `redsocial` DISABLE KEYS */;
/*!40000 ALTER TABLE `redsocial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refutacion`
--

DROP TABLE IF EXISTS `refutacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refutacion` (
  `fecha` date NOT NULL,
  `fuentes` varchar(45) NOT NULL,
  `organismo_oficial` varchar(45) NOT NULL,
  `id_refutacion` int NOT NULL AUTO_INCREMENT,
  `id_refutadores` int NOT NULL,
  PRIMARY KEY (`id_refutacion`),
  KEY `fk_id_refutadores_idx` (`id_refutadores`),
  CONSTRAINT `fk_id_refutadores` FOREIGN KEY (`id_refutadores`) REFERENCES `refutadores` (`id_refutadores`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refutacion`
--

LOCK TABLES `refutacion` WRITE;
/*!40000 ALTER TABLE `refutacion` DISABLE KEYS */;
INSERT INTO `refutacion` VALUES ('1212-12-12','mi mami','si',1,13),('1212-12-12','mi mami','si',2,13),('1212-12-12','mi mami','si',3,13),('1212-12-12','mi mami','si',4,13),('1212-12-12','mi mami','si',5,13),('1212-12-12','mi mami','si',6,13),('1212-12-12','mi mami','si',7,13),('1212-12-12','dsadasda','no',8,7),('1212-12-12','dsadasda','no',9,7),('1212-12-12','dsadasda','no',10,7),('1212-12-12','dsadasda','no',11,7),('1999-09-09','vsdv','si',25,18),('1999-09-09','aasdsad','no',27,8),('2025-12-10','ds','si',28,18),('1999-09-09','csjhcsjnds','no',29,8),('1999-09-09','csjhcsjnds','no',30,8),('1999-09-09','csjhcsjnds','no',31,8),('1999-09-09','csjhcsjnds','no',32,8),('1999-09-09','csjhcsjnds','no',33,8),('1999-09-09','csjhcsjnds','no',34,8),('1999-09-09','csjhcsjnds','no',35,8),('1923-12-12','ciucuid','no',36,8),('1999-09-09','cjkeiwcjcw','no',37,8),('1999-09-09','cniunid','no',38,8),('1999-09-09','vaddsa','no',39,8),('1999-09-09','vaddsa','no',40,8);
/*!40000 ALTER TABLE `refutacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refutadores`
--

DROP TABLE IF EXISTS `refutadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refutadores` (
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `medios_presentes` varchar(45) NOT NULL,
  `id_refutadores` int NOT NULL AUTO_INCREMENT,
  `legajo` int NOT NULL,
  PRIMARY KEY (`id_refutadores`),
  UNIQUE KEY `nombre_apellido_UNIQUE` (`nombre`,`apellido`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refutadores`
--

LOCK TABLES `refutadores` WRITE;
/*!40000 ALTER TABLE `refutadores` DISABLE KEYS */;
INSERT INTO `refutadores` VALUES ('ariel','charca','Red Social',7,312),('ariel','cahrca','Red Social',8,321),('pepe','duartes','Red Social',13,436),('rgr','rgegregr','Traduccion Audiovisual',15,54),('Stella','Sorrestein','Red Social',16,75),('Rigby','Rickerson','Red Social',17,423),('Mordecai','No tiene','Red Social',18,965),('pablo','avendaño','Traduccion Audiovisual',19,5444);
/*!40000 ALTER TABLE `refutadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipomedios`
--

DROP TABLE IF EXISTS `tipomedios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipomedios` (
  `idtipomedios` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`idtipomedios`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipomedios`
--

LOCK TABLES `tipomedios` WRITE;
/*!40000 ALTER TABLE `tipomedios` DISABLE KEYS */;
INSERT INTO `tipomedios` VALUES (1,'Red Social'),(2,'Traduccion Audiovisual'),(3,'Traducciones Escritas');
/*!40000 ALTER TABLE `tipomedios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `traduccionaudiovisual`
--

DROP TABLE IF EXISTS `traduccionaudiovisual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `traduccionaudiovisual` (
  `idTA` int NOT NULL AUTO_INCREMENT,
  `nombreDelPrograma` varchar(45) NOT NULL,
  `minutosDedicados` int NOT NULL,
  `rating` int NOT NULL,
  PRIMARY KEY (`idTA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `traduccionaudiovisual`
--

LOCK TABLES `traduccionaudiovisual` WRITE;
/*!40000 ALTER TABLE `traduccionaudiovisual` DISABLE KEYS */;
/*!40000 ALTER TABLE `traduccionaudiovisual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `traduccionesescritas`
--

DROP TABLE IF EXISTS `traduccionesescritas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `traduccionesescritas` (
  `idTE` int NOT NULL AUTO_INCREMENT,
  `nombreDiario` varchar(45) NOT NULL,
  `nombreNota` varchar(45) NOT NULL,
  `cantidadDeVisualizaciones` int NOT NULL,
  PRIMARY KEY (`idTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `traduccionesescritas`
--

LOCK TABLES `traduccionesescritas` WRITE;
/*!40000 ALTER TABLE `traduccionesescritas` DISABLE KEYS */;
/*!40000 ALTER TABLE `traduccionesescritas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-26  9:54:51
