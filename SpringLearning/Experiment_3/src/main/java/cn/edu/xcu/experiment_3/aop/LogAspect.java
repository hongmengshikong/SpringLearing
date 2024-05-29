package cn.edu.xcu.experiment_3.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {
    @Pointcut("execution(* cn.edu.xcu.experiment_3.service.impl.RoleServiceImpl.*(..))")
    public void RolePointcut(){}

    @Around("RolePointcut()")
    public Object RoleAround(ProceedingJoinPoint pjp)throws Throwable{
        long startTime = System.currentTimeMillis();
        log.info("开始时间"+startTime);
        log.info("LogAspect ... RoleAround.....start.....555555......");
        //获取aop作⽤的实体类
        System.out.println("LogAspect ... RoleAround,类名:"+pjp.getTarget());
        //获取aop作⽤的⽅法
        System.out.println("LogAspect ... RoleAround,方法名:"+pjp.getSignature());
        //获取aop作⽤的⽅法中的输⼊参数
        Object[] args = pjp.getArgs();

        for (int i = 0; i < args.length; i++) {
            System.out.println("LogAspect ... RoleAround,参数:"+args[i]);
        }
        // 执行目标方法
        Object result = pjp.proceed();

        long endTime = System.currentTimeMillis();
        log.info("结束时间"+endTime);
        long duration = endTime - startTime;
        log.info("LogAspect ... RoleAround, 处理时间: " + duration + " 毫秒");
        log.info("LogAspect ... RoleAround....."+result);
        log.info("LogAspect ... RoleAround.....end.....666666......");
        return result;
    }
    @Pointcut("@annotation(operationLog)")
    public void UserPointcut(OperationLog operationLog) {}
    @Around("UserPointcut(operationLog)")
    public Object around(ProceedingJoinPoint pjp, OperationLog operationLog) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("开始时间"+startTime);
        // 获取操作名称、类型和描述
        String operationName = operationLog.value();
        String operationType = String.valueOf(operationLog.operatorType());
        String operationDesc = operationLog.desc();

        // 打印操作信息
        log.info("操作名称: " + operationName);
        log.info("操作类型: " + operationType);
        log.info("操作描述: " + operationDesc);

        // 获取方法名和参数
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        log.info("方法名: " + methodName);
        for (Object arg : args) {
            log.info("参数: " + arg);
        }

        // 执行目标方法
        Object result = pjp.proceed();

        long endTime = System.currentTimeMillis();
        log.info("结束时间"+endTime);
        long duration = endTime - startTime;

        // 打印处理时间
        log.info("操作花费的时间: " + duration + " 毫秒");

        return result;
    }




}
