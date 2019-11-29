package com.bandaddict.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Mail
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

    private String from;
    private String to;
    private String subject;
    private String templateFileName;
    private Locale locale;
    private List<Object> attachments;
    private Map<String, String> model;

}
