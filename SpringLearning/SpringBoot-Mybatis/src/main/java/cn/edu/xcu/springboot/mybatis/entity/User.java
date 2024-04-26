package cn.edu.xcu.springboot.mybatis.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer age;
    private String email;
    private Date birth;
}