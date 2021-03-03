package distribuidos.services;

import distribuidos.models.Complete_post;
import distribuidos.models.Post_stats;
import distribuidos.models.Stored_posts;
import distribuidos.repositories.MongoPostRepo;
import distribuidos.repositories.PostgrePostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostService {

    @Autowired
    private MongoPostRepo mongoPostRepo;

    @Resource
    private PostgrePostRepo postgrePostRepo;

    @Autowired
    public PostService(MongoPostRepo mongoPostRepo, PostgrePostRepo postgrePostRepo)
    {
        this.mongoPostRepo = mongoPostRepo;
        this.postgrePostRepo = postgrePostRepo;
    }

    @GetMapping("/data/all")
    public List<Stored_posts> findAllMongoPost()
    {
        return mongoPostRepo.findAll();
    }

    @GetMapping("/data/{id}")
    public Stored_posts findMongoPostByRedditId(@PathVariable("id") String id)
    {
        List<Stored_posts> mongoList = mongoPostRepo.findAll();
        Stored_posts empty = new Stored_posts();

        for(Stored_posts mongoPost: mongoList)
        {
            if (mongoPost.getRedditId().equals(id))
            {
                return mongoPost;
            }
        }

        return empty;
    }

    @GetMapping("/stats/all")
    public List<Post_stats> findAllPostgrePost()
    {
        return postgrePostRepo.findAll();
    }

    @GetMapping("/stats/{id}")
    public Post_stats findPostgrePostByRedditId(@PathVariable("id") String id)
    {
        return postgrePostRepo.findByRedditId(id);
    }

    @GetMapping("/all")
    public List<Complete_post> findAllCompletePost()
    {
        List<Stored_posts> mongoList = mongoPostRepo.findAll();
        List<Post_stats> postgreList = postgrePostRepo.findAll();
        List<Complete_post> completeList = new ArrayList<>();

        for(Post_stats postgrePost : postgreList)
        {
            for(Stored_posts mongoPost: mongoList)
            {
                if (mongoPost.getRedditId().equals(postgrePost.getRedditId()))
                {
                    Complete_post complete_post = new Complete_post();
                    complete_post.build(postgrePost, mongoPost);
                    completeList.add(complete_post);
                }
            }
        }

        return completeList;
    }

    @GetMapping("/{id}")
    public Complete_post findCompletePostByRedditId(@PathVariable String id)
    {
        List<Stored_posts> mongoList = mongoPostRepo.findAll();
        Post_stats postgrePost = postgrePostRepo.findByRedditId(id);
        Complete_post complete_post = new Complete_post();

        for(Stored_posts mongoPost: mongoList)
        {
            if (mongoPost.getRedditId().equals(id))
            {
                complete_post.build(postgrePost, mongoPost);
            }
        }

        return complete_post;
    }
}
