CREATE SCHEMA `task_manager` ;

CREATE TABLE `task_manager`.`roles` (
  `id` TINYINT(4) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

CREATE TABLE `task_manager`.`users` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `role_id` TINYINT(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  INDEX `user_status_fk_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `user_status_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `task_manager`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  
CREATE TABLE `task_manager`.`workspace_status` (
  `id` TINYINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `task_manager`.`workspaces` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `description` TEXT NULL,
  `status_id` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `workspace_status_fk_idx` (`status_id` ASC) VISIBLE,
  CONSTRAINT `workspace_status_fk`
    FOREIGN KEY (`status_id`)
    REFERENCES `task_manager`.`workspace_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `task_manager`.`task_status` (
  `id` TINYINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `task_manager`.`tasks` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `author_id` BIGINT NOT NULL,
  `receiver_id` BIGINT NOT NULL,
  `description` LONGTEXT NULL,
  `status_id` TINYINT NOT NULL,
  `workspace_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `author_id_fk_idx` (`author_id` ASC) VISIBLE,
  INDEX `receiver_Id_fk_idx` (`receiver_id` ASC) VISIBLE,
  INDEX `workspace_id_fk_idx` (`workspace_id` ASC) VISIBLE,
  INDEX `task_status_fk_idx` (`status_id` ASC) VISIBLE,
  CONSTRAINT `author_id_fk`
    FOREIGN KEY (`author_id`)
    REFERENCES `task_manager`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `receiver_Id_fk`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `task_manager`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `workspace_id_fk`
    FOREIGN KEY (`workspace_id`)
    REFERENCES `task_manager`.`workspaces` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `task_status_fk`
    FOREIGN KEY (`status_id`)
    REFERENCES `task_manager`.`task_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE TABLE `task_manager`.`workspace_users` (
  `workspace_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `role_id` TINYINT NOT NULL,
  INDEX `wu_workspace_id_fk_idx` (`workspace_id` ASC) VISIBLE,
  INDEX `wu_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `wu_role_id_fk_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `wu_workspace_id_fk`
    FOREIGN KEY (`workspace_id`)
    REFERENCES `task_manager`.`workspaces` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `wu_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `task_manager`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `wu_role_id_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `task_manager`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  