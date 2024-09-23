CREATE SCHEMA IF NOT EXISTS `hwdb` DEFAULT CHARACTER SET utf8 ;
USE `hwdb` ;

CREATE TABLE IF NOT EXISTS `hwdb`.`movie` (
  `id` VARCHAR(16) NOT NULL,
  `director` VARCHAR(45) NULL,
  `genre` VARCHAR(45) NULL,
  `runningtime` TIMESTAMP NULL,
  `releasedate` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `hwdb`.`scoreinfo` (
  `name` VARCHAR(20) NOT NULL,
  `rating` INT NULL,
  `comment` VARCHAR(45) NULL,
  `movie_id` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`movie_id`, `name`),
  CONSTRAINT `aa`
    FOREIGN KEY (`movie_id`)
    REFERENCES `hwdb`.`movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;