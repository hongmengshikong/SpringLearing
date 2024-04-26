package cn.edu.xcu.springmvc.mapper;

import cn.edu.xcu.springmvc.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public  interface UserMapper {

    public User findUserById(Long id);
}