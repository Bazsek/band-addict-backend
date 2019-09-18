package com.bandaddict.DTO;

import com.bandaddict.Entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class SheetDTO {

    private String title;
    private String name;
    private String instrument;
    private Date createdAt;
    private User createdBy;
}
