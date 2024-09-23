-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ssafyhome
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafyhome
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafyhome` DEFAULT CHARACTER SET utf8mb4 ;
USE `ssafyhome` ;

-- -----------------------------------------------------
-- Table `ssafyhome`.`dongcodes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyhome`.`dongcodes` ;

CREATE TABLE IF NOT EXISTS `ssafyhome`.`dongcodes` (
  `dong_code` VARCHAR(10) NOT NULL,
  `sido_name` VARCHAR(30) NULL,
  `gugun_name` VARCHAR(30) NULL,
  `dong_name` VARCHAR(30) NULL,
  PRIMARY KEY (`dong_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyhome`.`houseinfos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyhome`.`houseinfos` ;

CREATE TABLE IF NOT EXISTS `ssafyhome`.`houseinfos` (
  `apt_seq` VARCHAR(20) NOT NULL,
  `sgg_cd` VARCHAR(5) NULL,
  `umd_cd` VARCHAR(5) NULL,
  `umd_nm` VARCHAR(20) NULL,
  `jibun` VARCHAR(10) NULL,
  `road_nm_sgg_cd` VARCHAR(5) NULL,
  `road_nm` VARCHAR(20) NULL,
  `road_nm_bonbun` VARCHAR(10) NULL,
  `road_nm_bubun` VARCHAR(10) NULL,
  `apt_nm` VARCHAR(40) NULL,
  `build_year` INT NULL,
  `latitude` VARCHAR(45) NULL,
  `longitude` VARCHAR(45) NULL,
  PRIMARY KEY (`apt_seq`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafyhome`.`housedeals`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyhome`.`housedeals` ;

CREATE TABLE IF NOT EXISTS `ssafyhome`.`housedeals` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `apt_seq` VARCHAR(20) NULL,
  `apt_dong` VARCHAR(40) NULL,
  `floor` VARCHAR(3) NULL,
  `deal_year` INT NULL,
  `deal_month` INT NULL,
  `deal_day` INT NULL,
  `exclu_use_ar` DECIMAL(7,2) NULL,
  `deal_amount` VARCHAR(10) NULL,
  PRIMARY KEY (`no`),
  INDEX `apt_seq_to_house_info_idx` (`apt_seq` ASC) VISIBLE,
  CONSTRAINT `apt_seq_to_house_info`
    FOREIGN KEY (`apt_seq`)
    REFERENCES `ssafyhome`.`houseinfos` (`apt_seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
