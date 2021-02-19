FROM openjdk:8
COPY build/libs/sentiment_analyzer-0.0.1-SNAPSHOT.jar sentiment_analyzer.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sentiment_analyzer.jar"]