package cn.edu.xcu.springboot.fileupload.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    @ExcelProperty("⽤户ID")
    private Long id;
    @ExcelProperty("⽤户名")
    private String username;
    @ExcelIgnore
    private String password;
    @ExcelProperty("邮箱")
    private String email;
    @ExcelProperty("年龄")
    private Integer age;
    @ExcelProperty("出生日期")
    private LocalDateTime birth;

}