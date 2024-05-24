package cn.edu.xcu.springboot.aop.service.impl;

import cn.edu.xcu.springboot.aop.aop.Log;
import cn.edu.xcu.springboot.aop.aop.OperatorType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xcu.springboot.aop.entity.Role;
import cn.edu.xcu.springboot.aop.service.RoleService;
import cn.edu.xcu.springboot.aop.mapper.RoleMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 时空
* @description 针对表【t_role】的数据库操作Service实现
* @createDate 2024-05-21 22:51:19
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Cacheable(value = "role",key = "#id",unless = "#result == null")
    @Log(value = "查询角色",desc =   "根据角色ID查询角色信息")
    @Override
    public Role getRoleById(Long id) {
        return baseMapper.selectById(id);
    }

    @CacheEvict(value = "roleList",allEntries = true)
    @Log(value = "添加角色",operatorType = OperatorType.INSERT,desc =   "添加新的角色信息")
    @Override
    public int addRole(Role role) {
        return baseMapper.insert(role);
    }

    @Caching(evict = {@CacheEvict(value = "role",key = "#role.id"),@CacheEvict(value = "roleList",allEntries = true)})
    @Override
    public int updateRole(Role role) {
        return baseMapper.updateById(role);
    }

    @Caching(evict = {@CacheEvict(value = "role",key = "#id"),@CacheEvict(value = "roleList",allEntries = true)})
    @Override
    public int removeRole(Long id) {
        return baseMapper.deleteById(id);
    }

    @Cacheable(value = "roleList",keyGenerator = "keyGenerator",unless = "#result == null||#result.size() == 0")
    @Override
    public List<Role> getRoleList() {
        return baseMapper.selectList(null);
    }
}




