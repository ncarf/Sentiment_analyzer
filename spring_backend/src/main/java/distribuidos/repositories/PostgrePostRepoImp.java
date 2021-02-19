package distribuidos.repositories;

import distribuidos.Mappers.PostgrePostRowMapper;
import distribuidos.models.Post_stats;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostgrePostRepoImp implements PostgrePostRepo{

    public PostgrePostRepoImp(NamedParameterJdbcTemplate template)
    {
        this.template = template;
    }

    private NamedParameterJdbcTemplate template;

    @Override
    public Post_stats findByRedditId(String redditId)
    {
        final String sql = "SELECT * FROM stored_posts WHERE id = :redditId";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("redditId", redditId);

        return template.queryForObject(sql, param, new PostgrePostRowMapper());
    }

    @Override
    public List<Post_stats> findAll()
    {
        return template.query("SELECT * FROM stored_posts", new PostgrePostRowMapper());
    }


}
