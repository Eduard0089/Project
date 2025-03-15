package jm.task.core.jdbc.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final DataSource dataSource;

    public Util() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/my_database");
        config.setUsername("root");
        config.setPassword("Edik0089");
        config.setMaximumPoolSize(10);
        this.dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {

        return dataSource.getConnection();

//        String url = "jdbc:mysql://localhost:3306/my_database";
//        String user = "root";
//        String password = "Edik0089";
//
//            try (Connection connection = DriverManager.getConnection(url, user, password)) {
//                return connection;
//            } catch (SQLException e) {
//                System.err.println("Ошибка подключения" + e);
//            }
//        return null;
    }

}
