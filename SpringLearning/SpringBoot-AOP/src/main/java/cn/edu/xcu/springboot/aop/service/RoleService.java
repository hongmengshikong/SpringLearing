package cn.edu.xcu.springboot.aop.service;

import cn.edu.xcu.springboot.aop.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 时空
* @description 针对表【t_role】的数据库操作Service
* @createDate 2024-05-21 22:51:19
*/
public interface RoleService extends IService<Role> {
    public Role getRoleById(Long id);
    public int addRole(Role role);
    public int updateRole(Role role);
    public int removeRole(Long id);

    List<Role> getRoleList();
}
