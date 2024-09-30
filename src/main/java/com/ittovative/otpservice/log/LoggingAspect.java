package com.ittovative.otpservice.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.ittovative.otpservice.constant.AspectConstant.AFTER_RETURN_MESSAGE;
import static com.ittovative.otpservice.constant.AspectConstant.AFTER_THROW_MESSAGE;
import static com.ittovative.otpservice.constant.AspectConstant.BEFORE_MESSAGE;
import static com.ittovative.otpservice.util.AspectUtil.getClassName;
import static com.ittovative.otpservice.util.AspectUtil.getMethodArgs;
import static com.ittovative.otpservice.util.AspectUtil.getMethodName;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Logs method execution details before and after the method is called.
     * <p>
     * This method serves as an around advice for methods within the {@code com.ittovative.otpservice} package.
     * It logs information before the method is executed, including the class name, method name, and arguments.
     * After the method execution, it logs the method's return value or,
     * in case of an exception, logs the error details.
     * </p>
     * <p>
     * This method provides useful logging for debugging and monitoring the behavior of methods within the application.
     * It uses {@code Logger} to log messages at different stages of method execution.
     * </p>
     *
     * @param joinPoint The {@code ProceedingJoinPoint} which contains details about the method being executed,
     *                  such as the class name, method name, and arguments.
     * @return The result of the method execution.
     * @throws Throwable If the method being logged throws an exception, it is propagated after logging the error.
     */
    @Around("execution(* com.ittovative.otpservice.*.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = getClassName(joinPoint);
        String methodName = getMethodName(joinPoint);
        StringBuilder args = getMethodArgs(joinPoint);
        Object returnVal = null;

        LOGGER.info(BEFORE_MESSAGE, className, methodName, args);
        try {
            returnVal = joinPoint.proceed();
        } catch (Throwable throwable) {
            LOGGER.error(AFTER_THROW_MESSAGE, throwable, className, methodName, args);
            throw throwable;
        }
        LOGGER.info(AFTER_RETURN_MESSAGE, className, methodName, args, returnVal);

        return returnVal;
    }
}
