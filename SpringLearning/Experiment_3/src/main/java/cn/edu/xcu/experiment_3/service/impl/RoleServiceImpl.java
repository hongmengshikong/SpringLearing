package cn.edu.xcu.experiment_3.service.impl;

import cn.edu.xcu.experiment_3.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xcu.experiment_3.entity.Role;
import cn.edu.xcu.experiment_3.service.RoleService;
import cn.edu.xcu.experiment_3.mapper.RoleMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
* @author 时空
* @description 针对表【t_role】的数据库操作Service实现
* @createDate 2024-05-28 21:12:39
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{
    @CacheEvict(value = "rolePage",allEntries = true)
    @Override
    public int addRole(Role role) {
        return baseMapper.insert(role);
    }
    @Caching(evict = {@CacheEvict(value = "role",key = "#id"),@CacheEvict(value = "rolePage",allEntries = true)})
    @Override
    public int removeRole(Long id) {
        return baseMapper.deleteById(id);
    }
    @Caching(evict = {@CacheEvict(value = "role",key = "#role.id"),@CacheEvict(value = "rolePage",allEntries = true)})
    @Override
    public int updateRole(Role role) {
        return baseMapper.updateById(role);
    }
    @Cacheable(value = "role",key = "#id",unless = "#result == null")
    @Override
    public Role getRoleById(Long id) {
        return baseMapper.selectById(id);
    }
    @Cacheable(value = "rolePage",keyGenerator = "keyGenerator",unless = "#result == null||#result.records.isEmpty()")
    @Override
    public IPage<Role> getRolePage(Long pageIndex, Long pageSize) {
        Page<Role> page = new Page<>(pageIndex, pageSize);
        baseMapper.selectPage(page, null);
        return page;
    }
}




