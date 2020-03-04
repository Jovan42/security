
START TRANSACTION;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `first_name` bigint(20) DEFAULT NULL,
  `last_name` bigint(20) DEFAULT NULL,
  `user_name` bigint(20) DEFAULT NULL,
  `password` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `users` WRITE;
UNLOCK TABLES;
COMMIT;
