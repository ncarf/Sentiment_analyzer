FROM python:3.7

WORKDIR /usr/src/app

RUN pip install praw && \
	pip install kafka-python && \
	pip install vaderSentiment && \
	pip install psycopg2 && \
	pip install pymongo