package cn.edu.xcu.springboot.security.controller;

import cn.edu.xcu.springboot.security.dto.UserAddDto;
import cn.edu.xcu.springboot.security.dto.UserChPassDto;
import cn.edu.xcu.springboot.security.service.UserService;
import cn.edu.xcu.springboot.security.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("principal.username.equals(#user.username)or principal.username.equals('admin')")
    @PostMapping("/chpasswd")
    public R changePassword(@RequestBody UserChPassDto user){
        int result=userService.changeUserPassword(user);
        return R.ok();
    }
    @PreAuthorize("isAnonymous()or principal.username.equals('admin')")
    @PostMapping("/register")
    public R addUser(@RequestBody UserAddDto user){
        int result=userService.addUser(user);
        return result>0?R.ok():R.fail();
    }
}
