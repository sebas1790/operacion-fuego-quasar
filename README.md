# Operación Fuego de Quasar
Repositorio que contiene el Challenge de MeLi

By: Juan Sebastian Molina

# Aplicación

Consta de 3 Niveles los cuales fueron expuestos mediante APIRest, la información de satelites y mensajes fue persistida en MongoDB.

# Despliegue

Se maneja Build automático mediante Acciones de GitHub, el cual obtiene el artefacto (Imagen Docker) de la aplicación y se publica a la nube de Docker mediante Docker Hub, posteriormente en una instancia EC2 de AWS, se ejecuta (de forma manual) un docker-compose, el cual descarga y ejecuta las imagenes para el despliegue de la aplicación, asegurándose así siempre tener la ultima versión de la aplicación.

```YML
version: '3.1'

services:
  melimongo:
    image: mongo
    container_name: "melimongo"
    ports:
      - 27017:27017
  mongo-express:
    links:
      - melimongo
    image: mongo-express
    restart: always
    ports:
      - 8081:8081
    environment:
      - ME_CONFIG_MONGODB_URL= "mongodb://melimongo:27017"
  operacion-fuego-quasar:
    image: sebas170/operacion-fuego-quasar:1.0
    container_name: "operacion-fuego-quasar"
    ports:
      - 8090:8090
    links:
      - melimongo
```

# Nivel 1
Definición de los métodos GetLocation y GetMessage

# Nivel 2 y Nivel 3
ApiREST desplegado sobre Spring Boot Framework para la exposición y consumo de dicha Api.

# Swagger de la Api

http://ec2-18-224-18-153.us-east-2.compute.amazonaws.com:8090/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

# Mongo Express para visualización de la DB

http://ec2-18-224-18-153.us-east-2.compute.amazonaws.com:8081/

# Consumo de los servicios expuestos

POST ec2-18-224-18-153.us-east-2.compute.amazonaws.com:8090/topsecret/

-> cURL para consumir el servicio

```cURL
curl --location --request POST 'ec2-18-224-18-153.us-east-2.compute.amazonaws.com:8090/topsecret/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "satellites": [
        {
            "name": "kenobi",
            "distance": 100.0,
            "message": [
                "este",
                "",
                "",
                "mensaje",
                ""
            ]
        },
        {
            "name": "skywalker",
            "distance": 115.5,
            "message": [
                "",
                "es",
                "",
                "",
                "secreto"
            ]
        },
        {
            "name": "sato",
            "distance": 142.7,
            "message": [
                "este",
                "",
                "un",
                "",
                ""
            ]
        }
    ]
}'
```

POST ec2-18-224-18-153.us-east-2.compute.amazonaws.com:8090/topsecret_split/{satelite_name}

-> cURL para consumir el servicio

```cURL
curl --location --request POST 'ec2-18-224-18-153.us-east-2.compute.amazonaws.com:8090/topsecret_split/sato' \
--header 'Content-Type: application/json' \
--data-raw '{
    "distance": 142.7,
    "message": [
        "",
        "",
        "un",
        "",
        ""
    ]
}'
```

GET ec2-18-224-18-153.us-east-2.compute.amazonaws.com:8090/topsecret_split/

-> cURL para consumir el servicio

```cURL
curl --location --request GET 'ec2-18-224-18-153.us-east-2.compute.amazonaws.com:8090/topsecret_split/'
```
