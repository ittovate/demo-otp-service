package com.ittovative.otpservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

/**
 * Logging aspect.
 */
@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Log before controller methods.
     *
     * @param joinPoint the join point
     */
    @Before("execution(* com.ittovative.otpservice..*(..))")
    public void logBeforeControllerMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        StringBuilder argsString = new StringBuilder();
        for (Object arg : args) {
            if (arg != null) {
                argsString.append(arg).append(", ");
            }
        }
        if (!argsString.isEmpty()) {
            argsString.setLength(argsString.length() - 2);
        }
        LOGGER.info("Executing method: {} with arguments: [{}]", methodName, argsString);
    }

    /**
     * Log exception.
     *
     * @param joinPoint the join point
     * @param exception the exception
     */
    @AfterThrowing(
            pointcut = "execution(* com.ittovative.otpservice..*.*(..))",
            throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().toShortString();
        String className = joinPoint.getSignature().getDeclaringTypeName();

        WebRequest webRequest = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof WebRequest) {
                webRequest = (WebRequest) arg;
                break;
            }
        }

        if (webRequest != null) {
            LOGGER.error(
                    "Exception in {}.{}() - {}: Request Details: {}",
                    className,
                    methodName,
                    exception.getMessage(),
                    webRequest.getDescription(false),
                    exception);
        } else {
            LOGGER.error(
                    "Exception in {}.{}() - {}",
                    className,
                    methodName,
                    exception.getMessage(),
                    exception);
        }
    }
}
