from kafka import KafkaConsumer
from kafka import KafkaProducer
import json

# Consumer setup

bootstrap_servers_input = ['localhost:9091']

consumer = KafkaConsumer('reddit_posts', bootstrap_servers = bootstrap_servers_input)

# Producer setup

bootstrap_servers_output = ['localhost:9092']
topicName = 'filtered_posts'

producer = KafkaProducer(bootstrap_servers = bootstrap_servers_output)


for message in consumer:
    messageJson = json.loads(message.value)
    #redditJson = json.dumps({"Title": message.value.title, "Id": message.value.id, "Author": submission.author.name, "content": submission.selftext, "score": submission.score}).encode('utf-8')
    #producer.send(topicName, redditJson)

producer.flush()
