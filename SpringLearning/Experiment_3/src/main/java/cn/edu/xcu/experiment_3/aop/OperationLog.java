package cn.edu.xcu.experiment_3.aop;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
    public String value() default "";
    public OperatorType operatorType() default OperatorType.QUERY;
    public String desc() default "";
}
