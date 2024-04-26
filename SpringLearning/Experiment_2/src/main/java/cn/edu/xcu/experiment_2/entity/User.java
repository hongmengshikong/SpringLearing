package cn.edu.xcu.experiment_2.entity;

import cn.edu.xcu.experiment_2.utils.ValidGroup;
import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "t_user")
public class User {
    @NotNull(message = "更新时ID不能为空", groups = {ValidGroup.UPdate.class})
    private Long id;
    @Pattern(regexp = "[A-Za-z0-9]{4,30}", message = "用户名只能是字母和数字", groups = {ValidGroup.All.class})
    @NotBlank(message = "用户名不能为空", groups = {ValidGroup.All.class})
    private String username;
    @Pattern(regexp = "(?![A-Za-z]+$)(?![A-Z\\d]+$)(?![A-Z\\W_]+$)(?![a-z\\d]+$)(?![a-z\\W_]+$)(?!\n[\\d\\W_]+$)\\S{6,}$", message = "密码必须三种以上字符，长度大于6", groups = {ValidGroup.Insert.class})
    @NotBlank(groups = ValidGroup.Insert.class)
    private String password;
    @Pattern(regexp = "[\\u4e00-\\u9Fa5]{2,}", message = "昵称只能是中文字符，长度大于2", groups = {ValidGroup.Insert.class})
    @NotBlank(message = "用户昵称不能为空", groups = {ValidGroup.Insert.class})
    private String userNick;
    @Range(min = 1, max = 100, message = "年龄必须在1-100之间", groups = {ValidGroup.Insert.class})
    @NotNull(message = "年龄不能为空", groups = {ValidGroup.Insert.class})
    private Integer age;
    @Email(message = "邮箱地址必须满足邮箱格式", groups = {ValidGroup.Insert.class})
    private String email;
    @TableField(exist = false)
    @Valid
    @NotNull(message = "身份信息不能为空", groups = {ValidGroup.Insert.class})
    private IdCard idCard;
    private Long cardId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "出生日期不能为空", groups = {ValidGroup.Insert.class})
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
    @TableField(exist = false)
    List<Role> roles;
}
