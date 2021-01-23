from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer
from kafka import KafkaConsumer
from psycopg2 import connect
import json


analyzer = SentimentIntensityAnalyzer()


# Postgresql connection settings

dbName = "sentiment_analyzer"
username = "postgres"
pwd = "secret"
address = "postgresql"

conn = connect(
    dbname = dbName,
    user = username,
    host = address,
    password = pwd
)

cursor = conn.cursor()

# Postgresql table creation

create_table_query = '''CREATE TABLE IF NOT EXISTS stored_posts
          (ID  VARCHAR  PRIMARY KEY  NOT NULL,
          MAYUSCULAS  INT  NOT NULL,
          MINUSCULAS  INT  NOT NULL,
          VOCALES  INT  NOT NULL,
          CONSONANTES  INT  NOT NULL,
          PALABRAS  INT  NOT NULL,
          STOPWORDS  INT  NOT NULL,
          POLARIDAD  REAL  NOT NULL); '''

# Execute a command: this creates a new table

cursor.execute(create_table_query)
conn.commit()
print("Table created successfully in PostgreSQL ")


# Kafka consumer connection settings

bootstrap_servers_consumer = ['4_kafka_proclenguaje:19092']
consumerTopicName = 'proclenguaje'

consumer = KafkaConsumer(consumerTopicName, bootstrap_servers = bootstrap_servers_consumer)

for message in consumer:


    # Obtain data from Json.
    messageJson = json.loads(message.value)
    pk = messageJson['Id']
    text = messageJson['Texto']
    capital = messageJson['Mayusculas']
    nonCapital = messageJson['Minusculas']
    vowels = messageJson['Vocales']
    consonants = messageJson['Consonantes']
    words = messageJson['Palabras']
    stopwords = messageJson['Stopwords']

    # Obtain analytics.
    rs = analyzer.polarity_scores(text)

    cursor.execute("INSERT INTO stored_posts (id, mayusculas, minusculas, vocales, consonantes, palabras, stopwords, polaridad) VALUES (%s, %s, %s, %s, %s, %s, %s, %s);",
    (pk, capital, nonCapital, vowels, consonants, words, stopwords, rs['compound']))

    conn.commit()


cursor.close()
conn.close()