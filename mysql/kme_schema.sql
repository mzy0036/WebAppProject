-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema kme
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema kme
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `kme` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `kme` ;

-- -----------------------------------------------------
-- Table `kme`.`assignment_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kme`.`assignment_type` (
  `assignment_type_id` INT NOT NULL AUTO_INCREMENT,
  `alias` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`assignment_type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kme`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kme`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(60) NULL DEFAULT NULL,
  `password` VARCHAR(256) NULL DEFAULT NULL,
  `firstname` VARCHAR(60) NULL DEFAULT NULL,
  `lastname` VARCHAR(60) NULL DEFAULT NULL,
  `email` VARCHAR(80) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kme`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kme`.`course` (
  `course_id` INT NOT NULL AUTO_INCREMENT,
  `course_number` VARCHAR(10) NULL DEFAULT NULL,
  `course_name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(256) NULL DEFAULT NULL,
  `teacher_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  INDEX `teacher_id` (`teacher_id` ASC) VISIBLE,
  CONSTRAINT `course_ibfk_1`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `kme`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kme`.`assignment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kme`.`assignment` (
  `assignment_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(256) NULL DEFAULT NULL,
  `assignment_type_id` INT NULL DEFAULT NULL,
  `course_id` INT NULL DEFAULT NULL,
  `student_id` INT NULL DEFAULT NULL,
  `due_date` DATETIME NULL DEFAULT NULL,
  `open_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`assignment_id`),
  INDEX `assignment_type_id` (`assignment_type_id` ASC) VISIBLE,
  INDEX `course_id` (`course_id` ASC) VISIBLE,
  INDEX `student_id` (`student_id` ASC) VISIBLE,
  CONSTRAINT `assignment_ibfk_1`
    FOREIGN KEY (`assignment_type_id`)
    REFERENCES `kme`.`assignment_type` (`assignment_type_id`),
  CONSTRAINT `assignment_ibfk_2`
    FOREIGN KEY (`course_id`)
    REFERENCES `kme`.`course` (`course_id`),
  CONSTRAINT `assignment_ibfk_3`
    FOREIGN KEY (`student_id`)
    REFERENCES `kme`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kme`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kme`.`question` (
  `question_id` INT NOT NULL AUTO_INCREMENT,
  `question_text` VARCHAR(256) NULL DEFAULT NULL,
  `assignment_id` INT NULL DEFAULT NULL,
  `question_type` ENUM('multiple-choice', 'short-answer') NULL DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  INDEX `assignment_id` (`assignment_id` ASC) VISIBLE,
  CONSTRAINT `question_ibfk_1`
    FOREIGN KEY (`assignment_id`)
    REFERENCES `kme`.`assignment` (`assignment_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kme`.`answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kme`.`answers` (
  `answer_id` INT NOT NULL AUTO_INCREMENT,
  `question_id` INT NULL DEFAULT NULL,
  `answer_text` VARCHAR(128) NULL DEFAULT NULL,
  `correct_answer` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`answer_id`),
  INDEX `question_id` (`question_id` ASC) VISIBLE,
  CONSTRAINT `answers_ibfk_1`
    FOREIGN KEY (`question_id`)
    REFERENCES `kme`.`question` (`question_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kme`.`grade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kme`.`grade` (
  `grade_id` INT NOT NULL AUTO_INCREMENT,
  `assignment_id` INT NULL DEFAULT NULL,
  `earned_grade` DECIMAL(3,2) NULL DEFAULT NULL,
  `maximum_grade` DECIMAL(3,2) NULL DEFAULT NULL,
  `weight` DECIMAL(3,2) NULL DEFAULT NULL,
  PRIMARY KEY (`grade_id`),
  INDEX `assignment_id` (`assignment_id` ASC) VISIBLE,
  CONSTRAINT `grade_ibfk_1`
    FOREIGN KEY (`assignment_id`)
    REFERENCES `kme`.`assignment` (`assignment_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kme`.`messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kme`.`messages` (
  `messageID` INT NOT NULL,
  `SenderID` INT NOT NULL,
  `RecieverID` INT NOT NULL DEFAULT '-1',
  `content` VARCHAR(1024) NOT NULL,
  `TimeSent` DATETIME NOT NULL,
  PRIMARY KEY (`messageID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `kme`.`teacher_student_lookup`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kme`.`teacher_student_lookup` (
  `teacher_student_id` INT NOT NULL AUTO_INCREMENT,
  `course_id` INT NULL DEFAULT NULL,
  `teacher_id` INT NULL DEFAULT NULL,
  `student_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`teacher_student_id`),
  INDEX `course_id` (`course_id` ASC) VISIBLE,
  INDEX `teacher_id` (`teacher_id` ASC) VISIBLE,
  INDEX `student_id` (`student_id` ASC) VISIBLE,
  CONSTRAINT `teacher_student_lookup_ibfk_1`
    FOREIGN KEY (`course_id`)
    REFERENCES `kme`.`course` (`course_id`),
  CONSTRAINT `teacher_student_lookup_ibfk_2`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `kme`.`user` (`user_id`),
  CONSTRAINT `teacher_student_lookup_ibfk_3`
    FOREIGN KEY (`student_id`)
    REFERENCES `kme`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
