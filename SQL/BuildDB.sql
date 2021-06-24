-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.9-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for transfer
CREATE DATABASE IF NOT EXISTS `transfer` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `transfer`;

-- Dumping structure for table transfer.transactions
CREATE TABLE IF NOT EXISTS `transactions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `viewable` bit(1) NOT NULL,
  `received_by_id` int(11) NOT NULL,
  `sent_from_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7tiw81amxnhk9xsqk796g4au9` (`received_by_id`),
  KEY `FKmr9n8yk378cp8d2k1gv1ff62m` (`sent_from_id`),
  CONSTRAINT `FK7tiw81amxnhk9xsqk796g4au9` FOREIGN KEY (`received_by_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKmr9n8yk378cp8d2k1gv1ff62m` FOREIGN KEY (`sent_from_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table transfer.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `current_balance` double NOT NULL,
  `login_id` int(11) NOT NULL,
  `user_information_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmm2rbqyaxv8qjsq4qw75ns59o` (`login_id`),
  KEY `FKlg3m5n1j0fao6906v562sen21` (`user_information_id`),
  CONSTRAINT `FKlg3m5n1j0fao6906v562sen21` FOREIGN KEY (`user_information_id`) REFERENCES `users_information` (`id`),
  CONSTRAINT `FKmm2rbqyaxv8qjsq4qw75ns59o` FOREIGN KEY (`login_id`) REFERENCES `users_login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table transfer.users_friends
CREATE TABLE IF NOT EXISTS `users_friends` (
  `user_id` int(11) NOT NULL,
  `friends_id` int(11) NOT NULL,
  UNIQUE KEY `UK_eslx46xq9voscm63qgdtr5c28` (`friends_id`),
  KEY `FKry5pun2eg852sbl2l50p236bo` (`user_id`),
  CONSTRAINT `FKo51ktjiheso8mkdd5n4pdf9f3` FOREIGN KEY (`friends_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKry5pun2eg852sbl2l50p236bo` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table transfer.users_information
CREATE TABLE IF NOT EXISTS `users_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `birth_date` date NOT NULL,
  `creation_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `hidden` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7804o5u1tgvr90bu5r804p257` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table transfer.users_login
CREATE TABLE IF NOT EXISTS `users_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `disabled` bit(1) NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `login_attempts` int(11) NOT NULL,
  `password` varchar(30) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hskren04wiw7uqnkiaisu7wf3` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table transfer.users_received_transactions
CREATE TABLE IF NOT EXISTS `users_received_transactions` (
  `user_id` int(11) NOT NULL,
  `received_transactions_id` int(11) NOT NULL,
  KEY `FKat76nqnjah23bgg634n6uf082` (`received_transactions_id`),
  KEY `FK9ccqi0nldb0b55cembvc86myu` (`user_id`),
  CONSTRAINT `FK9ccqi0nldb0b55cembvc86myu` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKat76nqnjah23bgg634n6uf082` FOREIGN KEY (`received_transactions_id`) REFERENCES `transactions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

-- Dumping structure for table transfer.users_sent_transactions
CREATE TABLE IF NOT EXISTS `users_sent_transactions` (
  `user_id` int(11) NOT NULL,
  `sent_transactions_id` int(11) NOT NULL,
  KEY `FK2kkacypt1n6n42fsqquno3d5r` (`sent_transactions_id`),
  KEY `FK5f50gfouav5qnvludget9736g` (`user_id`),
  CONSTRAINT `FK2kkacypt1n6n42fsqquno3d5r` FOREIGN KEY (`sent_transactions_id`) REFERENCES `transactions` (`id`),
  CONSTRAINT `FK5f50gfouav5qnvludget9736g` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
