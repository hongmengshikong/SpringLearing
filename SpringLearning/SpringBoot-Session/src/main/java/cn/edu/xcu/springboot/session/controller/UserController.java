package cn.edu.xcu.springboot.session.controller;

import cn.edu.xcu.springboot.session.entity.User;
import cn.edu.xcu.springboot.session.service.UserService;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/c1")
    @ResponseBody
    public String c1(HttpServletResponse response){
        Cookie cookie = new Cookie("username", "zhangsan");
        cookie.setDomain("localhost");
        cookie.setPath("/");
//        cookie.setMaxAge(120);
        response.addCookie(cookie);
        return "设置cookie成功";

    }
    @GetMapping("/c2")
    @ResponseBody
    public String c2(HttpServletRequest req){

        Cookie[] cookies = req.getCookies();
        if(ArrayUtil.isNotEmpty(cookies)){
            for (Cookie c : cookies){
                if(c.getName().equals("username")){
                    return "获取cookie成功,key:"+c.getName()+",value:"+c.getValue();
                }
            }
        }

        return "获取cookie失败,当前没有设置cookie";

    }
    @GetMapping("/s1")
    @ResponseBody
    public String s1(HttpSession session){
        log.info("UserController ... s1方法被执行");
        session.setAttribute("username","zhangsan");
//        int i=1/0;
        return "设置session成功";
    }

    @GetMapping("/s2")
    @ResponseBody
    public String s2(HttpSession session){
        log.info("UserController ... s2方法被执行");
        String username=(String) session.getAttribute("username");
        if (StrUtil.isNotBlank(username)){

            return "获取session成功,username:"+username;
        }else {

            return "当前没有设置session";
        }
    }
    @PostMapping("/login")
    public String login(String username, String passwd, HttpServletResponse resp, Model model,HttpSession session){
        User user=userService.login(username,passwd);
        if (ObjUtil.isNotNull(user)){
//            Cookie cookie = new Cookie("username", username);
//            cookie.setPath("/");
//            resp.addCookie(cookie);
            session.setAttribute("user",user);
            model.addAttribute("msg","用户登录成功");
            return "index";
        }else {
            model.addAttribute("msg","用户名密码不正确,登录失败");

            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletResponse resp,HttpSession session){
//        Cookie cookie = new Cookie("username", null);
//        cookie.setPath("/");
//        cookie.setMaxAge(0);
//        resp.addCookie(cookie);
        session.removeAttribute("user");
        return "index";
    }
}
