package cn.edu.xcu.springboot.profiles.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "teacher")
public class Teacher {
    private Long id;
    private String name;
    private String school;
}
