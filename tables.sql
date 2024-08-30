CREATE TABLE `user` (
                        `user_id` bigint NOT NULL AUTO_INCREMENT,
                        `created_at` datetime(6) DEFAULT NULL,
                        `modified_at` datetime(6) DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `role` tinyint DEFAULT NULL,
                        `username` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`user_id`),
                        CONSTRAINT `user_chk_1` CHECK (`role` BETWEEN 0 AND 1),
                        UNIQUE (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `todo` (
                        `todo_id` bigint NOT NULL AUTO_INCREMENT,
                        `created_at` datetime(6) DEFAULT NULL,
                        `modified_at` datetime(6) DEFAULT NULL,
                        `todo_contents` varchar(255) DEFAULT NULL,
                        `todo_title` varchar(255) DEFAULT NULL,
                        `user_id` bigint DEFAULT NULL,
                        `weather` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`todo_id`),
                        CONSTRAINT `fk_user_todo` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `comment` (
                           `comment_id` bigint NOT NULL AUTO_INCREMENT,
                           `created_at` datetime(6) DEFAULT NULL,
                           `modified_at` datetime(6) DEFAULT NULL,
                           `comment_contents` varchar(255) DEFAULT NULL,
                           `username` varchar(255) DEFAULT NULL,
                           `todo_id` bigint DEFAULT NULL,
                           PRIMARY KEY (`comment_id`),
                           KEY `FK_comment_todo` (`todo_id`),
                           CONSTRAINT `FK_comment_todo` FOREIGN KEY (`todo_id`) REFERENCES `todo` (`todo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_todo` (
                             `utid` bigint NOT NULL AUTO_INCREMENT,
                             `todo_id` bigint DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL,
                             PRIMARY KEY (`utid`),
                             KEY `FK_user_todo_user` (`user_id`),
                             KEY `FK_user_todo_todo` (`todo_id`),
                             CONSTRAINT `FK_user_todo_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
                             CONSTRAINT `FK_user_todo_todo` FOREIGN KEY (`todo_id`) REFERENCES `todo` (`todo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
