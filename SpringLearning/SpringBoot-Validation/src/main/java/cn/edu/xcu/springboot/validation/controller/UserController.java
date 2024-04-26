package cn.edu.xcu.springboot.validation.controller;

import cn.edu.xcu.springboot.validation.entity.User;
import cn.edu.xcu.springboot.validation.utils.ValidGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController  //默认返回的都是json数据
@RequestMapping("/user")
public class UserController {
//    @PostMapping("/save")
//    public String saveUser(@Validated @RequestBody User user){
//        System.out.println(user);
//        return "保存⽤户信息成功，"+user;
//    }
    @PostMapping("/save")
//    @ResponseBody
    public String saveUser(@Validated({ValidGroup.Insert.class}) @RequestBody User user){
        return "保存⽤户信息成功，"+user;
    }
    @PostMapping("/update")
//    @ResponseBody
    public String updateUser(@Validated({ValidGroup.UPdate.class}) @RequestBody User user){
        return "更新⽤户信息成功，"+user;
    }
    @GetMapping(value = "/params")
//    @ResponseBody
    public String findUserById(@NotNull(message = "参数id不能为空") String id){
        return "通过id获取⽤户信息，必须携带id参数，否则⽆法访问该⽅法。id="+id;
    }
}
