package cn.edu.xcu.springboot.profiles.controller;

import cn.edu.xcu.springboot.profiles.entity.Person;
import cn.edu.xcu.springboot.profiles.entity.Student;
import cn.edu.xcu.springboot.profiles.entity.Teacher;
import cn.edu.xcu.springboot.profiles.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Value("${file.uploadDir}")
    private String uploadDir;
    @Value("${file.downloadDir}")
    private String downloadDir;
    @Autowired
    private Person person;
    @Autowired
    private Student student;
    @Autowired
    private MyService myService;
    @Autowired
    private Teacher teacher;
    @GetMapping("/person")
    public Person getPerson(){
        return person;
    }
    @GetMapping("/file")
    public String getFile(){
        return "uploadDir:"+uploadDir+";downloadDir:"+downloadDir;
    }
    @GetMapping("/student")
    public Student getStudent(){
        return student;
    }
    @GetMapping("/service")
    public String service(){
        myService.sayHello();
        return "调用service中的方法成功";
    }
    @GetMapping("/teacher")
    public Teacher getTeacher(){
        return teacher;
    }
}
