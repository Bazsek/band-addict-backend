package com.bandaddict.Exception;

/**
 * Exception for scheduled tasks
 */
public class SchedulerException extends RuntimeException {
    public SchedulerException(String message, Throwable cause) {
        super(message, cause);
    }
}
