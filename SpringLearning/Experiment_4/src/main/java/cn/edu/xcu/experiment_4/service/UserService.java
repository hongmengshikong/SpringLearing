package cn.edu.xcu.experiment_4.service;

import cn.edu.xcu.experiment_4.dto.UserAddDto;
import cn.edu.xcu.experiment_4.dto.UserChPassDto;
import cn.edu.xcu.experiment_4.entity.LoginUser;
import cn.edu.xcu.experiment_4.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 时空
* @description 针对表【sys_user(用户信息表)】的数据库操作Service
* @createDate 2024-06-14 08:44:58
*/
public interface UserService extends IService<User> {

    LoginUser getLoginUserByUsername(String username);

    int changeUserPassword(UserChPassDto user);

    int addUser(UserAddDto userDto);
}
