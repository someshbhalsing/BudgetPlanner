Follow MySQL databases commands below before using the project

create database budgetplanner;

CREATE TABLE `budgetplanner`.`user` (
  `uid` INT NOT NULL AUTO_INCREMENT,
  `uname` VARCHAR(25) NOT NULL,
  `upassword` VARCHAR(12) NOT NULL,
  `street` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  `phone_no` INT(10) NULL,
  `email_addr` VARCHAR(45) NULL,
  PRIMARY KEY (`uid`));

CREATE TABLE `budgetplanner`.`expensed` (
  `eid` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NULL,
  `item_name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `price` INT NULL,
  `pay_method` VARCHAR(45) NULL,
  `remaining_amount` INT NULL,
  PRIMARY KEY (`eid`));
