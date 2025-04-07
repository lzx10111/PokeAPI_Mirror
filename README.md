# PokeAPI Mirror

Una sencilla aplicacion web basada en PokeAPI hecha en JAVA con el framework SpringBoot<br/>
<br/>
## Tabla de contenidos (En construcción...)

1. MySQL
   - Creando la base de datos con MySQL
     - Creando el esquema en MySQL
     - Creando las tablas en MySQL
       - Para PokeAPI
       - Para Usuarios
2. SpringBoot
   - Creando la base de datos con SpringBoot

## Creando el esquema en MySQL<br/>
La parte mas simple de esta seccion... solo se necesita un nombre para el esquema en este caso lo llamare **bd_pokemon**.
```sql
CREATE SCHEMA `bd_pokemon`;
```
MySQL no tiene una convencion de nomenclatura asi que yo usare mi propia logica...
Lo importante es la consistencia por ejemplo yo empiezo los nombre de mis esquemas con **bd_** donde **bd** es de base de datos.
## Creando las tablas en MySQL<br/>

### Para PokeAPI
Para esta aplicacion web solo usaremos un limitado rango de valores del objeto json que nos da PokeAPI especificamente los siguientes:

- abilities
- ability
- cries
- official_art_work
- other
- pokemon
- sprites

```sql
CREATE TABLE `abilities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_hidden` bit(1) NOT NULL,
  `slot` int NOT NULL,
  `pokemon_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_abilities_pokemon_id` (`pokemon_id`),
  CONSTRAINT `FK_abilities_pokemon` FOREIGN KEY (`pokemon_id`) REFERENCES `pokemon` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```






