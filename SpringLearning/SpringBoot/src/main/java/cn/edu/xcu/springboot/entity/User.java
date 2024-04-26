package cn.edu.xcu.springboot.entity;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data

public class User {
    private Long id;
    private String username;
    private String password;
    private String userNick;
    private Integer age;
    private String email;
    private Date birth;
    private IdCard idCard;
    private List<Order> orders;
    private HashMap<String,Integer> scores;
}
