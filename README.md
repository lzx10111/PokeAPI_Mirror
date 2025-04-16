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
Algunos criterios que utilize para la creacion de la base de datos
- Los nombres de las tablas estaran en singular y en minuscula.
- Todas las columnas relacionadas con numeros enteros seran **int**. Al ser una aplicacion simple y con valores muy limitados es mas que suficiente con que sean **int**.
- Todas las columnas relacionadas con numeros enteros seran **SIGNED**. En este caso no hay numeros negativos pero segun lei colocar columnas con **UNSIGNED** provoca complicaciones si se quiere emigrar la base de datos.

### Para PokeAPI
Para esta aplicacion web solo usaremos un limitado rango de valores del objeto json que nos da PokeAPI especificamente los siguientes:

- abilities
- ability
- cries
- official_art_work
- other
- pokemon
- sprites

Estos seran tambien los nombres de las tablas  






