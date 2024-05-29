package cn.edu.xcu.experiment_3.controller;

import cn.edu.xcu.experiment_3.entity.User;
import cn.edu.xcu.experiment_3.service.UserService;
import cn.edu.xcu.experiment_3.utils.R;
import cn.edu.xcu.experiment_3.utils.ValidGroup;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/add")
    @ResponseBody
    public R addUser(@Validated({ValidGroup.Insert.class}) User user) {
        int result =userService.addUser(user);
        if (result>0){
            return R.ok("添加用户信息成功");
        }else {
            return R.fail("添加用户信息失败");
        }
    }
    @DeleteMapping("/{id}")
    public R deleteUser(@PathVariable Long id) {
        int result =userService.removeUser(id);
        if (result>0){
            return R.ok("删除用户信息成功");
        }else {
            return R.fail("删除用户信息失败");
        }
    }
    @PutMapping("/update")
    public R updateUser(@RequestBody User user) {
        int result=userService.updateUser(user);
        if (result>0){
            return R.ok("更新用户信息成功");
        }else {
            return R.fail("更新用户信息失败");
        }
    }

    @GetMapping("/{id}")
    public R findUsersById(@PathVariable Long id) {
        User user=userService.findUserById(id);
        if (ObjUtil.isNotNull(user)){
            return R.ok(user);
        }else {
            return R.fail("当前用户Id不存在");
        }
    }
    @GetMapping("/page/{pageIndex}")
    @ResponseBody
    public IPage<User> getUserPage(@PathVariable Long pageIndex) {
        IPage<User> page = userService.getUserPage(pageIndex,5L);
        return page;
    }

}
