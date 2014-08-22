package com.github.zxh.classpy.common;

/**
 *
 * @author zxh
 */
@SuppressWarnings("serial")
public class FileParseException extends RuntimeException {

    public FileParseException(String message) {
        super(message);
    }

    public FileParseException(Throwable cause) {
        super(cause);
    }
    
}
