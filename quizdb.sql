
DROP DATABASE IF EXISTS `quizdb`;

CREATE DATABASE IF NOT EXISTS `quizdb` DEFAULT CHARACTER SET utf8;

USE `quizdb`;

DROP TABLE IF EXISTS `Quiz`;

CREATE TABLE IF NOT EXISTS `Quiz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

START TRANSACTION;

USE `quizdb`;
INSERT INTO `Quiz` (`name`)
VALUES ('Quiz #1');
INSERT INTO `Quiz` (`name`)
VALUES ('Animal Quiz');
INSERT INTO `Quiz` (`name`)
VALUES ('Space Jam Trivia');

COMMIT;
