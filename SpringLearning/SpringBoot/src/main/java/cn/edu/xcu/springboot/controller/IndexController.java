package cn.edu.xcu.springboot.controller;

import cn.edu.xcu.springboot.entity.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.HttpCookie;

@Controller
public class IndexController {
    @RequestMapping("/hello")
    public void hello() {
        //xxx为Mapping中url地址，例如该⽅法跳转到hello.html
        System.out.println("此⽅法默认跳转到templates/hello.html⻚⾯");
    }

    @GetMapping("/test")
    public String test() {
        //xxx为Mapping中url地址，例如该⽅法跳转到hello.html
        System.out.println("此⽅法默认跳转到templates/hello.html⻚⾯");
        return "hello";
    }
    @GetMapping("aaa")
//    @RequestMapping("/aaa")
    public String testAAA() {
        return "aaa";
    }

    @GetMapping("/bbb")
//    @RequestMapping("/bbb")
    public String testBBB() {
        //将请求重定向到/hello，重定向是将当前请求重定向的url告诉客户端，客户端重新向重定向的地址发送请求，相当于发⽣了两次请求
        return "redirect:/hello";
    }

    @RequestMapping("/ccc")
    public String testccc() {
        //将请求转发到/aaa，转发是将当前请求转发到⼀个新的url，对客户端来说相当于⼀次请求，⽽且转发时request对象不变，其中携带的参数也可以传递给新的请求，转发⼀般只在同⼀个应⽤之间，重定向可以转发到其他应⽤站点
        return "forward:/aaa";
    }

    @RequestMapping("/ddd")

    public ModelAndView testDDD(HttpServletRequest request, HttpSession session, Model model, ModelMap modelMap) {
        ModelAndView modelAndView = new  ModelAndView();
        ServletContext context = session.getServletContext();
        //测试携带参数优先级
        model.addAttribute("username", "model设置的username");
//        request.setAttribute("username", "request设置的username");
        session.setAttribute("username", "session设置的username");
        context.setAttribute("username", "context设置的username");
        User user = new User();
        user.setUsername("zhagnsan");
        user.setPassword("12345");
        user.setAge(19);
//        model.addAttribute("user", user);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("ddd");
        return modelAndView;
    }
    @GetMapping("/dd")
    public String testDD(HttpServletRequest request, HttpSession session, Model model) {
        ModelAndView modelAndView = new  ModelAndView();
        ServletContext context = session.getServletContext();
        //测试携带参数优先级
        model.addAttribute("username", "model设置的username");
        request.setAttribute("username", "request设置的username");
        session.setAttribute("username", "session设置的username");
        context.setAttribute("username", "context设置的username");
        User user = new User();
        user.setUsername("zhagnsan");
        user.setPassword("12345");
        user.setAge(19);
        model.addAttribute("user", user);
        return "ddd";
    }

    @RequestMapping("/eee")
    public String testEEE() {
        return "eee";
    }

    @RequestMapping("/fff")
    public ModelAndView testFFF() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("username", "zhangsan");
        mv.setViewName("fff");
        return mv;
    }

    @RequestMapping("/ggg")
    public void testGGG(HttpServletResponse response) {
        try {
            response.getWriter().println("response");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/userInfo")
    @ResponseBody
    public User userInfo() {
        User user = new User();
        user.setId(1L);
        user.setUsername("zhangsan");
        user.setPassword("123");
        user.setEmail("zhangsan@xcu.edu.cn");
        user.setAge(19);
        return user;
    }
}