package cn.edu.xcu.springboot.security.service;

import cn.edu.xcu.springboot.security.dto.UserAddDto;
import cn.edu.xcu.springboot.security.dto.UserChPassDto;
import cn.edu.xcu.springboot.security.entity.LoginUser;
import cn.edu.xcu.springboot.security.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 时空
* @description 针对表【sys_user(用户信息表)】的数据库操作Service
* @createDate 2024-06-04 14:50:44
*/
public interface UserService extends IService<User> {

    LoginUser getLoginUserByUsername(String username);

    int changeUserPassword(UserChPassDto user);

    int addUser(UserAddDto user);
}
