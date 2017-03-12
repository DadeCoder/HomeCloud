package com.dade.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dade on 2017/2/19.
 */
public class LogUtil {

    static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void info(String mes){
        StackTraceElement ste = getCurrentElement();
        String className = getClassName(ste);
        logger.info(getTemplate(ste, className, mes));
    }

    public static void warn(String mes){
        StackTraceElement ste = getCurrentElement();
        String className = getClassName(ste);
        logger.warn(getTemplate(ste, className, mes));
    }

    public static void error(String mes){
        StackTraceElement ste = getCurrentElement();
        String className = getClassName(ste);
        logger.error(getTemplate(ste, className, mes));
    }


    private static String getTemplate(StackTraceElement ste, String className, Object message) {
        return "[" + className + "." + ste.getMethodName() + "." + ste.getLineNumber() + "] " + message;
    }

    private static String getClassName(StackTraceElement ste) {
        String className = ste.getClassName();
        List<String> classNames = Arrays.asList(className.split("\\."));
        if (className.contains("com.dade")) {
            classNames = classNames.subList(2, classNames.size());
        }
        return classNames.stream()
                .collect(Collectors.joining("."));
    }

    private static StackTraceElement getCurrentElement() {
        StackTraceElement[] list = Thread.currentThread().getStackTrace();
        return list[3];
    }

}
