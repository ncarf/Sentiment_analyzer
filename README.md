#Reddit en un stream
##Entrega 2: Prueba de concepto

Teniendo instalado Docker, ejecutar el siguiente comando en la terminal desde el directorio donde están los archivos descargados del repositorio:

````
docker-compose up --build
````

Se puede constatar que se han descargado los posts de reddit revisando las siguientes direcciones desde la carpeta data creada al ejecutar el comando:
- Posts en bruto: ./Sentiment_analyzer/data/2_kafka_posts/data/posts-0/00000000000000000000.log
- Posts con procesamiento de lenguaje: ./Sentiment_analyzer/data/4_kafka_proclenguaje/data/proclenguaje-0/00000000000000000000.log
- El análisis de sentimiento también es ejecutado con el archivo 5_python_analysis.py, pero este se podrá visualizar en la entrega 3 al guardar los datos en PostgreSQL.
