package cn.edu.xcu.springboot.aop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xcu.springboot.aop.entity.User;
import cn.edu.xcu.springboot.aop.service.UserService;
import cn.edu.xcu.springboot.aop.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 时空
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2024-05-21 21:00:57
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public User findUserByName(String username) {
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();

        wrapper.eq(User::getUsername,username);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List<User> findUserList() {

        return baseMapper.selectList(null);
    }

    @Override
    public User findUserById(Long id) {
        return baseMapper.selectById(id);
    }
}




