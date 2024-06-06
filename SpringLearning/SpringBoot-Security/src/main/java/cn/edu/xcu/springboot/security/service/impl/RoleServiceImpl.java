package cn.edu.xcu.springboot.security.service.impl;

import cn.edu.xcu.springboot.security.entity.UserRole;
import cn.edu.xcu.springboot.security.service.UserRoleService;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xcu.springboot.security.entity.Role;
import cn.edu.xcu.springboot.security.service.RoleService;
import cn.edu.xcu.springboot.security.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 时空
* @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
* @createDate 2024-06-04 14:50:33
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<Role> getRoleByUserId(Long userId) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(UserRole::getRoleId)
                .eq(UserRole::getUserId, userId);
        List<Long> roleIds = userRoleService.listObjs(wrapper, obj -> (long) obj);
        if (CollUtil.isNotEmpty(roleIds)){
            List<Role> roles = baseMapper.selectBatchIds(roleIds);
            return roles;
        }
        return null;
    }
}




