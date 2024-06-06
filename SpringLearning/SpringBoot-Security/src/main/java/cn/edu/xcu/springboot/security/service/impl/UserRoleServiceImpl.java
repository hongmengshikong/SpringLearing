package cn.edu.xcu.springboot.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xcu.springboot.security.entity.UserRole;
import cn.edu.xcu.springboot.security.service.UserRoleService;
import cn.edu.xcu.springboot.security.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 时空
* @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Service实现
* @createDate 2024-06-04 14:50:51
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




