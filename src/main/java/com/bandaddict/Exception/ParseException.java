package com.bandaddict.Exception;

/**
 * Jwt token parse exception
 */
public class ParseException extends RuntimeException {

    public ParseException(final String message, final Throwable cause){
        super(message, cause);
    }
}
