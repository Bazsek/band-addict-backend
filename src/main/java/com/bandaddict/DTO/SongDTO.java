package com.bandaddict.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SongDTO {

    private String title;
    private Date publishingDate;
    private String album;
    private String description;
    private String youtube;
    private String type;
    private BandDTO band;
    private List<MusicStyleDTO> styles;
}
