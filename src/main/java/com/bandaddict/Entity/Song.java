package com.bandaddict.Entity;

import com.bandaddict.Enum.MusicType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Song entity
 */
@Data
@Entity
@Table
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private Date publishingDate;
    private String album;
    private String description;
    private String youtube;

    @OneToMany(mappedBy = "song")
    private List<MusicStyle> style;

    @Enumerated(EnumType.STRING)
    private MusicType type;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;
}
