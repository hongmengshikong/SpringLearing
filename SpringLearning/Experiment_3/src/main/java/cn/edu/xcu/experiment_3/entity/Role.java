package cn.edu.xcu.experiment_3.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName t_role
 */
@TableName(value ="t_role")
@Data
public class Role implements Serializable {
    @TableId
    private Long id;

    private String roleName;

    private String roleDesc;

    private static final long serialVersionUID = 1L;
}