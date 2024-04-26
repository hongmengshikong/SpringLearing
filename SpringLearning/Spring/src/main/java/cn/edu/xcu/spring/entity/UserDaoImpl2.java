package cn.edu.xcu.spring.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl2 implements UserDao2 {
    @Autowired
    private User2 user;
    @Override
    public User2 findUserById(Long id) {
        return this.user;
    }
}