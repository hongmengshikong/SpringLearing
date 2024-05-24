package cn.edu.xcu.springboot.aop.controller;

import cn.edu.xcu.springboot.aop.entity.Role;
import cn.edu.xcu.springboot.aop.service.RoleService;
import cn.edu.xcu.springboot.aop.utils.R;
import cn.hutool.core.util.ObjUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("/{id}")
    public R getRoleById(@PathVariable Long id){
        Role role = roleService.getRoleById(id);
        if (ObjUtil.isNotNull(role)){
            return R.ok(role);
        }else {
            return R.fail("当前角色Id不存在");
        }
    }
    @PostMapping("")
    public R addRole(@RequestBody Role role){
        int result = roleService.addRole(role);
        if (result>0){
            return R.ok("添加角色信息成功");
        }else {
            return R.fail("添加角色信息失败");
        }
    }
    @PutMapping("")
    public R updateRole(@RequestBody Role role){
        int result = roleService.updateRole(role);
        if (result>0){
            return R.ok("更新角色信息成功");
        }else {
            return R.fail("更新角色信息失败");
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
    @GetMapping("list")
    public R roleList(){
        List<Role> roles = roleService.getRoleList();
        if (ObjUtil.isNotEmpty(roles)){
            return R.ok(roles);
        }else {
            return R.fail("当前角色列表为空");
        }
    }
}
