package cn.edu.xcu.springboot.profiles.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private Long id;
    private String name;
    private List<String> hobby;
    private Map<String,Object> map;
}
