package cn.edu.xcu.experiment_2.service;

import cn.edu.xcu.experiment_2.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    User findUserById_1(Long id);
    int saveUser(User user);
    User findUserById(Long id);
    int deleteUserById(Long id);
    IPage<User> getUserPage(Long pageIndex, Long pagesize);
}
