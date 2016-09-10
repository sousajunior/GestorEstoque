-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema estoquedb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema estoquedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `estoquedb` DEFAULT CHARACTER SET utf8 ;
USE `estoquedb` ;

-- -----------------------------------------------------
-- Table `estoquedb`.`armazem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoquedb`.`armazem` (
  `codigoArmazem` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(30) NULL,
  PRIMARY KEY (`codigoArmazem`),
  UNIQUE INDEX `idarmazem_UNIQUE` (`codigoArmazem` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estoquedb`.`unidadeMedida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoquedb`.`unidadeMedida` (
  `idunidadeMedida` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `abreviacao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idunidadeMedida`),
  UNIQUE INDEX `idunidadeMedida_UNIQUE` (`idunidadeMedida` ASC),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC),
  UNIQUE INDEX `abreviacao_UNIQUE` (`abreviacao` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estoquedb`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoquedb`.`produto` (
  `codigoProduto` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `controladoPorLote` TINYINT(1) NOT NULL,
  `quantidadeMinima` DOUBLE UNSIGNED NULL,
  `preco` DOUBLE UNSIGNED NOT NULL,
  `unidadeMedida_idunidadeMedida` INT NOT NULL,
  PRIMARY KEY (`codigoProduto`),
  INDEX `fk_produto_unidadeMedida1_idx` (`unidadeMedida_idunidadeMedida` ASC),
  CONSTRAINT `fk_produto_unidadeMedida1`
    FOREIGN KEY (`unidadeMedida_idunidadeMedida`)
    REFERENCES `estoquedb`.`unidadeMedida` (`idunidadeMedida`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estoquedb`.`fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoquedb`.`fornecedor` (
  `idfornecedor` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(45) NULL,
  `cnpj` VARCHAR(45) NULL,
  PRIMARY KEY (`idfornecedor`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC),
  UNIQUE INDEX `cnpj_UNIQUE` (`cnpj` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estoquedb`.`produtoArmazenado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoquedb`.`produtoArmazenado` (
  `idprodutoArmazenado` INT NOT NULL AUTO_INCREMENT,
  `lote` VARCHAR(45) NULL,
  `quantidade` VARCHAR(45) NOT NULL,
  `notaFiscal` INT NOT NULL,
  `armazem_codigoArmazem` INT UNSIGNED NOT NULL,
  `fornecedor_idfornecedor` INT NOT NULL,
  `produto_codigoProduto` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idprodutoArmazenado`),
  INDEX `fk_produtoArmazenado_armazem1_idx` (`armazem_codigoArmazem` ASC),
  INDEX `fk_produtoArmazenado_fornecedor1_idx` (`fornecedor_idfornecedor` ASC),
  INDEX `fk_produtoArmazenado_produto1_idx` (`produto_codigoProduto` ASC),
  CONSTRAINT `fk_produtoArmazenado_armazem1`
    FOREIGN KEY (`armazem_codigoArmazem`)
    REFERENCES `estoquedb`.`armazem` (`codigoArmazem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produtoArmazenado_fornecedor1`
    FOREIGN KEY (`fornecedor_idfornecedor`)
    REFERENCES `estoquedb`.`fornecedor` (`idfornecedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produtoArmazenado_produto1`
    FOREIGN KEY (`produto_codigoProduto`)
    REFERENCES `estoquedb`.`produto` (`codigoProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estoquedb`.`movimentacoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estoquedb`.`movimentacoes` (
  `idmovimentacao` INT NOT NULL,
  `lote` VARCHAR(45) NULL,
  `quantidade` VARCHAR(45) NULL,
  `notaFiscal` INT NULL,
  `registrosEntradacol` VARCHAR(45) NULL,
  `tipo` CHAR(1) NULL,
  `data` DATE NULL,
  `produtoArmazenado_idprodutoArmazenado` INT NOT NULL,
  `armazem_codigoArmazem` INT UNSIGNED NULL,
  PRIMARY KEY (`idmovimentacao`),
  INDEX `fk_movimentacoes_produtoArmazenado1_idx` (`produtoArmazenado_idprodutoArmazenado` ASC),
  INDEX `fk_movimentacoes_armazem1_idx` (`armazem_codigoArmazem` ASC),
  CONSTRAINT `fk_movimentacoes_produtoArmazenado1`
    FOREIGN KEY (`produtoArmazenado_idprodutoArmazenado`)
    REFERENCES `estoquedb`.`produtoArmazenado` (`idprodutoArmazenado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movimentacoes_armazem1`
    FOREIGN KEY (`armazem_codigoArmazem`)
    REFERENCES `estoquedb`.`armazem` (`codigoArmazem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
