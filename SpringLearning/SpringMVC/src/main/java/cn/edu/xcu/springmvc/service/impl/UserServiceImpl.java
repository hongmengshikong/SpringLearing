package cn.edu.xcu.springmvc.service.impl;
import cn.edu.xcu.springmvc.entity.User;
import cn.edu.xcu.springmvc.mapper.UserMapper;
import cn.edu.xcu.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }
}
