package cn.edu.xcu.springboot.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName sys_role_menu
 */
@TableName(value ="sys_role_menu")
@Data
public class RoleMenu implements Serializable {
    private Long roleId;

    private Long menuId;

    private static final long serialVersionUID = 1L;
}