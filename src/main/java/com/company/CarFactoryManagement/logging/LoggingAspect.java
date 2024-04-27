package com.company.CarFactoryManagement.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
//    private final Environment env;
//
//    public LoggingAspect(Environment env) {
//        this.env = env;
//    }

    @Pointcut("within(com.company.CarFactoryManagement.controller..*)" +
     " || within(com.company.CarFactoryManagement.service.impl..*)")
    public void applicationPackagePointcut() {

    }

    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        if(log.isDebugEnabled()) {
            log.error("Exception in {},{}() with cause = '{}' and exception = '{}'",
                    joinPoint.getSignature().getDeclaringType(),
                    joinPoint.getSignature().getName(),
                    getExceptionCause(e), e.getMessage(), e);
        } else {
            log.error("Exception in {},{}() with cause = '{}' and exception = '{}'",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    getExceptionCause(e), e.getMessage());
        }
    }

    private Object getExceptionCause(Throwable throwable) {
        return throwable.getCause() != null ? throwable.getCause() : "NULL";
    }


    @Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

            log.info("Enter : {}.{}() with arguments[s] = {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));

        try{
            Object result = joinPoint.proceed();
                log.info("Exit: {},{}() with result = {}",
                        joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result);

            return result;
        } catch (Exception ex) {
            log.error("Exception argument: {} in {},{}()",
                    Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName());
            throw ex;
        }
    }


}
