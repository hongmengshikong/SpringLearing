package cn.edu.xcu.springboot.security.service.impl;

import cn.edu.xcu.springboot.security.entity.Role;
import cn.edu.xcu.springboot.security.entity.RoleMenu;
import cn.edu.xcu.springboot.security.service.RoleMenuService;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xcu.springboot.security.entity.Menu;
import cn.edu.xcu.springboot.security.service.MenuService;
import cn.edu.xcu.springboot.security.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author 时空
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
* @createDate 2024-06-04 14:50:57
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<Menu> getMenusByRoles(List<Role> roles) {
        if(roles.stream().anyMatch(role -> "admin".equals(role.getRoleKey()))){
            return baseMapper.selectList(null);
        }
        List<Long> roleIds = roles.stream().map(Role::getRoleId).collect(Collectors.toList());
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(RoleMenu::getMenuId)
                .in(RoleMenu::getRoleId, roleIds);
        List<Long> menuIds = roleMenuService.listObjs(wrapper, obj -> (long) obj);
        if (CollUtil.isNotEmpty(menuIds)) {
            return baseMapper.selectBatchIds(menuIds);
        }

        return null;
    }
}




