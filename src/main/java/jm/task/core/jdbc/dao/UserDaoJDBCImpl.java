package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = Util.getInstance();

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "lastName VARCHAR(50) NOT NULL," +
                "age TINYINT NOT NULL" +
                ")";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
                statement.execute(sql);
                System.out.println("Таблица 'users' создана!");

        } catch (SQLException e) {
            System.err.println("Ошибка при создании таблицы" + e);
        }


    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection connection = util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Таблица 'users' удалена!");
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении таблицы" + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age );
            preparedStatement.executeUpdate();
            System.out.println("Пользователь " + name + " добавлен в таблицу");
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении пользователя" + e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с ID " + id + " удален из таблицы");
        } catch (SQLException e) {
            System.err.println("Ошибка удаления полльзователя по ID" + e);
        }
    }

    public List<User> getAllUsers() {
        List <User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Не удалось получить данные из таблицы" + e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute(sql);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.err.println("Не удалось очистить таблицу" + e);
        }
    }
}
