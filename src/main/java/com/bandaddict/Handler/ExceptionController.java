package com.bandaddict.Handler;

import com.bandaddict.Exception.NotFoundException;
import com.bandaddict.Model.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * the exception controller
 */
@ControllerAdvice
public class ExceptionController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleNotFoundException(final NotFoundException exception, final WebRequest req){
        return new ResponseEntity<>(setErrorDetails(exception, req), HttpStatus.NOT_FOUND);
    }

    private ErrorDetails setErrorDetails(final Exception ex, final WebRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        return new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
    }

}
