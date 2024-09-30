package com.ittovative.otpservice.util;

import org.aspectj.lang.JoinPoint;

public final class AspectUtil {
    /**
     * Retrieves the class name where the join point is located.
     * <p>
     * This method extracts the fully qualified class name from the provided {@code joinPoint}.
     * It is useful for logging or debugging purposes to identify which class a particular method belongs to.
     * </p>
     *
     * @param joinPoint The join point representing the method being executed.
     * @return The fully qualified class name where the method is located.
     */
    public static String getClassName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getDeclaringTypeName();
    }

    /**
     * Retrieves the method signature in a short format from the join point.
     * <p>
     * This method returns the method name and its parameter types in a compact format.
     * It is useful for logging or debugging purposes to identify which method is being executed.
     * </p>
     *
     * @param joinPoint The join point representing the method being executed.
     * @return A short string representation of the method signature.
     */
    public static String getMethodName(JoinPoint joinPoint) {
        return joinPoint.getSignature().toShortString();
    }

    /**
     * Retrieves the arguments passed to the method at the join point.
     * <p>
     * This method gathers all the arguments passed to the method represented by the {@code joinPoint}
     * and returns them as a comma-separated string. It is useful for logging or debugging method inputs.
     * </p>
     *
     * @param joinPoint The join point representing the method being executed.
     * @return A {@code StringBuilder} containing the arguments passed to the method, separated by commas.
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
