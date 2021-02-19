package distribuidos.Mappers;

import distribuidos.models.Post_stats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgrePostRowMapper implements RowMapper<Post_stats> {

    @Override
    public Post_stats mapRow(ResultSet rs, int arg1) throws SQLException {
        Post_stats post_stats = new Post_stats();
        post_stats.setRedditId(rs.getString("id"));
        post_stats.setUpperCase(rs.getInt("mayusculas"));
        post_stats.setLowerCase(rs.getInt("minusculas"));
        post_stats.setVowels(rs.getInt("vocales"));
        post_stats.setConsonants(rs.getInt("consonantes"));
        post_stats.setWords(rs.getInt("palabras"));
        post_stats.setStopwords(rs.getInt("stopwords"));
        post_stats.setPolarity(rs.getFloat("polaridad"));

        return post_stats;
    }
}
