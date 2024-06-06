package cn.edu.xcu.springboot.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName sys_user_role
 */
@TableName(value ="sys_user_role")
@Data
public class UserRole implements Serializable {
    private Long userId;

    private Long roleId;

    private static final long serialVersionUID = 1L;
}