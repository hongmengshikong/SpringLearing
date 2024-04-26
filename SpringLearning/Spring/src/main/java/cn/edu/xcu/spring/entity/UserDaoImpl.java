package cn.edu.xcu.spring.entity;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private List<User> users;
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    @Override
    public List<User> findAllUsers() {
        return this.users;
    }
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public User findUserById(Long id) {
        return user;
    }
}