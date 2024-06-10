package cn.edu.xcu.springboot.security.exception;

import cn.edu.xcu.springboot.security.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@Slf4j
@RestControllerAdvice
public class GlobalExceptionControllerAdvice {
    @ExceptionHandler(DuplicateKeyException.class)
    public R duplicateKeyException(DuplicateKeyException e){
        log.info("GlobalExceptionControllerAdvice ... duplicateKeyException");
        return R.fail("插入数据键值与已有数据冲突");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public R exception(AccessDeniedException e){
        log.info("GlobalExceptionControllerAdvice ... AccessDeniedException");
        return R.fail("未授权访问");
    }
    @ExceptionHandler(RuntimeException.class)
    public R exception(RuntimeException e){
        log.info("GlobalExceptionControllerAdvice ... RuntimeException");
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R exception(Exception e){
        log.info("GlobalExceptionControllerAdvice ... Exception");
        return R.fail(e.getMessage());
    }
}
