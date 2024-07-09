CREATE TABLE `user_passwords` (
                                  `user_id` int NOT NULL,
                                  `password` varchar(68) NOT NULL,
                                  PRIMARY KEY (`user_id`),
                                  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
                                  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;