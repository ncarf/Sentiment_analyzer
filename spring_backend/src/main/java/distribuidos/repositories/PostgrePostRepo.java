package distribuidos.repositories;

import distribuidos.models.Post_stats;

import java.util.List;

public interface PostgrePostRepo {

    public Post_stats findByRedditId(String redditId);
    public List<Post_stats> findAll();
}
