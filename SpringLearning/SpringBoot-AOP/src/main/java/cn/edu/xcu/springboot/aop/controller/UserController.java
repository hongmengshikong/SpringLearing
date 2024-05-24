package cn.edu.xcu.springboot.aop.controller;

import cn.edu.xcu.springboot.aop.entity.User;
import cn.edu.xcu.springboot.aop.service.UserService;
import cn.edu.xcu.springboot.aop.utils.R;
import cn.hutool.core.util.ObjUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/name/{username}")
    public R getUserByName(@PathVariable String username){
        User user=userService.findUserByName(username);
        if (ObjUtil.isNotNull(user)){
            return R.ok(user);

        }else {
            return R.fail("当前用户名不存在");
        }
    }
    @GetMapping("/list")
    public R getUserList(){
        List<User> users=userService.findUserList();
        if (ObjUtil.isNotEmpty(users)){
            return R.ok(users);

        }else {
            return R.fail("当前用户名不存在");
        }
    }
    @GetMapping("/id/{id}")
    public R getUserById(@PathVariable Long id){
        User user=userService.findUserById(id);
        if (ObjUtil.isNotNull(user)){
            return R.ok(user);

        }else {
            return R.fail("当前用户名不存在");
        }
    }
}
