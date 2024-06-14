package cn.edu.xcu.experiment_4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xcu.experiment_4.entity.RoleMenu;
import cn.edu.xcu.experiment_4.service.RoleMenuService;
import cn.edu.xcu.experiment_4.mapper.RoleMenuMapper;
import org.springframework.stereotype.Service;

/**
* @author 时空
* @description 针对表【sys_role_menu(角色和菜单关联表)】的数据库操作Service实现
* @createDate 2024-06-14 08:44:53
*/
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu>
    implements RoleMenuService{

}




