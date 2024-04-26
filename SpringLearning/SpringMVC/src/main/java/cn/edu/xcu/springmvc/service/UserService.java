package cn.edu.xcu.springmvc.service;

import cn.edu.xcu.springmvc.entity.User;

public interface UserService {
    public User findUserById(Long id);
}
