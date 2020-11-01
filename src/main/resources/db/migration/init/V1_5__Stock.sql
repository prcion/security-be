CREATE TABLE `stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `logo` varchar(200) NOT NULL,
  `currency` varchar(200) NOT NULL,
  `country` varchar(200) NOT NULL,
  `exchange_id` bigint(20) NOT NULL,
  `industry_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`exchange_id`) REFERENCES `exchange` (`id`) ON DELETE CASCADE,
  FOREIGN KEY (`industry_id`) REFERENCES `industry` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



