package jm.task.core.jdbc.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Util {
    private final DataSource dataSource;
    public static Util instance;
    private Util() {
        Properties properties = new Properties();
        String dbUrl;
        String dbUsername;
        String dbPassword;
        try (FileInputStream input = new FileInputStream("src/main/resources/application.properties"))
            {
            properties.load(input);
            dbUrl = properties.getProperty("db.url");
            dbUsername = properties.getProperty("db.username");
            dbPassword = properties.getProperty("db.password");
            } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
            } catch (IOException e) {
            throw new RuntimeException(e);
            }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUsername);
        config.setPassword(dbPassword);
        config.setMaximumPoolSize(10);
        this.dataSource = new HikariDataSource(config);
    }
    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }
        public Connection getConnection() throws SQLException {
            return dataSource.getConnection();
    }

    

}
