package de.exware.gplatform.internal;

import java.time.LocalDateTime;

public class Logger
{
    public static final String LEVEL_NONE = "";
    public static final String LEVEL_NATIVE = "LEVEL_NATIVE";
    public static final String LEVEL_IMPLEMENTATION = "LEVEL_IMPLEMENTATION";
    public static final String LEVEL_APPLICATION = "LEVEL_APPLICATION";

    private Class clazz;
    private String logPoint;
    private String continousLoggingConstant = null;
    private int continousLoggingCounter = 0;
    private static String logLevel = "";

    public Logger(Class clazz)
    {
        this(clazz, clazz.getName());
    }

    public void setLogPoint(String logPoint)
    {
        this.logPoint = logPoint;
    }

    public void setContinousLoggingConstant(String continousLoggingConstant)
    {
        this.continousLoggingConstant = continousLoggingConstant;
    }

    public Logger(Class clazz, String logPoint)
    {
        this(clazz, logPoint, "CLog_");
    }

    public Logger(Class clazz, String logPoint, String continousLoggingConstant)
    {
        this.clazz = clazz;
        this.logPoint = logPoint;
        this.continousLoggingConstant = continousLoggingConstant;
    }

    public void logNewLine(String level, String log)
    {
        log(level, log + "\n");
    }

    public void logNewLine(String log)
    {
        log(log + "\n");
    }
    
    public void logContinous(String level)
    {
        log(level, level);
    }
    
    public void logContinous()
    {
        log(continousLoggingConstant + continousLoggingCounter++);
    }

    public void log(String level, String log)
    {
        if (logLevel.contains(level)) {
            log(log);
        }
    }

    public void log(String log)
    {
        System.out.println(LocalDateTime.now().toString() + "_" + logPoint + ": " + log);
    }

    public static void setLogLevel(String level)
    {
        if (!logLevel.contains(level)) {
            logLevel = logLevel + level;
        }
    }

    public static void removeLogLevel(String level)
    {
        if (logLevel.contains(level)) {
            logLevel = logLevel.replaceAll(level, "");
        }
    }

    public static Logger getInstance(Class clazz)
    {
        return new Logger(clazz);
    }
}