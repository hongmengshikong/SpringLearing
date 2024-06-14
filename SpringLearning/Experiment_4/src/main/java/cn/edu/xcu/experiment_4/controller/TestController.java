package cn.edu.xcu.experiment_4.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PreAuthorize("hasAnyAuthority({'system:dept:list'})")
    @GetMapping("/r/r1")
    public String r1(){
        return "访问资源1";
    }
    @PreAuthorize("hasAnyAuthority('system:user:query')")
    @GetMapping("/r/r2")
    public String r2(){
        return "访问资源2";
    }
    @GetMapping("/r/r3")
    public String r3(){
        return "访问资源3";
    }
}
