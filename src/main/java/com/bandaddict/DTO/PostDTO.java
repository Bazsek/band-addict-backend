package com.bandaddict.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class PostDTO {

    private UserDTO createdBy;
    private String title;
    private String description;
    private String postType;
    private Date createdAt;
    private String picture;
}
