package cn.edu.xcu.springboot.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xcu.springboot.security.entity.RoleMenu;
import cn.edu.xcu.springboot.security.service.RoleMenuService;
import cn.edu.xcu.springboot.security.mapper.RoleMenuMapper;
import org.springframework.stereotype.Service;

/**
* @author 时空
* @description 针对表【sys_role_menu(角色和菜单关联表)】的数据库操作Service实现
* @createDate 2024-06-04 14:51:20
*/
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu>
    implements RoleMenuService{

}




