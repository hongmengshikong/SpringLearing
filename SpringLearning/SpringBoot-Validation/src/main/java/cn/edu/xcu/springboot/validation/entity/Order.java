package cn.edu.xcu.springboot.validation.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Order {
    @NotNull(message = "订单Id不能为空")
    private Long id;
    @NotBlank(message = "订单名称不能为空")
    private String name;
}
