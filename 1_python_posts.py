# Imports

import praw
import json
import pymongo
from kafka import KafkaProducer

# Reddit connection settings

client_id = "H38kFWwxAhzgog"
client_secret = "jeqLjICH9n5bPTxtQAspIHp2K79TQQ"
user_agent = "webapp:H38kFWwxAhzgog:v0.1 (by u/Alkeindem)"

reddit = praw.Reddit(
     client_id = client_id,
     client_secret = client_secret,
     user_agent = user_agent
 )

# Kafka connection settings

bootstrap_servers = ['2_kafka_posts:19091']
topicName = 'posts'

producer = KafkaProducer(bootstrap_servers = bootstrap_servers)

# Mongo connection settings
try:
	client = pymongo.MongoClient('mongodb', 27017, username = "mongo", password = "secret")
	client.server_info()
except err:
	print(err)

db = client.sentiment_analyzer
coll = db.stored_posts


# Actual connection

for submission in reddit.subreddit("changemyview").stream.submissions():
	try:
		redditJson = json.dumps({"Title": submission.title, "Id": submission.id, "Author": submission.author.name, "content": submission.selftext, "score": submission.score, "over_18": submission.over_18, "link_flair_text": submission.link_flair_text}).encode('utf-8')
		producer.send(topicName, redditJson)

		post = {"redditId": submission.id, "texto": submission.selftext, "autor": submission.author.name, "puntaje": submission.score, "flairs": submission.link_flair_text}
		x = coll.insert_one(post)
	except:
		print("Fetch failed.")

producer.flush()
