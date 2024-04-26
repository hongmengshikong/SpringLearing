package cn.edu.xcu.experiment_1.mapper;

import cn.edu.xcu.experiment_1.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper {
    public void saveUserRole(UserRole userRole) ;

    public void removeUserRoleByUserId(Long id);
}
