package cn.edu.xcu.experiment_2.controller;

import cn.edu.xcu.experiment_2.entity.User;
import cn.edu.xcu.experiment_2.service.UserService;
import cn.edu.xcu.experiment_2.utils.ValidGroup;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    @ResponseBody
    public User findUserById_1(@PathVariable Long id){
        return userService.findUserById_1(id);
    }
    @PostMapping("/add")
    @ResponseBody
    public String addUser(@Validated({ValidGroup.Insert.class}) User user){
        System.out.println(user);

        int result=userService.saveUser(user);
        if(result>0){
            return "添加用户成功";
        }else {
            return "添加用户失败";
        }
    }
    @GetMapping("/query/{id}")
    public String findUsersById(@PathVariable Long id, Model model){
        User user=userService.findUserById(id);
        System.out.println(user);
        model.addAttribute("user",user);
        return "userDetails";
    }
    @PostMapping("/update")
    @ResponseBody
    public String updateUser(@Validated ({ValidGroup.UPdate.class})@RequestBody User user){
        boolean result=userService.updateById(user);
        if(result){
            return "更新用户成功";
        }else {
            return "更新用户失败";
        }
    }
    @GetMapping("/del/{id}")
    @ResponseBody
    public String delUser(@PathVariable Long id){
        int result = userService.deleteUserById(id);
        if(result>0){
            return "删除用户成功";
        }else {
            return "删除用户失败";
        }
    }
    @GetMapping("/page/{pageIndex}/{pageSize}")
    @ResponseBody
    public IPage<User> getUserPage(@PathVariable Long pageIndex, @PathVariable Long pageSize) {
        IPage<User> page = userService.getUserPage(pageIndex, pageSize);
        return page;
    }
}
