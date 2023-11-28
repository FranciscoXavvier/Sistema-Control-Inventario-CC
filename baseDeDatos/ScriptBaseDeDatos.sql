-- MySQL Script generated by MySQL Workbench
-- Fri Oct 20 16:14:10 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SistemaControlHardwareSoftware
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SistemaControlHardwareSoftware
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SistemaControlHardwareSoftware` ;
USE `SistemaControlHardwareSoftware` ;

-- -----------------------------------------------------
-- Table `SistemaControlHardwareSoftware`.`Programa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaControlHardwareSoftware`.`Programa` (
  `idPrograma` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `version` VARCHAR(45) NULL,
  `edicion` VARCHAR(45) NULL,
  `idioma` VARCHAR(45) NULL,
  PRIMARY KEY (`idPrograma`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SistemaControlHardwareSoftware`.`Tipo_Periferico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaControlHardwareSoftware`.`Tipo_Periferico` (
  `idTipo_Periferico` INT NOT NULL AUTO_INCREMENT,
  `tipo_periferico` VARCHAR(45) NULL,
  PRIMARY KEY (`idTipo_Periferico`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SistemaControlHardwareSoftware`.`Sistema_Operativo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaControlHardwareSoftware`.`Sistema_Operativo` (
  `idSistema_Operativo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `version` VARCHAR(45) NULL,
  `edicion` VARCHAR(45) NULL,
  `arquitectura` VARCHAR(45) NULL,
  `tipo` VARCHAR(45) NULL,
  `idioma` VARCHAR(45) NULL,
  PRIMARY KEY (`idSistema_Operativo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SistemaControlHardwareSoftware`.`Tecnico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaControlHardwareSoftware`.`Tecnico` (
  `idTecnico` INT NOT NULL AUTO_INCREMENT,
  `numero_personal` INT NULL,
  `nombre` VARCHAR(45) NULL,
  `apellido_paterno` VARCHAR(45) NULL,
  `apellido_materno` VARCHAR(45) NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(80) NULL,
  PRIMARY KEY (`idTecnico`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SistemaControlHardwareSoftware`.`Centro_Computo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaControlHardwareSoftware`.`Centro_Computo` (
  `idCentro_Computo` INT NOT NULL AUTO_INCREMENT,
  `Tecnico_idTecnico` INT NOT NULL,
  `nombre_centro_computo` VARCHAR(45) NULL,
  PRIMARY KEY (`idCentro_Computo`),
  CONSTRAINT `fk_Centro_Computo_Tecnico1`
    FOREIGN KEY (`Tecnico_idTecnico`)
    REFERENCES `SistemaControlHardwareSoftware`.`Tecnico` (`idTecnico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SistemaControlHardwareSoftware`.`Equipo_Computo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaControlHardwareSoftware`.`Equipo_Computo` (
  `idCPU` INT NOT NULL AUTO_INCREMENT,
  `Sistema_Operativo_idSistema_Operativo` INT NOT NULL,
  `Centro_Computo_idCentro_Computo` INT NOT NULL,
  `procesador` VARCHAR(45) NULL,
  `tarjeta_madre` VARCHAR(45) NULL,
  `memoria_ram` VARCHAR(45) NULL,
  `almacenamiento` VARCHAR(45) NULL,
  `lector_optico` VARCHAR(45) NULL,
  `codigo_de_barras` VARCHAR(45) NULL,
  `fila` VARCHAR(2) NULL,
  `columna` VARCHAR(2) NULL,
  PRIMARY KEY (`idCPU`),
  CONSTRAINT `fk_CPU_Sistema_Operativo1`
    FOREIGN KEY (`Sistema_Operativo_idSistema_Operativo`)
    REFERENCES `SistemaControlHardwareSoftware`.`Sistema_Operativo` (`idSistema_Operativo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CPU_Centro_Computo1`
    FOREIGN KEY (`Centro_Computo_idCentro_Computo`)
    REFERENCES `SistemaControlHardwareSoftware`.`Centro_Computo` (`idCentro_Computo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SistemaControlHardwareSoftware`.`Periferico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaControlHardwareSoftware`.`Periferico` (
  `Tipo_Periferico_idTipo_Periferico` INT NOT NULL,
  `Equipo_Computo_idEquipo_Computo` INT NOT NULL,
  `marca` VARCHAR(45) NULL,
  `modelo` VARCHAR(45) NULL,
  PRIMARY KEY (`Tipo_Periferico_idTipo_Periferico`, `Equipo_Computo_idEquipo_Computo`),
  CONSTRAINT `fk_Periferico_Tipo_Periferico`
    FOREIGN KEY (`Tipo_Periferico_idTipo_Periferico`)
    REFERENCES `SistemaControlHardwareSoftware`.`Tipo_Periferico` (`idTipo_Periferico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Periferico_CPU1`
    FOREIGN KEY (`Equipo_Computo_idEquipo_Computo`)
    REFERENCES `SistemaControlHardwareSoftware`.`Equipo_Computo` (`idCPU`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SistemaControlHardwareSoftware`.`Programa_Instalado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaControlHardwareSoftware`.`Programa_Instalado` (
  `Programa_idPrograma` INT NOT NULL,
  `CPU_idCPU` INT NOT NULL,
  PRIMARY KEY (`Programa_idPrograma`, `CPU_idCPU`),
  CONSTRAINT `fk_Programa_has_CPU_Programa1`
    FOREIGN KEY (`Programa_idPrograma`)
    REFERENCES `SistemaControlHardwareSoftware`.`Programa` (`idPrograma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Programa_has_CPU_CPU1`
    FOREIGN KEY (`CPU_idCPU`)
    REFERENCES `SistemaControlHardwareSoftware`.`Equipo_Computo` (`idCPU`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;