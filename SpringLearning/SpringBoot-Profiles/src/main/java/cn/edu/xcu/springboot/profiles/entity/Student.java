package cn.edu.xcu.springboot.profiles.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:test.properties")
@EnableConfigurationProperties(Student.class)
@ConfigurationProperties(prefix = "test.student")
public class Student {
    private Long id;
    private String name;
    private String course;
    private Integer age;
}
