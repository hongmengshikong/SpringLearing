package cn.edu.xcu.experiment_3.service;

import cn.edu.xcu.experiment_3.entity.Role;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 时空
* @description 针对表【t_role】的数据库操作Service
* @createDate 2024-05-28 21:12:39
*/
public interface RoleService extends IService<Role> {

    public int addRole(Role role);

    public int removeRole(Long id);

    public int updateRole(Role role);

    public Role getRoleById(Long id);

    IPage<Role> getRolePage(Long pageIndex, Long pageSize);
}
