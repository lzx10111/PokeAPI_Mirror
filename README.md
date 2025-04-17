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
   - Configuracion Inicial
     - Configurando la conexion a la base de datos

## Creando el esquema en MySQL<br/>
La parte mas simple de esta seccion... solo se necesita un nombre para el esquema en este caso lo llamare **bd_pokemon**.
```sql
CREATE SCHEMA `bd_pokemon`;
```
MySQL no tiene una convencion de nomenclatura asi que yo usare mi propia logica...
Lo importante es la consistencia por ejemplo yo empiezo los nombre de mis esquemas con **bd_** donde **bd** es de base de datos.
## Creando las tablas en MySQL<br/>
Algunos criterios que utilize para la creacion de la base de datos
- Los nombres de las tablas estaran en minuscula.
- Todas las columnas con datos numericos seran:
  - **int**: Para esta aplicacion es mas que suficiente el rango que nos brinda este tipo de dato.
  - **SIGNED** (default): En esta aplicacion solo utilizaremos numeros positivos por lo que podria usar **UNSIGNED** pero lei que colocar columnas con **UNSIGNED** provoca complicaciones si se quiere emigrar la base de datos.
- Las **CONSTRAINT** tendran una forma de llamarse especifica dependiendo de cual sea la **CONSTRAINT**. En el caso esta aplicacion sera de la siguiente forma.

  - **FOREIGN KEY**: FK_TableName_TableSource
  - **UNIQUE KEY**: UK_TableName_ColumnName
  - **INDEX KEY**: IDX_TableName_ColumnName
  - **CHECK KEY**: CHK_TableName_ColumnName

### Para PokeAPI
Para esta aplicacion web solo usaremos un limitado rango de valores del objeto JSON que nos da PokeAPI especificamente los siguientes:

- abilities
- ability
- cries
- official_art_work
- other
- pokemon
- sprites

Estos seran tambien los nombres de las tablas  

## Configuracion Inicial

### Configurando la conexion a la base de datos

Esto se puede hacer con una clase de java o en el archivo [application.properties](src/main/resources/application.properties). En mi caso usaré el archivo porque lo encuentro más fácil e intuitivo.

<ins><h4>Url</h4></ins>

```
spring.datasource.url=jdbc:mysql://localhost:port/databasename?serverTimezone=timezone
```
- port: El puerto que tiene la base de datos en este caso **3306**.
- databasename: El nombre de la base de datos en este caso **bd_pokemon**.
- timezone: El nombre de la base de datos en este caso **America/Santiago**.

Reemplazando quedaria de la siguiente manera...
```
spring.datasource.url=jdbc:mysql://localhost:3306/bd_pokemon?serverTimezone=America/Santiago
```

<ins><h4>Username</h4></ins>

```
spring.datasource.username=username
```
- username: El nombre de usuario que tiene la base de datos en este caso **root**.

```
spring.datasource.username=root
```

<ins><h4>Password</h4></ins>

```
spring.datasource.password=password
```
- password: El contraseña del usuario **root** que tiene la base de datos en este caso **admin**.

```
spring.datasource.password=admin
```





