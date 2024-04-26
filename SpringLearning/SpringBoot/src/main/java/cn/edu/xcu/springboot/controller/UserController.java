package cn.edu.xcu.springboot.controller;

import cn.edu.xcu.springboot.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

//@Controller
@RestController  //默认返回的都是json数据
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = {"/add", "/insert"},method = {RequestMethod.GET,RequestMethod.POST})
//    @ResponseBody  //返回json数据
    public String addUser() {
        return "添加⽤户成功";
    }

    @GetMapping(value = "/list")
    public String list(String username) {
        return "查询⽤户列表只允许get⽅法访问,username="+username;
    }

    @PutMapping(value = "/update")
    public String update() {
        return "更新⽤户信息，只允许put⽅法访问";
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "删除⽤户，只能通过delete⽅法访问";
    }

    @GetMapping(value = "/params", params = "id=1")
    public String findUserById(String id) {
        return "通过id获取⽤户信息，必须携带id参数，否则⽆法访问该⽅法。id=" + id;
    }

    @GetMapping("/{username}/{id}")
    public String getUser(@PathVariable("username") String username, @PathVariable("id") Long id) {
        return "restful⻛格请求,username=" + username + ",id=" + id;
    }

    @RequestMapping("/getUserId")
    public String getUserId(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model, ModelMap modelMap) {
        request.setAttribute("username","zhangsan");
        session.setAttribute("username","zhangsan");
        model.addAttribute("username","zhangsan");
        modelMap.addAttribute("username","zhangsan");
        return "通过HttpServletRequest获取参数,userId=" + request.getParameter("userId");
    }

    @RequestMapping("/getUsernameAndId")
    public String getUsernameAndId(@RequestParam(value = "name", required = false, defaultValue = "xcu") String username, Long id) {
        //如果通过注解@RequestParam设置绑定的参数名，前端传递过来的数据中如果没有该参数则抛出异常，可以添加参数required = false设置该参数不是必须，通过defaultValue设置默认值
        return "简单数据类型绑定，参数名必须和get或者post⽅法设置的参数名保持⼀致，如果不⼀致则需要通过注解 @RequestParam来进⾏绑定。username=" + username + ",id=" + id;
    }

    @PostMapping("/register")
    public String register(User user,@RequestParam("test")String test) {
        System.out.println(user);
        return "⽤户注册成功,用户信息user:"+user+",test"+test;
    }

    @PostMapping("/postUserIds")
    public String postUserIds(Long[] ids) {
        Arrays.stream(ids).forEach(System.out::println);
        return "postUserIds获取数组数据成功";
    }

    @PostMapping("/postUserList")
    public String postUserList(@RequestParam List<Long> ids) {
        ids.stream().forEach(System.out::println);
        return "postUserList获取数组数据成功";
    }

    @PostMapping("/addWithIdCard")
    public String addWithIdCard(User user) {
        System.out.println(user);
        return "添加复杂pojo数据类型成功";
    }

    @PostMapping("/addWithOrder")
    public String addWithOrder(User user) {
        System.out.println(user);
        return "获取数组数据成功";
    }

    @PostMapping("/addWithScores")
    public String addWithScores(User user) {
        System.out.println(user);
        return "获取⽤户信息包含成绩";
    }

    @PostMapping("addByJson")
    public String addUserByJson(@RequestBody User user) {
        System.out.println(user);
        return "Json数据绑定成功";
    }
}