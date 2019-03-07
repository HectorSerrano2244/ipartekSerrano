CREATE DATABASE  IF NOT EXISTS `dgt_hector` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dgt_hector`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dgt_hector
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agente`
--

DROP TABLE IF EXISTS `agente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `placa` int(11) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `placa_UNIQUE` (`placa`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agente`
--

LOCK TABLES `agente` WRITE;
/*!40000 ALTER TABLE `agente` DISABLE KEYS */;
INSERT INTO `agente` VALUES (1,'Majonei',123456,'123456'),(2,'Johnny Walker',987654,'t45gt4w5t'),(3,'Monk',987321,'123456789'),(4,'Takelberry',987987,'987987'),(5,'Tontimmy',998776,'7miti76');
/*!40000 ALTER TABLE `agente` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tbi_agente_comprobacion` BEFORE INSERT ON `agente` FOR EACH ROW BEGIN
	set new.nombre=trim(new.nombre);
    set new.password=trim(new.password);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `multa`
--

DROP TABLE IF EXISTS `multa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `multa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `importe` float DEFAULT '50',
  `concepto` varchar(255) DEFAULT NULL,
  `fecha_alta` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_vehiculo` int(11) NOT NULL,
  `id_agente` int(11) NOT NULL,
  `fecha_mod` datetime DEFAULT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_multa_vehiculo_idx` (`id_vehiculo`) /*!80000 INVISIBLE */,
  KEY `fk__idx` (`id_agente`),
  CONSTRAINT `fk_multa_agente` FOREIGN KEY (`id_agente`) REFERENCES `agente` (`id`),
  CONSTRAINT `fk_multa_vehiculo` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multa`
--

LOCK TABLES `multa` WRITE;
/*!40000 ALTER TABLE `multa` DISABLE KEYS */;
INSERT INTO `multa` VALUES (12,21.53,'234567876532weerewtrwywete','2018-09-14 10:25:30',1,4,'2019-03-04 09:59:44',NULL),(13,0,'por muy feo','2018-09-14 11:06:39',1,4,'2019-03-04 12:58:53',NULL),(14,500,'por muy feo','2018-08-14 13:02:47',1,4,NULL,'2019-03-04 09:57:43'),(15,500.7,'vas mas borracho que el tato','2018-06-15 12:25:10',2,4,'2019-03-04 13:28:14',NULL),(16,500.7,'borrachuzo de mierda','2018-05-15 12:31:15',2,4,'2019-03-04 09:27:53','2019-03-04 13:28:07'),(17,500.7,'borrachuzo de mierda','2018-02-15 12:33:21',2,4,'2019-03-04 09:25:44',NULL),(18,500.7,'borrachuzo de mierda','2019-01-15 12:35:22',2,4,'2019-03-04 09:23:29',NULL),(19,500.7,'borrachuzo de mierda','2018-08-15 12:37:48',2,4,NULL,'2019-03-04 09:50:47'),(20,500.7,'borrachuzo de mierda','2019-01-15 12:38:49',2,4,NULL,'2019-03-04 09:47:07'),(21,500.7,'borrachuzo de mierda','2019-01-15 12:39:12',2,4,'2019-03-04 09:59:50',NULL),(22,500.7,'borrachuzo de mierda','2018-04-15 12:42:02',2,4,NULL,'2019-01-07 08:30:22'),(23,500.7,'borrachuzo de mierda','2018-07-15 12:45:28',2,4,NULL,'2019-03-04 09:45:44'),(24,500.7,'borrachuzo de mierda','2018-11-15 12:45:32',2,4,NULL,'2019-03-04 09:33:04'),(25,500.7,'borrachuzo de mierda','2018-01-15 12:46:07',2,4,'2019-03-04 09:53:13',NULL),(26,500.7,'borrachuzo de mierda','2018-10-15 12:48:21',2,4,NULL,'2019-01-01 08:30:22'),(27,500.7,'borrachuzo de mierda','2018-11-15 12:49:41',2,4,NULL,'2019-03-04 09:37:06'),(28,456.6,'fdgrd','2018-12-16 09:21:46',2,4,'2019-03-04 09:53:59','2019-03-04 09:56:51'),(29,540.59,'aefwefwefwfewefwe','2018-12-17 12:08:48',1,1,NULL,NULL),(30,1445,'pruebaaaaaaa','2018-03-20 21:21:10',2,4,NULL,'2019-03-04 09:34:09'),(31,0,'khfkesjh','2018-03-21 10:02:01',3,4,NULL,'2019-03-04 09:29:32'),(32,1100,'Prueba en REST','2019-02-28 08:26:08',2,3,NULL,NULL),(33,1100,'Otra prueba en REST','2019-02-28 08:27:29',1,2,NULL,NULL),(34,9999,'Prueba REST','2019-02-28 14:22:44',1,4,'2019-03-04 11:18:49',NULL),(41,150,'Probando desde Swagger','2019-03-01 13:51:42',1,4,'2019-03-04 09:59:22','2019-03-04 12:58:48'),(44,150,'LA MULTA DEFINITIVA','2019-03-01 14:13:35',1,4,'2019-03-04 09:52:43','2019-03-04 12:57:44'),(45,900,'Voy a probar esto de nuevo','2019-03-04 10:26:31',1,4,NULL,'2019-03-04 12:36:09'),(46,20,'OTRA PRUEBA RESTTTTTTTTTT','2019-03-04 11:54:56',1,4,'2019-03-04 12:58:42',NULL),(48,300,'PRUEBO CON METER OTRA PORQUE ME QUEDO SIN MULTAS','2019-03-04 13:01:59',3,4,'2019-03-04 13:40:57',NULL);
/*!40000 ALTER TABLE `multa` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tbi_multa_correcciones` BEFORE INSERT ON `multa` FOR EACH ROW BEGIN
	set new.concepto=trim(new.concepto);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `objetivo`
--

DROP TABLE IF EXISTS `objetivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `objetivo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Anio` int(4) NOT NULL,
  `objetivo` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objetivo`
--

LOCK TABLES `objetivo` WRITE;
/*!40000 ALTER TABLE `objetivo` DISABLE KEYS */;
INSERT INTO `objetivo` VALUES (1,2018,12000),(2,2019,14400);
/*!40000 ALTER TABLE `objetivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vehiculo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(10) NOT NULL,
  `modelo` varchar(45) NOT NULL DEFAULT 'cuatro latas',
  `km` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `matricula_UNIQUE` (`matricula`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES (1,'3548MKZ','Toyota Yaris',500),(2,'9605EFH','Fiat multipla',800),(3,'5674MBD','GRT',1800),(4,'BI0020AZ','flagoneta',47500);
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tbi_coche_comprobaciones` BEFORE INSERT ON `vehiculo` FOR EACH ROW BEGIN
	set new.matricula=trim(new.matricula);
    set new.modelo=trim(new.modelo);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'dgt_hector'
--

--
-- Dumping routines for database 'dgt_hector'
--
/*!50003 DROP PROCEDURE IF EXISTS `pa_agente_getById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_agente_getById`(IN p_id LONG)
BEGIN
	SELECT 
		id, 
        nombre,
        placa,
        `password`
    FROM 
		agente 
    WHERE 
		id = p_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_coche_getByMatricula` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_coche_getByMatricula`(IN p_matricula VARCHAR(50))
BEGIN
SELECT id,matricula,modelo,km FROM vehiculo WHERE matricula= p_matricula;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_login` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_login`(IN p_placa INT, IN p_pass VARCHAR(20))
BEGIN
SELECT 
	a.id,
	a.nombre,
	a.placa,
    a.password
FROM 
	agente as a
WHERE
	a.placa=p_placa AND
    a.password=p_pass;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_multa_devuelveTotales` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_multa_devuelveTotales`(IN p_opcion varchar(30),IN p_idagente LONG,IN p_anio INTEGER,IN p_mes INTEGER,OUT o_total FLOAT)
BEGIN
IF p_opcion='totalMensualAnio' then
	SELECT
		round(sum(importe)) INTO o_total
	FROM
		multa
    WHERE
		id_agente=p_idagente AND
        month(fecha_alta)=p_mes AND
        year(fecha_alta)=p_anio AND
        fecha_baja is null;
elseif p_opcion='totalAnual' then
	SELECT
		round(sum(importe)) INTO o_total
	FROM
		multa
    WHERE
		id_agente=p_idagente AND
        year(fecha_alta)=p_anio AND
        fecha_baja is null;
end if;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_multa_getByAgenteId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_multa_getByAgenteId`(IN p_id_agente INT, IN p_operacion VARCHAR(15))
BEGIN

IF p_operacion = 'inactivas' then
	SELECT 
		m.id,
		m.fecha_alta, 
		m.fecha_baja,
        m.importe,
        m.concepto,
		v.matricula,
		v.modelo,
        v.km
	FROM 
		multa m, 
		vehiculo v 
	WHERE 
		m.id_vehiculo = v.id AND 
		m.id_agente = p_id_agente AND
		m.fecha_baja IS NOT NULL
	ORDER BY m.id DESC LIMIT 1000;
            
else
	SELECT 
		m.id,
		m.fecha_alta, 
		m.fecha_baja,
        m.importe,
		m.concepto,
		v.matricula, 
		v.modelo,
        v.km
	FROM 
		multa m, 
		vehiculo v 
	WHERE 
		m.id_vehiculo = v.id AND 
		m.id_agente = p_id_agente AND
		m.fecha_baja IS NULL
	ORDER BY m.id DESC LIMIT 1000;
end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_multa_getById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_multa_getById`(IN p_id_multa LONG)
BEGIN
SELECT
	m.id, 
    id_agente,
    id_coche,
    fecha_alta,
    fecha_baja, 
    importe, 
    concepto,
    v.matricula,
    v.modelo,
    v.km
FROM 
	multa as m,
    vehiculo as v
WHERE 
	m.id_coche=v.id AND
    m.id = p_id_multa;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_multa_insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_multa_insert`(IN p_importe FLOAT, IN p_concepto VARCHAR(255), IN p_id_vehiculo LONG, IN p_id_agente LONG, OUT o_id LONG)
BEGIN
INSERT INTO multa (`importe`, `concepto`, `id_vehiculo`, `id_agente`) VALUES (p_importe, p_concepto, p_id_vehiculo, p_id_agente);

SET o_id=last_insert_id();

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_multa_update` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_multa_update`(IN p_id LONG, IN p_oper VARCHAR(15))
BEGIN
IF p_oper = 'recuperar' then
	UPDATE 
		multa 
    SET 
		fecha_baja = NULL,
        fecha_mod = CURRENT_TIMESTAMP 
	WHERE 
		id = p_id;
else
	UPDATE 
		multa 
    SET 
		fecha_baja = CURRENT_TIMESTAMP  
	WHERE 
		id = p_id;
end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-04 14:25:25
