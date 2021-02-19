package distribuidos.db_config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import distribuidos.repositories.MongoPostRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = MongoPostRepo.class)
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName()
    {
        return "sentiment_analyzer";
    }

    @Override
    public MongoClient mongoClient()
    {
        ConnectionString connectionString = new ConnectionString("mongodb://mongodb:27017/sentiment_analyzer");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .credential(MongoCredential.createCredential("mongo", "admin", "secret".toCharArray()))
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), "sentiment_analyzer");
    }

}
