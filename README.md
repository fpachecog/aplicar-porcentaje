# aplicar-porcentaje

- ## Endpoints
La documentación de la API se puede encontrar en el siguiente link: https://studio-ws.apicur.io/sharing/2cd4931d-2e29-47b3-a3a0-1fa46be97bc7
o en el archivo [swagger.yaml](swagger.yaml)

Para el desarrollo de la API se utilizó:
    - Base de datos: Postgres (en Docker)
    - Cache: Redis (en Docker). Quise utilizar Redis porque es distribuido y se puede escalar horizontalmente. No está limitado sólo al nodo local
    - Los POJO fueron creados a partir de json schemas, utilizando un plugin de maven: jsonschema2pojo-maven-plugin

## Ejecutar Api
- Para iniciar la Api, se debe ejecutar el comando:
```
 docker-compose up --build
```
- Al iniciar el servicio se ejecutan los archivos schema.sql.
    - schema.sql: Contiene un script que crea la tabla operaciones, que es donde se almacenan los datos de cada llamada a la api "/api/v1/calculos/porcentaje"

![Descargar Colección para Postman ](CalculoPorcentajes.postman_collection.json)