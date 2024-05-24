package cn.edu.xcu.springboot.aop.service;

import cn.edu.xcu.springboot.aop.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 时空
* @description 针对表【t_user】的数据库操作Service
* @createDate 2024-05-21 21:00:57
*/
public interface UserService extends IService<User> {

    User findUserByName(String username);

    List<User> findUserList();

    User findUserById(Long id);
}
