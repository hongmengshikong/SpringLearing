package cn.edu.xcu.experiment_3.service;

import cn.edu.xcu.experiment_3.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 时空
* @description 针对表【t_user】的数据库操作Service
* @createDate 2024-05-28 21:12:23
*/
public interface UserService extends IService<User> {

    public int addUser(User user);

    public int removeUser(Long id);

    public int updateUser(User user);

    public User findUserById(Long id);

    IPage<User> getUserPage(Long pageIndex, Long pageSize);
}
