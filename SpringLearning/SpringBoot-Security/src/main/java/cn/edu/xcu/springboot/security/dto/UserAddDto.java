package cn.edu.xcu.springboot.security.dto;

import lombok.Data;

@Data
public class UserAddDto {
    private Long userId;
    private Long deptId;

    private String userName;
    private String password;

    private String nickName;

    private String email;

}
