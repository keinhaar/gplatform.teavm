package de.exware.gplatform.internal;

import java.time.LocalDateTime;

public class Logger {
    public static final String LEVEL_NATIVE = "LEVEL_NATIVE";
    public static final String LEVEL_APPLICATION = "LEVEL_APPLICATION";
    
    
    private Class clazz;
    private String className;
    private static String logLevel = "";
    
    public Logger(Class clazz) {
        this.clazz = clazz;
        
        className = clazz.getName();
    }
    
    public void logNewLine(String level, String log) {
        if(logLevel.contains(level)) {
            logNewLine(log);
        }
    }
    
    public void logNewLine(String log) {
        log(log + "\n");
    }
    
    public void log(String level, String log) {
        if(logLevel.contains(level)) {
            log(log);
        }
    }
    
    public void log(String log) {
        System.out.println(LocalDateTime.now().toString() + "_" + className + ": " + log);
    }
    
    public static void setLogLevel(String level) {
        if(!logLevel.contains(level)) {
            logLevel = logLevel + level;
        }
    }
    
    public static void removeLogLevel(String level) {
        if(logLevel.contains(level)) {
            logLevel = logLevel.replaceAll(level, "");
        }
    }
    
    public static Logger getInstance(Class clazz) {
        return new Logger(clazz);
    }
}