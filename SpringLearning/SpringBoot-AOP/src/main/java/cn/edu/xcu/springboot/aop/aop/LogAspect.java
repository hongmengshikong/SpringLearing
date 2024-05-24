package cn.edu.xcu.springboot.aop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {
    @Before("execution(* cn.edu.xcu.springboot.aop.service.impl.UserServiceImpl.*(..))")
    public void before(JoinPoint jp){
        log.info("LogAspect ... before");
        //获取aop作⽤的实体类
        System.out.println("LogAspect ... before,类名:"+jp.getTarget());
        //获取aop作⽤的⽅法
        System.out.println("LogAspect ... before,方法名:"+jp.getSignature());
        //获取aop作⽤的⽅法中的输⼊参数
        Object[] args = jp.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("LogAspect ... before,参数:"+args[i]);
        }
    }
    @Pointcut("execution(* cn.edu.xcu.springboot.aop.service.impl.UserServiceImpl.*(..))")
    public void pointcut(){}
    @After("pointcut()")
    public void after(JoinPoint jp){
        log.info("LogAspect ... after.....222222......");
    }
    @AfterReturning(value = "pointcut()",returning = "rst")
    public void afterReturning(JoinPoint jp,Object rst){
        log.info("LogAspect ... afterReturning....."+rst);
        log.info("LogAspect ... afterReturning.....333333......");
    }
    @AfterThrowing(value = "pointcut()",throwing = "e")
    public void afterThrowing(Exception e){
        log.info("LogAspect ... afterThrowing....."+e.getMessage());
        log.info("LogAspect ... afterThrowing.....444444......");
    }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp)throws Throwable{
        log.info("LogAspect ... around.....start.....555555......");
        Object[] args = pjp.getArgs();
        for (Object o:args
        ) {
            System.out.println("around....."+o);
        }
        //修改传递的参数可以在这⾥修改
// Object result=pjp.proceed(args);
        Object result=pjp.proceed();
        log.info("LogAspect ... around....."+result);
        log.info("LogAspect ... around.....end.....666666......");
        return result;
    }
    @AfterReturning(pointcut = "@annotation(controllerLog)",returning = "jsonResult")
    public void  doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult){
        log.info("LogAspect ... doAfterReturning......"+controllerLog.value());
        log.info("LogAspect ... doAfterReturning......"+controllerLog.desc());
        log.info("LogAspect ... doAfterReturning......"+controllerLog.operatorType());
        log.info("LogAspect ... doAfterReturning......"+jsonResult);
    }

}
