package cn.edu.xcu.experiment_4.service;

import cn.edu.xcu.experiment_4.entity.Menu;
import cn.edu.xcu.experiment_4.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 时空
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2024-06-14 08:44:41
*/
public interface MenuService extends IService<Menu> {

    List<Menu> getMenusByRoles(List<Role> roles);
}
