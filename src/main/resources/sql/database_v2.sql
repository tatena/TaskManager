-- Additional columns for implementing timezone --

ALTER TABLE `task_manager`.`tasks`
ADD COLUMN `start_date` DATETIME NOT NULL   AFTER `workspace_id`,
ADD COLUMN `end_date`   DATETIME NULL       AFTER `start_date`;
ADD COLUMN `deadline`   DATETIME NOT NULL   AFTER `end_date`,

ALTER TABLE `task_manager`.`users`
ADD COLUMN `timezone` INT NULL AFTER `deleted`;

ALTER TABLE `task_manager`.`workspaces`
ADD COLUMN `timezone` INT NULL AFTER `status`;
