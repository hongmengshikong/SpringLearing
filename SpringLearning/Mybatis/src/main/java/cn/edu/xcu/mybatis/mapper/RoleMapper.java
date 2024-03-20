package cn.edu.xcu.mybatis.mapper;

import cn.edu.xcu.mybatis.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface RoleMapper {
    public List<Role> findRolesByUserId(@Param("userId") Long userId);
}
