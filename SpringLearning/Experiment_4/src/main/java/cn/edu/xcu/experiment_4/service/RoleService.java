package cn.edu.xcu.experiment_4.service;

import cn.edu.xcu.experiment_4.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 时空
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2024-06-14 08:44:31
*/
public interface RoleService extends IService<Role> {

    List<Role> getRoleByUserId(Long userId);
}
