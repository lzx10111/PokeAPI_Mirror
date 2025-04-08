CREATE SCHEMA `bd_pokemon`;

USE bd_pokemon;

CREATE TABLE `pokemon` (
  `id` int NOT NULL,
  `base_experience` int,
  `height` int,
  `name` varchar(255),
  `weight` int,
  PRIMARY KEY (`id`)
);

CREATE TABLE `abilities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_hidden` bit(1),
  `slot` int,
  `pokemon_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_abilities_pokemon_id` (`pokemon_id`),
  CONSTRAINT `FK_abilities_pokemon` FOREIGN KEY (`pokemon_id`) REFERENCES `pokemon` (`id`) ON DELETE CASCADE
);

CREATE TABLE `ability` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255),
  `url` varchar(255),
  `abilities_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ability_abilities_id` (`abilities_id`),
  CONSTRAINT `FK_ability_abilities` FOREIGN KEY (`abilities_id`) REFERENCES `abilities` (`id`) ON DELETE CASCADE
);

CREATE TABLE `cries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `latest` varchar(255),
  `legacy` varchar(255),
  `pokemon_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cries_pokemon_id` (`pokemon_id`),
  CONSTRAINT `FK_cries_pokemon` FOREIGN KEY (`pokemon_id`) REFERENCES `pokemon` (`id`) ON DELETE CASCADE
);

CREATE TABLE `sprites` (
  `id` int NOT NULL AUTO_INCREMENT,
  `back_default` varchar(255),
  `back_female` varchar(255),
  `back_shiny` varchar(255),
  `back_shiny_female` varchar(255),
  `front_default` varchar(255),
  `front_female` varchar(255),
  `front_shiny` varchar(255),
  `front_shiny_female` varchar(255),
  `pokemon_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sprites_pokemon_id` (`pokemon_id`),
  CONSTRAINT `FK_sprites_pokemon` FOREIGN KEY (`pokemon_id`) REFERENCES `pokemon` (`id`) ON DELETE CASCADE
);

CREATE TABLE `other` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sprites_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_other_sprites_id` (`sprites_id`),
  CONSTRAINT `FK_other_sprites` FOREIGN KEY (`sprites_id`) REFERENCES `sprites` (`id`) ON DELETE CASCADE
);

CREATE TABLE `official_art_work` (
  `id` int NOT NULL AUTO_INCREMENT,
  `front_default` varchar(255),
  `front_shiny` varchar(255),
  `other_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_official_art_work_other_id` (`other_id`),
  CONSTRAINT `FK_official_art_work_other` FOREIGN KEY (`other_id`) REFERENCES `other` (`id`) ON DELETE CASCADE
);

CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_birth` date NOT NULL,
  `date_created` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user_roles` (
  `user_id` int NOT NULL,
  `roles` tinyint NOT NULL,
  KEY `IDX_user_roles_user_id` (`user_id`),
  CONSTRAINT `FK_user_roles_usuarios` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `CHK_user_roles_roles` CHECK ((`roles` between 0 and 1))
);

CREATE TABLE `favoritos` (
  `pokemon_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`pokemon_id`,`user_id`)
);
