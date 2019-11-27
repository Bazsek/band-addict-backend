package com.bandaddict.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Album DTO for Album entity
 */
@Data
public class AlbumDTO {
    private Long id;
    private String name;
    private Date createdAt;
    private String description;
    private String coverPhoto;
    private List<SongDTO> songs;
}
