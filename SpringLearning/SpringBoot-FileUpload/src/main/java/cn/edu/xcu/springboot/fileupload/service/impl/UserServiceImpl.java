package cn.edu.xcu.springboot.fileupload.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xcu.springboot.fileupload.entity.User;
import cn.edu.xcu.springboot.fileupload.service.UserService;
import cn.edu.xcu.springboot.fileupload.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 时空
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2024-05-12 16:00:57
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




