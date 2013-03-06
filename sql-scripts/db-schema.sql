SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `mybatis-preso` ;
CREATE SCHEMA IF NOT EXISTS `mybatis-preso` DEFAULT CHARACTER SET utf8 ;
USE `mybatis-preso` ;

-- -----------------------------------------------------
-- Table `mybatis-preso`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mybatis-preso`.`Users` ;

CREATE  TABLE IF NOT EXISTS `mybatis-preso`.`Users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE UNIQUE INDEX `name_UNIQUE` ON `mybatis-preso`.`Users` (`name` ASC) ;

CREATE UNIQUE INDEX `email_UNIQUE` ON `mybatis-preso`.`Users` (`email` ASC) ;


-- -----------------------------------------------------
-- Table `mybatis-preso`.`Systems`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mybatis-preso`.`Systems` ;

CREATE  TABLE IF NOT EXISTS `mybatis-preso`.`Systems` (
  `id` INT NOT NULL ,
  `name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mybatis-preso`.`Modules`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mybatis-preso`.`Modules` ;

CREATE  TABLE IF NOT EXISTS `mybatis-preso`.`Modules` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `idSystem` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mybatis-preso`.`Routines`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mybatis-preso`.`Routines` ;

CREATE  TABLE IF NOT EXISTS `mybatis-preso`.`Routines` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `idModule` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mybatis-preso`.`Users_Systems`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mybatis-preso`.`Users_Systems` ;

CREATE  TABLE IF NOT EXISTS `mybatis-preso`.`Users_Systems` (
  `idUser` INT NOT NULL ,
  `idSystem` INT NOT NULL ,
  PRIMARY KEY (`idUser`, `idSystem`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mybatis-preso`.`Users_Routines`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mybatis-preso`.`Users_Routines` ;

CREATE  TABLE IF NOT EXISTS `mybatis-preso`.`Users_Routines` (
  `Users_id` INT(11) NOT NULL ,
  `Routines_id` INT NOT NULL ,
  `active` BIT NULL DEFAULT 0 ,
  PRIMARY KEY (`Users_id`, `Routines_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
