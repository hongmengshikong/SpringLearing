package cn.edu.xcu.springboot.aop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName t_role
 */
@TableName(value ="t_role")
@Data
public class Role implements Serializable {
    private Long id;

    private String roleName;

    private String roleDesc;

    private static final long serialVersionUID = 1L;
}