package cn.edu.xcu.springboot.validation.entity;

import cn.edu.xcu.springboot.validation.utils.ValidGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Data
public class User {
//    private Long id;
//    @NotBlank(message = "⽤户名不能为空")
//    @Size(min = 5, max = 10, message = "⽤户名⻓度必须在5-10个字符之间")
//    private String username;
//    //三种以上不同类型字符
//    //^(?![A-Za-z]+$)(?![A-Z\d]+$)(?![A-Z\W_]+$)(?![a-z\d]+$)(?![a-z\W_]+$)(?![\d\W_]+$)\S{6,}$
//    @Pattern(regexp = "(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[\\W_]).{6,32}", message = "密码必须满⾜密码复杂度")
//    @NotBlank
//    private String password;
//    @Range(min = 1, max = 120, message = "年龄必须在1-120之间")
//    private Integer age;
//    @Email
//    private String email;
    @NotNull(message = "⽤户ID不能为空",groups = {ValidGroup.UPdate.class})
    private Long id;
    @NotBlank(message = "⽤户名不能为空",groups = {ValidGroup.Insert.class,ValidGroup.UPdate.class})
    @Size(min = 5,max = 10,message = "⽤户名⻓度必须在2-10个字符之间")
    private String username;
    //三种以上不同类型字符
    //^(?![A-Za-z]+$)(?![A-Z\d]+$)(?![A-Z\W]+$)(?![a-z\d]+$)(?![a-z\W]+$)(?![\d\W]+$)\S{6,}$
    @Pattern(regexp = "(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^a-zA-Z0-9]).{6,32}",message = "密码必须满⾜密码复杂度")
    @NotBlank(groups = ValidGroup.Insert.class)
    private String password;
    @Range(min = 1,max = 120,message = "年龄必须在1-120之间")
    @NotNull(message = "年龄不能为空",groups = ValidGroup.Insert.class)
    private Integer age;
    @Email
    private String email;
    @Valid //添加Valid注解，该属性中的⼦属性也进⾏验证
    @NotNull(message = "idcard不能为空")
    private IdCard idCard;
    @NotEmpty(message = "订单列表不能为空")
    @Valid
    private List<Order> orders;
}