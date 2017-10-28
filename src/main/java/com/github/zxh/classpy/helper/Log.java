package com.github.zxh.classpy.helper;

public class Log {
    public static void log(Object message) {
        System.out.print("INFO: ");
        System.out.println(message);
    }

    public static void log(Throwable exception) {
        System.err.print("ERROR: ");
        exception.printStackTrace(System.err);
    }

    public static void info(String message) {
        System.out.print("INFO: ");
        System.out.println(message);
    }

    public static void warning(String message) {
        System.out.print("WARNING: ");
        System.out.println(message);
    }

    public static void error(String message) {
        System.err.print("ERROR: ");
        System.err.println(message);
    }
}
