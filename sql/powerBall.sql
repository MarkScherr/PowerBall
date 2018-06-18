CREATE DATABASE powerball;

use powerball;
CREATE TABLE `numbers` (
  `pickId` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `timePicked` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `stateName` char(2) NOT NULL,
  `firstName` char(100) NOT NULL,
  `lastName` char(100) NOT NULL,
  `eMail` varchar(100) NOT NULL,
  `ball1` int(11) NOT NULL,
  `ball2` int(11) NOT NULL,
  `ball3` int(11) NOT NULL,
  `ball4` int(11) NOT NULL,
  `ball5` int(11) NOT NULL,
  `powerBall` int(11) DEFAULT NULL,
  `quickPick` bit(1) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL
  
);

CREATE TABLE `numbersdrawn` (
  `drawId` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `timePicked` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ball1` int(11) NOT NULL,
  `ball2` int(11) NOT NULL,
  `ball3` int(11) NOT NULL,
  `ball4` int(11) NOT NULL,
  `ball5` int(11) NOT NULL,
  `powerBall` int(11) DEFAULT NULL
);
