package com.example.demo.config;

package com.example.demo.config;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Profile("heroku")
public class HerokuDatabaseConfiguration {

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        String databaseUrl = System.getenv("JAWSDB_MARIA_URL");
        if (databaseUrl == null) {
            databaseUrl = System.getenv("CLEARDB_DATABASE_URL");
        }
        if (databaseUrl == null) {
            throw new RuntimeException("Veritabanı URL'si bulunamadı. Lütfen JawsDB veya ClearDB eklentisini kurun.");
        }

        URI dbUri = new URI(databaseUrl);

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();

        return DataSourceBuilder.create()
                .url(dbUrl)
                .username(username)
                .password(password)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
