package cn.edu.xcu.spring.entity;

import java.util.List;

public interface UserDao {
//    public User findUserById(Long id);
public User findUserById(Long id);
    public List<User> findAllUsers();
}
