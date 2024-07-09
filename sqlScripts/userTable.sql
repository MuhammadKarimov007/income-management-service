CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `first_name` varchar(30) DEFAULT NULL,
                        `last_name` varchar(30) DEFAULT NULL,
                        `email` varchar(120) NOT NULL UNIQUE,
                        `birth_date` date,
                        `gender` VARCHAR(6) CHECK (`gender` IN ('Male', 'Female')),
                        `balance` DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
