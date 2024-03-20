package cn.edu.xcu.mybatis.service;

import cn.edu.xcu.mybatis.entity.User;

public interface UserService {
    public User findUserById(Long id);
}
