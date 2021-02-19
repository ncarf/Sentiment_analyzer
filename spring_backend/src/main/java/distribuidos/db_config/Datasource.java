package distribuidos.db_config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class Datasource {

    @Value("org.postgresql.Driver")
    private String driverClass;

    @Value("jdbc:postgresql://postgresql/sentiment_analyzer")
    private String url;

    @Value("postgres")
    private String username;

    @Value("secret")
    private String password;

    @Bean
    public DataSource dataSource(){
        System.out.println(driverClass+" "+ url+" "+username+" "+password);
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setUrl(url);
        source.setUsername(username);
        source.setPassword(password);
        return source;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource());
        return namedParameterJdbcTemplate;

    }
}
