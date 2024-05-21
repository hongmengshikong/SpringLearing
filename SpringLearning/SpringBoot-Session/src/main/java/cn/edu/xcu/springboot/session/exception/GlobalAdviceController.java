package cn.edu.xcu.springboot.session.exception;

import cn.edu.xcu.springboot.session.utils.HttpStatus;
import cn.edu.xcu.springboot.session.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAdviceController {
    @ExceptionHandler
    public R myException(MyException e){
        return R.fail(HttpStatus.CONFLICT,e.getMessage());
    }
    @ExceptionHandler(ArithmeticException.class)
    public R exception(ArithmeticException e){
        // 返回给前端的信息
        return R.fail("算术异常"+e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R exception(Exception e){
        return R.fail(e.getMessage());

    }

}
