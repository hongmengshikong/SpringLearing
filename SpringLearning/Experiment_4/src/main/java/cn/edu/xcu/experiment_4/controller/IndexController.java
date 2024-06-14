package cn.edu.xcu.experiment_4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
}
