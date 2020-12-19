# Imports

import praw
import json
from kafka import KafkaProducer
import pymongo

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

# MongoDB connection

myclient = pymongo.MongoClient("mongodb://127.0.0.1:27017/")
mydb = myclient["reddit"]
mycol = mydb["posts"]

# Actual connection

for submission in reddit.subreddit("changemyview").stream.submissions():
	try:
		redditPythonObject = {"Title": submission.title, "Id": submission.id, "Author": submission.author.name, "content": submission.selftext, "score": submission.score, "over_18": submission.over_18, "link_flair_text": submission.link_flair_text}
		redditJson = json.dumps(redditPythonObject).encode('utf-8')
		producer.send(topicName, redditJson)
		mongoSend = mycol.insert_one(redditPythonObject)
	except:
		print("Fetch failed.")

producer.flush()
