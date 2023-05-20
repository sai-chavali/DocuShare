package ai.typeface.documentShare.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration("sql-connect")
public class SQLConfig {
    @Value("${azure.sql.admin.url}")
    private String url;

    @Value("${azure.sql.admin.username}")
    private String username;

    @Value("${azure.sql.admin.key}")
    private String password;

    @Bean
    @Primary
    public DataSource getDataSource() {
        return DataSourceBuilder.create().url(url).username(username).password(password).build();
    }

    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
