package cn.edu.xcu.springboot.session.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xcu.springboot.session.entity.User;
import cn.edu.xcu.springboot.session.service.UserService;
import cn.edu.xcu.springboot.session.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 时空
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2024-05-12 20:40:45
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public User login(String username, String passwd) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username)
                .eq(User::getPassword, passwd);
        return baseMapper.selectOne(wrapper);
    }
}




