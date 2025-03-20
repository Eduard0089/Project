package jm.task.core.jdbc.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Util {
    String dbUrl;
    String dbUsername;
    String dbPassword;
    private final DataSource dataSource;
    public static Util instance;
    HikariConfig config = new HikariConfig();
    private Util() {
        Properties properties = new Properties();
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
        this.config = config;
        dataSource = new HikariDataSource(config);
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
    public static SessionFactory getSessionFactory(){
        SessionFactory sessionFactory;
        Util util = new Util();
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");

            configuration.addAnnotatedClass(jm.task.core.jdbc.model.User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .applySetting("hibernate.connection.datasource", util.dataSource)
                    .build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
