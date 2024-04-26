package cn.edu.xcu.spring.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao2 userDao;
    @Override
    public User2 findUserById(Long id) {
        return userDao.findUserById(id);
    }
}
