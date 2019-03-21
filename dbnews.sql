drop database if exists db_news;
create database db_news CHARACTER SET utf8mb4;
use db_news;


DROP TABLE IF EXISTS `News_Collector`;

CREATE table `News_Collector` (
`id` int(100) unsigned NOT NULL AUTO_INCREMENT,
`source` varchar(100) NOT NULL,
`url` varchar(1000) NOT NULL,
`title` varchar(500) NOT NULL,
`date` varchar(50) NOT NULL,
PRIMARY KEY (`id`)
);

