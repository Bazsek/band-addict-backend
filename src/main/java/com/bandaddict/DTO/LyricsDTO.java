package com.bandaddict.DTO;

import com.bandaddict.Entity.Song;
import lombok.Data;

import java.util.Date;

/**
 * LyricsDTO for Lyrics entity
 */
@Data
public class LyricsDTO {

    private Long id;
    private String title;
    private String text;
    private Date createdAt;
    private UserDTO createdBy;
    private Song song;
}
