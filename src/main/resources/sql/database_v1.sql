-- DROP SCHEMA `task_manager`;

CREATE SCHEMA `task_manager`;

CREATE TABLE `task_manager`.`users`
(
    `id`         BIGINT      NOT NULL AUTO_INCREMENT,
    `username`   VARCHAR(45) NOT NULL,
    `password`   VARCHAR(255) NOT NULL,
    `first_name` VARCHAR(45) NOT NULL,
    `last_name`  VARCHAR(45) NOT NULL,
    `role`    	 VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE
);

CREATE TABLE `task_manager`.`workspaces`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(255) NOT NULL,
    `description` TEXT         NULL,
    `status`      VARCHAR(45)  NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `task_manager`.`workspace_users`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT,
    `workspace_id` BIGINT 		NOT NULL,
    `user_id`      BIGINT		NOT NULL,
    `role`     	   VARCHAR(45)  NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `wu_workspace_id_fk_idx` (`workspace_id` ASC) VISIBLE,
    INDEX `wu_user_id_fk_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `wu_workspace_id_fk`
        FOREIGN KEY (`workspace_id`)
            REFERENCES `task_manager`.`workspaces` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `wu_user_id_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `task_manager`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


CREATE TABLE `task_manager`.`tasks`
(
    `id`           BIGINT   	NOT NULL AUTO_INCREMENT,
    `author_id`    BIGINT   	NOT NULL,
    `receiver_id`  BIGINT   	NOT NULL,
    `description`  LONGTEXT 	NULL,
    `status`       VARCHAR(45)  NOT NULL,
    `workspace_id` BIGINT   	NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `author_id_fk_idx` (`author_id` ASC) VISIBLE,
    INDEX `receiver_Id_fk_idx` (`receiver_id` ASC) VISIBLE,
    INDEX `workspace_id_fk_idx` (`workspace_id` ASC) VISIBLE,
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
            ON UPDATE NO ACTION
);

ALTER TABLE `task_manager`.`users`
    ADD COLUMN `deleted` BIT(1) NOT NULL AFTER `role`;