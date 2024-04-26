package cn.edu.xcu.experiment_1.entity;

import lombok.Data;

import java.util.List;

@Data

public class User {
    private Long id;
    private String username;
    private String password;
    private String userNick;
    private Integer age;
    private String email;
    private IdCard idCard;
    private List<Role> roles;
}

