package distribuidos.repositories;

import distribuidos.models.Stored_posts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MongoPostRepo extends MongoRepository<Stored_posts, String> {

    public Optional<Stored_posts> findByRedditId(String reddit_id);
    public List<Stored_posts> findAll();
}
