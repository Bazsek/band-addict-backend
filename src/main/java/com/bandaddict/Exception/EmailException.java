package com.bandaddict.Exception;

/**
 * Email sending exception
 */
public class EmailException extends RuntimeException {

    public EmailException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
