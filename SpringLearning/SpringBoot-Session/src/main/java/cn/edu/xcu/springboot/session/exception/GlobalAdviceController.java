package cn.edu.xcu.springboot.session.exception;

import cn.edu.xcu.springboot.session.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAdviceController {
    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception e){
        return R.fail(e.getMessage());

    }

}
