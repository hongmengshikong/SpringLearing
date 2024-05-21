package cn.edu.xcu.springboot.session.service;

import cn.edu.xcu.springboot.session.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 时空
* @description 针对表【t_user】的数据库操作Service
* @createDate 2024-05-12 20:40:45
*/
public interface UserService extends IService<User> {

    User login(String username, String passwd);
}
