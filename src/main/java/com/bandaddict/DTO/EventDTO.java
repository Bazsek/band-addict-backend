package com.bandaddict.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class EventDTO {

    private Long id;
    private String title;
    private String description;
    private Date start;
    private Date end;
    private String type;
}
