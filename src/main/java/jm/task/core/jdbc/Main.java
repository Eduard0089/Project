package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        List<User> users = new ArrayList<>();
        User user1 = new User("Name1", "LastName1", (byte)19);
        users.add(user1);
        User user2 = new User("Name2", "LastName2", (byte)20);
        users.add(user2);
        User user3 = new User("Name3", "LastName3", (byte)21);
        users.add(user3);
        User user4 = new User("Name4", "LastName4", (byte)22);
        users.add(user4);
        for (User user : users){
            userDao.saveUser(user.getName(), user.getLastName(), user.getAge());
        }
        List <User> users2 = new ArrayList<>();
        users2 = userDao.getAllUsers();
        for (User user : users2){
            System.out.println(user.toString());
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
