package cn.edu.xcu.experiment_1.mapper;


import cn.edu.xcu.experiment_1.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {

    public Role findRoleByName(String roleName);
}
