package com.ittovative.otpservice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.ittovative.otpservice.util.AspectUtil.getClassName;
import static com.ittovative.otpservice.util.AspectUtil.getMethodArgs;
import static com.ittovative.otpservice.util.AspectUtil.getMethodName;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Around advice for logging
     * before and after executing project methods.
     *
     * @param joinPoint which contains details about method called
     * @return the return value of the method
     */
    @Around("execution(* com.ittovative.otpservice.*.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = getClassName(joinPoint);
        String methodName = getMethodName(joinPoint);
        StringBuilder args = getMethodArgs(joinPoint);
        Object returnVal = null;

        LOGGER.info("Executing ===> {}.{} with arguments: [{}]", className, methodName, args);
        try {
            returnVal = joinPoint.proceed();
        } catch (Throwable throwable) {
            LOGGER.error("Exception {} in ===> {}.{} with arguments: [{}]", throwable, className, methodName, args);
            throw throwable;
        }
        LOGGER.info("Finished ===> {}.{} with arguments: [{}] and returned {}", className, methodName, args, returnVal);

        return returnVal;
    }
}
