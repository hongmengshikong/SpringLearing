package cn.edu.xcu.springmvc.controller;

import cn.edu.xcu.springmvc.entity.User;
import cn.edu.xcu.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") Long id){
        User user = userService.findUserById(id);
        return user;
    }
    @GetMapping("/byId")
    public String getUserByIdRequestParam(@RequestParam("id")Long id, Model model){
        User user = userService.findUserById(id);
        model.addAttribute("user",user);
        return "user";
    }
}

