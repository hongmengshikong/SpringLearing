package cn.edu.xcu.springboot.security.service;

import cn.edu.xcu.springboot.security.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 时空
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2024-06-04 14:50:33
*/
public interface RoleService extends IService<Role> {

    List<Role> getRoleByUserId(Long userId);
}
