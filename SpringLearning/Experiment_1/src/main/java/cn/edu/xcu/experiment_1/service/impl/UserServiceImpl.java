package cn.edu.xcu.experiment_1.service.impl;

import cn.edu.xcu.experiment_1.entity.Role;
import cn.edu.xcu.experiment_1.entity.User;
import cn.edu.xcu.experiment_1.entity.UserRole;
import cn.edu.xcu.experiment_1.mapper.RoleMapper;
import cn.edu.xcu.experiment_1.mapper.UserMapper;
import cn.edu.xcu.experiment_1.mapper.UserRoleMapper;
import cn.edu.xcu.experiment_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }
    @Transactional
    @Override
    public int saveUser(User user){
        int result = userMapper.saveUser(user);
        Role role =roleMapper.findRoleByName("admin");
        if (role != null) {
            UserRole userRole=new UserRole(null, user.getId(),role.getId());
            userRoleMapper.saveUserRole(userRole);
        }
        return result;
    }
    @Override
    public User findUserWithRoleById(Long id){
        User user=userMapper.findUserWithRoles(id);
        return user;
    }
    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
    @Transactional
    @Override
    public int removeUserById(Long id){
        userRoleMapper.removeUserRoleByUserId(id);
        int result=userMapper.removeUserById(id);
        return result;
    }
    @Override
    public List<User> findAllUsersWithCard(){
        return userMapper.findAllUsersWithCard();
    }
}