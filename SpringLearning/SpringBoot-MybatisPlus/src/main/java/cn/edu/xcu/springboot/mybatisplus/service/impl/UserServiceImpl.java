package cn.edu.xcu.springboot.mybatisplus.service.impl;

import cn.edu.xcu.springboot.mybatisplus.entity.User;
import cn.edu.xcu.springboot.mybatisplus.mapper.UserMapper;
import cn.edu.xcu.springboot.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.security.Provider;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
