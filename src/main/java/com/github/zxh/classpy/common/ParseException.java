package com.github.zxh.classpy.common;

@SuppressWarnings("serial")
public class ParseException extends RuntimeException {

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }
    
}
