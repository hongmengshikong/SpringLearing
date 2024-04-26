package cn.edu.xcu.experiment_1.controller;

import cn.edu.xcu.experiment_1.entity.User;
import cn.edu.xcu.experiment_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/user")
public  class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") Long id){
        User user = userService.findUserById(id);
        return user;
    }
    @PostMapping("/add")
    @ResponseBody
    public String addUser(User user){
        int result=userService.saveUser(user);
        if(result>0){
            return "添加用户成功";
        }else {
            return "添加用户失败";
        }
    }
    @GetMapping("/query/{id}")
    @ResponseBody
    public User findUsersWithRoles(@PathVariable("id")Long id){
        User user=userService.findUserWithRoleById(id);
        return user;
    }
//    @ModelAttribute
    @PostMapping("/update")
    @ResponseBody
    public String updateUser(User user){
        System.out.println(user);
        int result=userService.updateUser(user);
        if(result>0){
            return "更新用户成功";
        }else {
            return "更新用户失败";
        }
    }


    @GetMapping("/del/{id}")
    @ResponseBody
    public String delUser(@PathVariable("id")Long id){
        int result = userService.removeUserById(id);
        if(result>0){
            return "删除用户成功";
        }else {
            return "删除用户失败";
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<User> findAllUsers(){
        List<User> users=userService.findAllUsersWithCard();
        return users;
    }
}
