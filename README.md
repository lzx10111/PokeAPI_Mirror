# PokeAPI Mirror

Una sencilla aplicacion web basada en PokeAPI hecha en JAVA con el framework SpringBoot<br/>
<br/>
## Tabla de contenidos (En construcción...)

1. MySQL
   - Creando la base de datos con MySQL
     - Creando el esquema en MySQL
     - Creando las tablas en MySQL
       - Para PokeAPI
         - Analizando los recursos de PokeAPI
         - Las partes que se utilizaran de estos recursos
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

#### Analizando los recursos de PokeAPI

