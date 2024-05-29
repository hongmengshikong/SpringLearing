package cn.edu.xcu.experiment_3.controller;

import cn.edu.xcu.experiment_3.entity.Role;
import cn.edu.xcu.experiment_3.entity.User;
import cn.edu.xcu.experiment_3.service.RoleService;
import cn.edu.xcu.experiment_3.utils.R;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public R addRole(@RequestBody Role role){
        int result = roleService.addRole(role);
        if (result>0){
            return R.ok("添加角色信息成功");
        }else {
            return R.fail("添加角色信息失败");
        }
    }

    @DeleteMapping("/{id}")
    public R removeRoleById(@PathVariable Long id){
        int result = roleService.removeRole(id);
        if (result>0){
            return R.ok("删除角色信息成功");
        }else {
            return R.fail("删除角色信息失败");
        }
    }

    @PutMapping("/update")
    public R updateRole(@RequestBody Role role){
        int result = roleService.updateRole(role);
        if (result>0){
            return R.ok("更新角色信息成功");
        }else {
            return R.fail("更新角色信息失败");
        }
    }

    @GetMapping("/{id}")
    public R getRoleById(@PathVariable Long id){
        Role role = roleService.getRoleById(id);
        if (ObjUtil.isNotNull(role)){
            return R.ok(role);
        }else {
            return R.fail("当前角色Id不存在");
        }
    }
    @GetMapping("/page/{pageIndex}")
    @ResponseBody
    public IPage<Role> getRolePage(@PathVariable Long pageIndex) {
        IPage<Role> page = roleService.getRolePage(pageIndex,5L);
        return page;
    }

}
