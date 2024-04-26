package cn.edu.xcu.springboot.mybatis.controller;

import cn.edu.xcu.springboot.mybatis.entity.User;
import cn.edu.xcu.springboot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController  //默认返回的都是json数据
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }
}
