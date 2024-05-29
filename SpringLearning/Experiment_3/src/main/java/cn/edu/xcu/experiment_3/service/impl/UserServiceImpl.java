package cn.edu.xcu.experiment_3.service.impl;

import cn.edu.xcu.experiment_3.aop.OperationLog;
import cn.edu.xcu.experiment_3.aop.OperatorType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xcu.experiment_3.entity.User;
import cn.edu.xcu.experiment_3.service.UserService;
import cn.edu.xcu.experiment_3.mapper.UserMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
* @author 时空
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2024-05-28 21:12:23
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @CacheEvict(value = "userPage",allEntries = true)
    @OperationLog(value = "添加用户",operatorType = OperatorType.INSERT,desc ="添加用户信息")
    @Override
    public int addUser(User user) {
        return baseMapper.insert(user);
    }


    @Caching(evict = {@CacheEvict(value = "user",key = "#id"),@CacheEvict(value = "userPage",allEntries = true)})
    @OperationLog(value = "删除用户",operatorType = OperatorType.DELETE,desc ="删除用户信息")
    @Override
    public int removeUser(Long id) {
        return baseMapper.deleteById(id);
    }

    @Caching(evict = {@CacheEvict(value = "user",key = "#user.id"),@CacheEvict(value = "userPage",allEntries = true)})
    @OperationLog(value = "修改用户",operatorType = OperatorType.UPDATE,desc ="修改用户信息")
    @Override
    public int updateUser(User user) {
        return baseMapper.updateById(user);
    }

    @Cacheable(value = "user",key = "#id",unless = "#result == null")
    @OperationLog(value = "查询用户",desc ="根据用户ID查询用户信息")
    @Override
    public User findUserById(Long id) {
        return baseMapper.selectById(id);
    }

    @Cacheable(value = "userPage",keyGenerator = "keyGenerator",unless = "#result == null||#result.records.isEmpty()")
    @OperationLog(value = "page",desc ="使用page查询用户信息")
    @Override
    public IPage<User> getUserPage(Long pageIndex, Long pageSize) {
        Page<User> page = new Page<>(pageIndex, pageSize);
        return baseMapper.selectPage(page,null);
    }
}




