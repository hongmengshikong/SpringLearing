package cn.edu.xcu.experiment_2.entity;

import cn.edu.xcu.experiment_2.utils.ValidGroup;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "t_idcard")
public class IdCard {
    private Long id;
    @NotNull(message = "身份证号码不能为空", groups = {ValidGroup.Insert.class})
    @Pattern(regexp = "\\d{18}", message = "身份议号码必须18位",groups = {ValidGroup.Insert.class})
    private String code;
    @NotBlank(message = "所在省份不能为空", groups = {ValidGroup.Insert.class})
    private String province;
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
}
