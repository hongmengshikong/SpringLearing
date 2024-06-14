package cn.edu.xcu.experiment_4.aop;


import cn.edu.xcu.experiment_4.dto.UserAddDto;
import cn.edu.xcu.experiment_4.dto.UserChPassDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.time.LocalDateTime;


@Aspect
@Component
@Slf4j
public class OperationLogAspect {

    @AfterReturning(pointcut = "execution(* cn.edu.xcu.experiment_4.service.impl.UserServiceImpl.changeUserPassword(..))")
    public void logChangePassword(JoinPoint joinPoint) {
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        UserChPassDto user = (UserChPassDto) args[0];

        // 获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();

        // 获取当前操作用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String operator = authentication != null ? authentication.getName() : "Unknown";

        // 记录操作日志
        log.info("User {} changed password for user {} at {}", operator, user.getUsername(), currentTime);
    }

    @AfterReturning(pointcut = "execution(* cn.edu.xcu.experiment_4.service.impl.UserServiceImpl.addUser(..))")
    public void logAddUser(JoinPoint joinPoint) {
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        UserAddDto user = (UserAddDto) args[0];

        // 获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();

        // 获取当前操作用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String operator = authentication != null ? authentication.getName() : "Unknown";

        // 记录操作日志
        log.info("User {} added a new user {} at {}", operator, user.getUserName(), currentTime);
    }
}
