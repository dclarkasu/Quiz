
DROP DATABASE IF EXISTS `quizdb`;

CREATE DATABASE IF NOT EXISTS `quizdb` DEFAULT CHARACTER SET utf8;

USE `quizdb`;

DROP TABLE IF EXISTS `Quiz`;

CREATE TABLE IF NOT EXISTS `Quiz` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `Question`;

CREATE TABLE IF NOT EXISTS `Question` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `quiz_id` INT(11) UNSIGNED NOT NULL,
  `questionText` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),-- declare keys at the end
  FOREIGN KEY (`quiz_id`)
  REFERENCES  `Quiz` (`id`) -- Refernces `theTable` (`thing in that table it's connected by`)
);

DROP TABLE IF EXISTS `Answer`;

CREATE TABLE IF NOT EXISTS `Answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` INT(11) UNSIGNED NOT NULL,
  `answerText` varchar(255) DEFAULT NULL,
  `isCorrect` BOOLEAN NOT NULL, -- true=1, false=0
  PRIMARY KEY (`id`),-- declare keys at the end
  FOREIGN KEY (`question_id`)
  REFERENCES `question` (`id`) -- Refernces `theTable` (`thing in that table it's connected by`)
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

-- From Conlin's dump file:
LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
INSERT INTO `quiz` VALUES (10,'States');
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` (id, quiz_id, questionText) VALUES
(1,10,'What is the smallest state in the US'),
(2,10,'What is the Colorado state bird?'),
(3,10,'What is the capital of Colorado?'),
(4,10,'What is the Colorado state flower?'),
(5,10,'What is the official state dance of Colorado?');

/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` (id, question_id, answerText, isCorrect) VALUES (1,1,'Deleware',0),
(2,1,'Rhode Island',1),
(3,1,'Texas',0),
(4,1,'Maine',0),
(5,2,'Lark bunting',1),
(6,2,'Northern mockingbird',0),
(7,2,'Brown pelican',0),
(8,2,'Willow ptarmigan',0),
(9,3,'Vail',0),
(10,3,'Denver',1),
(11,3,'Aspen',0),
(12,3,'Parker',0),
(13,4,'Carnation',0),
(14,4,'Rocky Mountain Columbine',1),
(15,4,'Rhododendron',0),
(16,4,'Saguaro',0),
(17,5,'Clogging',0),
(18,5,'Square Dance',1),
(19,5,'Lindy Hop',0),
(20,5,'Two step',0);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;
