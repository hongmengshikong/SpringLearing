package cn.edu.xcu.experiment_3.entity;

import cn.edu.xcu.experiment_3.utils.ValidGroup;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
@JsonPropertyOrder({
        "id", "username", "password", "email", "age",
        "createBy", "createTime", "updateBy", "updateTime",
        "version", "delFlag"
})
public class User implements Serializable {
    @TableId
    private Long id;

    private String username;
    @JsonIgnore
    private String password;

    private String email;

    private Integer age;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Version
    private Integer version;

    @TableLogic
    @JsonIgnore
    private Integer delFlag;

    private static final long serialVersionUID = 1L;
}