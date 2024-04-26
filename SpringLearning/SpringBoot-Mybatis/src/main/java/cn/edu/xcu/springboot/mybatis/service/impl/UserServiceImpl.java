package cn.edu.xcu.springboot.mybatis.service.impl;

import cn.edu.xcu.springboot.mybatis.entity.User;
import cn.edu.xcu.springboot.mybatis.mapper.UserMapper;
import cn.edu.xcu.springboot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserById(Long id){
        return userMapper.findUserById(id);
    }
}
