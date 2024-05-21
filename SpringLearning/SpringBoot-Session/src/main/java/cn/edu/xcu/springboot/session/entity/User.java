package cn.edu.xcu.springboot.session.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
}