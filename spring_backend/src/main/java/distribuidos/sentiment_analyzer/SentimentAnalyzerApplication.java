package distribuidos.sentiment_analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("distribuidos.*")
@SpringBootApplication
public class SentimentAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentimentAnalyzerApplication.class, args);
    }

}
