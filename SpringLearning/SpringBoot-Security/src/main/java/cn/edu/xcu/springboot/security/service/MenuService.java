package cn.edu.xcu.springboot.security.service;

import cn.edu.xcu.springboot.security.entity.Menu;
import cn.edu.xcu.springboot.security.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 时空
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2024-06-04 14:50:57
*/
public interface MenuService extends IService<Menu> {

    List<Menu> getMenusByRoles(List<Role> roles);
}
