# Functions

def esVocal(char):
    vocales = 'aeiouAEIOU'
    return char in vocales

def esConsonante(char):
    consonantes = 'qwrtypsdfghjklñzxcvbnmQWRTYPSDFGHJKLÑZXCVBNM'
    return char in consonantes

def esStopword(palabra):
    stopwords = ["a", "about", "above", "across", "after", "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also","although","always","am","among", "amongst", "amoungst", "amount",  "an", "and", "another", "any","anyhow","anyone","anything","anyway", "anywhere", "are", "around", "as",  "at", "back","be","became", "because","become","becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom","but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven","else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own","part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "thick", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves", "the"]
    return palabra in stopwords

# Imports

import praw
import json
from kafka import KafkaConsumer
from kafka import KafkaProducer

# Kafka consumer connection settings

bootstrap_servers_consumer = ['2_kafka_posts:19091']
consumerTopicName = 'posts'

consumer = KafkaConsumer(consumerTopicName, bootstrap_servers = bootstrap_servers_consumer)

# Kafka producer connection settings

bootstrap_servers_producer = ['4_kafka_proclenguaje:19092']
producerTopicName = 'proclenguaje'

producer = KafkaProducer(bootstrap_servers = bootstrap_servers_producer)

# Actual connection

for message in consumer:

    messageJson = json.loads(message.value)
    text = messageJson['content']

    cant_mayusculas = 0
    cant_minusculas = 0
    cant_vocales = 0
    cant_consonantes = 0

    for caracter in text:
        if(caracter.isupper()):
            cant_mayusculas+=1
        if(caracter.islower()):
            cant_minusculas+=1
        if(esVocal(caracter) == True):
            cant_vocales+=1
        if(esConsonante(caracter) == True):
            cant_consonantes+=1

    palabras = text.split(' ')
    cant_palabras = 0
    cant_stopwords = 0

    for palabra in palabras:
        cant_palabras+=1
        if(esStopword(palabra) == True):
            cant_stopwords+=1

    proclenguajeJson = json.dumps({"Id": messageJson['Id'], "Texto": text, "Mayusculas": cant_mayusculas, "Minusculas": cant_minusculas, "Vocales": cant_vocales, "Consonantes": cant_consonantes, "Palabras": cant_palabras, "Stopwords": cant_stopwords}).encode('utf-8')
    producer.send(producerTopicName, proclenguajeJson)

producer.flush()