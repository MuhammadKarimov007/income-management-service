CREATE TABLE `expenses` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `user_id` int NOT NULL,
                            `exp_amount` DECIMAL(10, 2) NOT NULL,
                            `exp_date` DATE NOT NULL,
                            `exp_time` TIME NOT NULL,
                            `note` VARCHAR(255) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
