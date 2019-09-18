package com.bandaddict.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * User details model
 */
@Data
@AllArgsConstructor
public class ErrorDetails {
    private Date timeStamp;
    private String message;
    private String details;
}
