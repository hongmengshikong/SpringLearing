package cn.edu.xcu.springboot.session.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Slf4j
@Controller
public class IndexController {
    @GetMapping("/test/abc")
    @ResponseBody
    public String test(){
        log.info("IndexController ... test方法被执行");
        return "返回test页面信息";
    }
    @GetMapping("/emps")
    public String emps(){
        return "emps";
    }
    @GetMapping("/depts")
    public String depts(){
        return "depts";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
