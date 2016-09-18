-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: estoquedb
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.13-MariaDB

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

CREATE SCHEMA IF NOT EXISTS `estoquedb` DEFAULT CHARACTER SET utf8 ;
USE `estoquedb` ;

--
-- Table structure for table `armazem`
--

DROP TABLE IF EXISTS `armazem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `armazem` (
  `codigoArmazem` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descricao` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`codigoArmazem`),
  UNIQUE KEY `idarmazem_UNIQUE` (`codigoArmazem`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `armazem`
--

LOCK TABLES `armazem` WRITE;
/*!40000 ALTER TABLE `armazem` DISABLE KEYS */;
INSERT INTO `armazem` VALUES (1,'Almoxarifado'),(2,'Câmara fria'),(3,'Armazem Dell');
/*!40000 ALTER TABLE `armazem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedor` (
  `idfornecedor` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `cnpj` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idfornecedor`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`),
  UNIQUE KEY `cnpj_UNIQUE` (`cnpj`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (1,'BRF',NULL,'514845'),(2,'Kalunga',NULL,'789426'),(3,'Matheus','078.751.589-23',NULL);
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimentacoes`
--

DROP TABLE IF EXISTS `movimentacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimentacoes` (
  `idmovimentacao` int(11) NOT NULL  AUTO_INCREMENT,
  `lote` varchar(45) DEFAULT NULL,
  `quantidade` varchar(45) DEFAULT NULL,
  `notaFiscal` int(11) DEFAULT NULL,
  `tipo` char(1) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `produtoArmazenado_idprodutoArmazenado` int(11) NOT NULL,
  `armazem_codigoArmazem` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idmovimentacao`),
  KEY `fk_movimentacoes_produtoArmazenado1_idx` (`produtoArmazenado_idprodutoArmazenado`),
  KEY `fk_movimentacoes_armazem1_idx` (`armazem_codigoArmazem`),
  CONSTRAINT `fk_movimentacoes_armazem1` FOREIGN KEY (`armazem_codigoArmazem`) REFERENCES `armazem` (`codigoArmazem`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_movimentacoes_produtoArmazenado1` FOREIGN KEY (`produtoArmazenado_idprodutoArmazenado`) REFERENCES `produtoarmazenado` (`idprodutoArmazenado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimentacoes`
--

LOCK TABLES `movimentacoes` WRITE;
/*!40000 ALTER TABLE `movimentacoes` DISABLE KEYS */;
INSERT INTO `movimentacoes` VALUES (1,'09FEV-A','50',56395,'e','2016-05-09',1,1),(2,'SAD23SET-C','100',1110,'e','2016-05-09',2,2),(3,'SAD23SET-B','20',1110,'e','2016-05-09',2,2),(5,NULL,'2',27062016,'e','2016-05-09',4,1),(6,NULL,'1',27062016,'s','2016-05-09',4,1);
/*!40000 ALTER TABLE `movimentacoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `codigoProduto` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `controladoPorLote` tinyint(1) NOT NULL,
  `quantidadeMinima` double unsigned DEFAULT NULL,
  `preco` double unsigned NOT NULL,
  `unidadeMedida_idunidadeMedida` int(11) NOT NULL,
  PRIMARY KEY (`codigoProduto`),
  KEY `fk_produto_unidadeMedida1_idx` (`unidadeMedida_idunidadeMedida`),
  CONSTRAINT `fk_produto_unidadeMedida1` FOREIGN KEY (`unidadeMedida_idunidadeMedida`) REFERENCES `unidademedida` (`idunidadeMedida`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Caneta BIC',0,20,1,3),(2,'coxa de frango',1,NULL,5,4),(3,'Carimbo',0,10,2,3),(4,'Água Mineral Leve',1,5,9,2),(5,'bife ',1,0,12,1),(6,'Copo descartável',0,1,2,4);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtoarmazenado`
--

DROP TABLE IF EXISTS `produtoarmazenado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtoarmazenado` (
  `idprodutoArmazenado` int(11) NOT NULL AUTO_INCREMENT,
  `lote` varchar(45) DEFAULT NULL,
  `quantidade` varchar(45) NOT NULL,
  `notaFiscal` int(11) NOT NULL,
  `armazem_codigoArmazem` int(10) unsigned NOT NULL,
  `fornecedor_idfornecedor` int(11) NOT NULL,
  `produto_codigoProduto` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idprodutoArmazenado`),
  KEY `fk_produtoArmazenado_armazem1_idx` (`armazem_codigoArmazem`),
  KEY `fk_produtoArmazenado_fornecedor1_idx` (`fornecedor_idfornecedor`),
  KEY `fk_produtoArmazenado_produto1_idx` (`produto_codigoProduto`),
  CONSTRAINT `fk_produtoArmazenado_armazem1` FOREIGN KEY (`armazem_codigoArmazem`) REFERENCES `armazem` (`codigoArmazem`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_produtoArmazenado_fornecedor1` FOREIGN KEY (`fornecedor_idfornecedor`) REFERENCES `fornecedor` (`idfornecedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_produtoArmazenado_produto1` FOREIGN KEY (`produto_codigoProduto`) REFERENCES `produto` (`codigoProduto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtoarmazenado`
--

LOCK TABLES `produtoarmazenado` WRITE;
/*!40000 ALTER TABLE `produtoarmazenado` DISABLE KEYS */;
INSERT INTO `produtoarmazenado` VALUES (1,NULL,'50',56395,1,2,1),(2,'SAD23SET-C','100',1110,2,1,2),(3,'SAD23SET-B','20',1110,2,1,2),(4,NULL,'1',27062016,1,3,6);
/*!40000 ALTER TABLE `produtoarmazenado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidademedida`
--

DROP TABLE IF EXISTS `unidademedida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unidademedida` (
  `idunidadeMedida` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `abreviacao` varchar(45) NOT NULL,
  PRIMARY KEY (`idunidadeMedida`),
  UNIQUE KEY `idunidadeMedida_UNIQUE` (`idunidadeMedida`),
  UNIQUE KEY `nome_UNIQUE` (`nome`),
  UNIQUE KEY `abreviacao_UNIQUE` (`abreviacao`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidademedida`
--

LOCK TABLES `unidademedida` WRITE;
/*!40000 ALTER TABLE `unidademedida` DISABLE KEYS */;
INSERT INTO `unidademedida` VALUES (1,'kilo','kg'),(2,'litro','l'),(3,'unidade','un'),(4,'caixas','cx'),(5,'galões','gal'),(6,'metros','m'),(7,'centímetros','cm'),(8,'gramas','g');
/*!40000 ALTER TABLE `unidademedida` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


-- Dump completed on 2016-09-07 12:40:31
