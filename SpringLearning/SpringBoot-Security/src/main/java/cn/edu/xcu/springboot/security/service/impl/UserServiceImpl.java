package cn.edu.xcu.springboot.security.service.impl;

import cn.edu.xcu.springboot.security.dto.UserAddDto;
import cn.edu.xcu.springboot.security.dto.UserChPassDto;
import cn.edu.xcu.springboot.security.entity.LoginUser;
import cn.edu.xcu.springboot.security.entity.Menu;
import cn.edu.xcu.springboot.security.entity.Role;
import cn.edu.xcu.springboot.security.service.MenuService;
import cn.edu.xcu.springboot.security.service.RoleService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xcu.springboot.security.entity.User;
import cn.edu.xcu.springboot.security.service.UserService;
import cn.edu.xcu.springboot.security.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 时空
* @description 针对表【sys_user(用户信息表)】的数据库操作Service实现
* @createDate 2024-06-04 14:50:44
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginUser getLoginUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);
        User user = baseMapper.selectOne(wrapper);
        if(ObjUtil.isNull(user)){
            throw new RuntimeException("用户名不存在");
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        List<Role> roles= roleService.getRoleByUserId(user.getUserId());
        if(CollUtil.isEmpty(roles)){
            loginUser.setRoles(new ArrayList<>());
            loginUser.setMenus(new ArrayList<>());
            loginUser.setPermissions(new ArrayList<>());
            return loginUser;
        }
        List<Menu> menus= menuService.getMenusByRoles(roles);
        if (CollUtil.isEmpty(menus)){
            loginUser.setMenus(new ArrayList<>());
            loginUser.setPermissions(new ArrayList<>());
            return loginUser;
        }
        List<String> permission = menus.stream().filter(menu -> StrUtil.isNotBlank(menu.getPerms()))
                .map(Menu::getPerms).collect(Collectors.toList());
        loginUser.setPermissions(permission);

        return loginUser;
    }

    @Override
    public int changeUserPassword(UserChPassDto user) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(User::getPassword,passwordEncoder.encode(user.getPassword()))
                .eq(User::getUserName,user.getUsername());
        return baseMapper.update(wrapper);
    }

    @Override
    public int addUser(UserAddDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return baseMapper.insert(user);
    }
}




