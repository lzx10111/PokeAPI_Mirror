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
   - Creando las entidades

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

#### Url

```
spring.datasource.url=jdbc:mysql://localhost:port/databasename?serverTimezone=timezone
```
- port: El puerto que tiene la base de datos en este caso es **3306**.
- databasename: El nombre de la base de datos en este caso es **bd_pokemon**.
- timezone: La zona horaria que necesites en este caso es **America/Santiago**.

Reemplazando quedaria de la siguiente manera...
```
spring.datasource.url=jdbc:mysql://localhost:3306/bd_pokemon?serverTimezone=America/Santiago
```

#### Username

```
spring.datasource.username=username
```
- username: El nombre de usuario que tiene la base de datos en este caso es **root**.

```
spring.datasource.username=root
```

#### Password

```
spring.datasource.password=password
```
- password: El contraseña del usuario **root** que tiene la base de datos en este caso es **admin**.

```
spring.datasource.password=admin
```

#### Driver

```
spring.datasource.driver-class-name=drivername
```
- drivername: El nombre del driver en este caso es **com.mysql.cj.jdbc.Driver**.

```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

El resultado de todo esto se puede ver en el ya mencionado archivo [application.properties](src/main/resources/application.properties).

## Creando las entidades

Una entidad es un objeto que representa una tabla en una base de datos relacional. Estas ayudan a transferir datos entre las capas de la aplicacion.


### Porque usar una entidad en vez de un DTO?

La razon radica en que una entidad puede contener logica mientras que un DTO no. Con logica nos referimos a que una entidad puede tener por ejemplo metodos para validacion pero un DTO no.


### Que es una anotacion?

Una Anotación Java es una forma de añadir metadatos al código fuente Java que están disponibles para la aplicación en tiempo de ejecución o de compilación.


### La anotacion @Entity

Esta anotacion la colocaremos arriba de la clase para indicar que la clase es una entidad.

```java
@Entity
public class Pokemon {
   //el resto del codigo
}
```
### La anotacion @Column

Esta anotacion la colocaremos arriba de cada campo que represente una columna en la respectiva tabla.

```java
@Entity
public class Pokemon {
   @Column
   private Integer id;

   @Column
   private String name;
   
   @Column
   private Integer height;
   
   @Column
   private Integer weight;
   
   @Column
   private Integer base_experience;
   }
```

Se pueden especificar parametros para como el nombre de la columna.

```java
@Entity
public class Pokemon {
   @Column(name="id")
   private Integer id;

   @Column(name="name")
   private String name;
   
   @Column(name="height")
   private Integer height;
   
   @Column(name="weight")
   private Integer weight;
   
   @Column(name="base_experience")
   private Integer base_experience;
   }
```
Cuando el campo tiene el mismo nombre que la columna entonces no es necesario colocar el parametro **name**. Esto se puede ver ejemplificado en la clase [Pokemon](src/main/java/com/example/PokemonAPI/model/dto/Pokemon.java).

### La anotacion @Id

Esta anotacion la colocaremos arriba del campo que represente la **PRIMARY KEY** en la respectiva tabla. En este caso el campo **id** representa a la clave primaria de la tabla **pokemon**.

```java
@Entity
public class Pokemon {
   @Id
   @Column
   private Integer id;

   // El otro codigo no se muestra para darle protagonismo al campo id.
   }
```

