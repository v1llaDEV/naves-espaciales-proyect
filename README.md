# naves-espaciales-proyect

## Para dockerizar y desplegar la aplicación habría que realizar los siguientes pasos:

1. Situarse en la raíz del proyecto
2. Tener instalado maven y una versión correctamente configurada de java 17
3. Ejecutar el comando 'mvn clean install' para generar el artefacto
4. Iniciar docker en la computadora
5. Ejecutar el comando 'docker build -t naves-espaciales-proyect .' para buildear la imagen de la aplicación
6. Ejecutar el comando 'docker run -p 8080:8080 naves-espaciales-proyect' para crear un contenedor a partir de la imágen
7. Después de algunos segundos ya se habría levantado la aplicación y se podría consultar por ejemplo a través de:
   1. A través de postman: http://localhost:8080/swagger-ui/index.html
   2. A través de un endpoint de actuator: http://localhost:8080/actuator/health

## Enunciado

Importante: Se debe utilizar la última versión LTS de Java, Spring Boot y de cualquier librería utilizada en el proyecto.
Desarrollar, utilizando Maven, Spring Boot, y Java, una API que permita hacer un mantenimiento CRUD de naves espaciales de series y películas.

Este mantenimiento debe permitir:

- Consultar todas las naves utilizando paginación.
- Consultar una única nave por id.
- Consultar todas las naves que contienen, en su nombre, el valor de un parámetro enviado en la petición. Por ejemplo, si enviamos “wing” devolverá “x-wing”.
- Crear una nueva nave.
- Modificar una nave.
- Eliminar una nave.
- Test unitario de como mínimo de una clase.
- Desarrollar un @Aspect que añada una línea de log cuando nos piden una nave con un id negativo.
- Gestión centralizada de excepciones.
- Utilizar cachés de algún tipo.

Puntos a tener en cuenta:

- Las naves se deben guardar en una base de datos. Puede ser, por ejemplo, H2 en memoria.
- La prueba se debe presentar en un repositorio de Git. No hace falta que esté publicado. Se puede enviar comprimido en un único archivo.

Puntos opcionales de mejora:

- Utilizar alguna librería que facilite el mantenimiento de los scripts DDL de base de datos.
- Test de integración.
- Presentar la aplicación dockerizada.
- Documentación de la API.
- Seguridad del API.
- Implementar algún consumer/producer para algún broker (Rabbit, Kafka, etc).