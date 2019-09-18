package com.bandaddict.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BandDTO {
    private Long id;
    private String name;
    private String shortDescription;
    private List<MusicStyleDTO> styles;
    private String country;
    private Date formedDate;
    private List<UserDTO> bandMembers;
    private List<SongDTO> songs;
    private String bandLogo;
}
