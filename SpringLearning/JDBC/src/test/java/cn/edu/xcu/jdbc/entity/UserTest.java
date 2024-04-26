package cn.edu.xcu.jdbc.entity;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class UserTest  {
    @Test
    public void testJdbc() throws SQLException {
        cn.edu.xcu.jdbc.entity.User user = UserDao.findUserById(2l);
        System.out.println(user);
    }
    @Test
    public void testFindAllUsers() throws SQLException {
        List<User> users = UserDao.findAllUsers();
        users.stream()
                .forEach(System.out::println);
    }

}