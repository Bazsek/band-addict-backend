package com.bandaddict.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Album entity
 */
@Entity
@Table
@Data
public class Album implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String coverPhoto;
    private Date createdAt;

    @OneToMany(mappedBy = "album")
    private List<Song> songs;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;

    @Override
    public String toString() {
        return "Album [id=" + id + "]";
    }
}
