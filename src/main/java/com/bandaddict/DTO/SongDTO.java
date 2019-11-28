package com.bandaddict.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * SongDTO for Song entity
 */
@Data
public class SongDTO {

    private Long id;
    private String title;
    private Date publishingDate;
    private AlbumDTO album;
    private String description;
    private String youtube;
    private String type;
    private BandDTO band;
    private List<MusicStyleDTO> styles;
    private LyricsDTO lyricsDTO;
}
