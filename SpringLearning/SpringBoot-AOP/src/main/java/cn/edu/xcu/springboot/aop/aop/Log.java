package cn.edu.xcu.springboot.aop.aop;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    public String value() default "";
    public OperatorType operatorType() default OperatorType.QUERY;
    public String desc() default "";
}
