package cn.edu.xcu.springboot.validation.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IdCard {
    private Long id;
    @NotBlank(message = "身份证编码不能为空")
    private String code;
}