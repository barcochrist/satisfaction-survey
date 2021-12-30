# Encuesta de Satisfacción

API Rest que expone recursos en formato JSON que dá cumplimiento a los siguientes requerimientos:

## Requerimientos

Utilizando Spring Boot y Java se debe realizar una API Rest que cumpla con los siguientes requerimientos:

Un restaurante requiere un sistema que permita listar una encuesta a sus clientes para recibir el feedback de sus servicios; 
la encuesta debe de tener un listado de preguntas abiertas y de selección múltiple con sus respectivas respuestas.

### Criterios de aceptación
- Se debe exponer un servicio que permita listar una encuesta con sus respectivas preguntas
- Se debe exponer un servicio que permita registrar la encuesta
- Implementar pruebas unitarias
- La encuesta puede tener una o varias preguntas de tipo abierta o selección múltiple
- No implementar ningún tipo de seguridad
- Definir modelo entidad relación de la base de datos

## Tecnologías

El proyecto está desarrollado con las siguientes tecnologías:

- Java
- Gradle
- Spring Boot
- PostgreSQL
- Git

## Documentación
Se incluye en el repositorio un directorio con el nombre "documentation" que contiene todos 
los componentes que ayudan a comprender el funcionamiento y la forma en que fue construida 
la aplicación, entre esos tenemos:

- Modelo Entidad Relación
- Archivo de Postman con todos los Endpoints y ejemplos de ejecución

También se incluye una serie de Scripts que ayudan a configurar la base de datos necesaria 
para el proyecto. Estos Scripts se encuentran dentro del directorio `database` dentro del proyecto.

## Instalación

A continuación se muestra una guía de como ejecutar el proyecto en un ambiente local.

### Requisitos

- Java 11 instalado
- Código fuente del proyecto
- Base de datos PostgreSQL
- IDE (entorno de desarrollo)

Se puede descargar la JDK Open Source desde las siguientes fuentes:

- [OpenJDK](https://openjdk.java.net/)
- [Amazon Corretto](https://aws.amazon.com/es/corretto/)

### Abrir proyecto en IDE
A continuación detallo el paso a paso para abrir el proyecto en un IDE:

1. Descargar el proyecto o clonar el proyecto en la maquina 
2. En el IDE de preferencia abrir el proyecto
3. Dejar que el IDE indexe y compile el proyecto

### Configurar base de datos
A continuación muestro un paso a paso de como ejecutar la base de datos en un ambiente local:
1. Instalar y configurar correctamente una instancia de PostgreSQL
2. Crear la base de datos. Para esto ejecutamos el script que se encuentra en el archivo `database.sql` dentro del proyecto
3. Crear el esquema. Para esto ejecutamos el script que se encuentra en el archivo `schema.sql` dentro del proyecto
4. Si necesitas registros de prueba o iniciales se debe ejecutar el script que se encuentra en el archivo `insert.sql` dentro del proyecto

### ¿Cómo ejecutar el proyecto?

Dentro del directorio principal del proyecto ejecutar lo siguiente: `./gradlew bootRun` con los
siguientes argumentos:

- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`

Ejemplo del comando completo:

```
./gradlew bootRun --args='--spring.datasource.url=jdbc:postgresql://localhost:5432/satisfaction_survey --spring.datasource.username=postgres --spring.datasource.password=postgres'
```

Es posible tambien definir los datos de conexión a la base de datos en el archivo de configuración `application.yml` para no enviarlos como argumentos en el comando anterior.