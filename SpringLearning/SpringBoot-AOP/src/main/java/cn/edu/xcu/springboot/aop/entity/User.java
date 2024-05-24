package cn.edu.xcu.springboot.aop.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class User implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String userNick;

    private Integer age;

    private String email;

    private static final long serialVersionUID = 1L;
}