package cn.edu.xcu.experiment_2.service.impl;

import cn.edu.xcu.experiment_2.entity.IdCard;
import cn.edu.xcu.experiment_2.entity.Role;
import cn.edu.xcu.experiment_2.entity.User;
import cn.edu.xcu.experiment_2.entity.UserRole;
import cn.edu.xcu.experiment_2.mapper.IdCardMapper;
import cn.edu.xcu.experiment_2.mapper.RoleMapper;
import cn.edu.xcu.experiment_2.mapper.UserMapper;
import cn.edu.xcu.experiment_2.mapper.UserRoleMapper;
import cn.edu.xcu.experiment_2.service.UserService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IdCardMapper idCardMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User findUserById_1(Long id) {
        return userMapper.findUserById_1(id);
    }
    @Transactional
    @Override
    public int saveUser(User user){
        idCardMapper.insert(user.getIdCard());
        user.setCardId(user.getIdCard().getId());
        int result=baseMapper.insert(user);
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Role::getId, Role::getRoleName, Role::getRoleDesc)
                .eq(Role::getRoleName, "admin");
        Role role = roleMapper.selectOne(wrapper);
        UserRole userRole=new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(user.getId());
        userRoleMapper.insert(userRole);
        return result;
    }
    @Override
    public User findUserById(Long id){
        User user=baseMapper.selectById(id);
        IdCard idCard=idCardMapper.selectById(user.getCardId());
        user.setIdCard(idCard);
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(UserRole::getRoleId)
                .eq(UserRole::getUserId, user.getId());
        List<Long> roleIds = userRoleMapper.selectObjs(wrapper).stream().map(o -> (long) o).collect(Collectors.toList());
        List<Role> roles = roleMapper.selectBatchIds(roleIds);
        user.setRoles(roles);
        return user;
    }
    @Override
    public int deleteUserById(Long id) {
        //删除用户，先删除用户身份信息，然后删除用户角色关联，最后删涂用户
        User user = baseMapper.selectById(id);
        idCardMapper.deleteById(user.getCardId());
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, id);
        userRoleMapper.delete(wrapper);
        return baseMapper.deleteById(id);
    }
    @Override
    public IPage<User> getUserPage(Long pageIndex, Long pageSize) {
        Page<User> page = new Page<>(pageIndex, pageSize);
        baseMapper.selectPage(page, null);
        page.getRecords().stream().forEach(user -> {
            if (ObjUtil.isNotEmpty(user.getCardId())) {
                IdCard idcard = idCardMapper.selectById(user.getCardId());
                user.setIdCard(idcard);
            }
            LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
            wrapper.select(UserRole::getRoleId)
                    .eq(UserRole::getUserId, user.getId());
            List<Long> roleIds = userRoleMapper.selectObjs(wrapper).stream().map(o -> (long) o).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(roleIds)) {
                List<Role> roles = roleMapper.selectBatchIds(roleIds);
                user.setRoles(roles);
            }
        });
        return page;
    }

}

