package cn.edu.xcu.jdbc.entity;

import lombok.Data;
    @Data
    public class User {
        private Long id;
        private String username;
        private String password;
        private String userNick;
        private Integer age;
        private String email;
    }

