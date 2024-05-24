package cn.edu.xcu.springboot.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/r/r1")
    public String r1(){
        return "访问资源1";
    }
    @GetMapping("/r/r2")
    public String r2(){
        return "访问资源2";
    }
    @GetMapping("/r/r3")
    public String r3(){
        return "访问资源3";
    }
}
