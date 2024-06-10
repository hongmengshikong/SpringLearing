package cn.edu.xcu.springboot.security.vo;

import lombok.Data;

@Data
public class UserLoginVo {
    private String username;
    private String password;
    private String vcode;

}
