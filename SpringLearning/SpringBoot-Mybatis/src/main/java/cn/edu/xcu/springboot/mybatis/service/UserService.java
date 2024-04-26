package cn.edu.xcu.springboot.mybatis.service;

import cn.edu.xcu.springboot.mybatis.entity.User;
import cn.edu.xcu.springboot.mybatis.mapper.UserMapper;

public interface UserService {
    public User findUserById(Long id);

}
