package cn.edu.xcu.spring.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    public void findUser(){
        User2 user = userService.findUserById(1l);
        System.out.println(user);
    }
}