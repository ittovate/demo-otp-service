package com.ittovative.otpservice.util;

import org.aspectj.lang.JoinPoint;

public final class AspectUtil {
    /**
     * Gets class name
     * of the join point.
     *
     * @param joinPoint the join point
     * @return the class name
     */
    public static String getClassName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getDeclaringTypeName();
    }

    /**
     * Gets short method name
     * of the joint point.
     *
     * @param joinPoint the join point
     * @return the name of method
     */
    public static String getMethodName(JoinPoint joinPoint) {
        return joinPoint.getSignature().toShortString();
    }

    /**
     * Get arguments of method
     * of the joint point.
     *
     * @param joinPoint the join point
     * @return the arguments of the method
     */
    public static StringBuilder getMethodArgs(JoinPoint joinPoint) {
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
        return argsString;
    }

    private AspectUtil() {
    }
}
