package cn.edu.xcu.springboot.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
//@TableName(value = "t_user")
public class User {
//    @TableId(type = IdType.ASSIGN_ID)//雪花算法
    private Long id;
    private String username;
    private String password;
    @TableField(value = "user_nick")
    private String userNick;
    private Integer age;
    private String email;
    private LocalDateTime birth;
    private Integer status;
    @TableLogic
    private Integer delFlag;
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private String remark;
    @Version
    private Integer version;
}
