package com.bandaddict.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * the Band entity
 */
@Entity
@Table
@Data
public class Band implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String shortDescription;

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    @JoinTable(
            name = "band_styles",
            joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "music_style_id"))
    private List<MusicStyle> styles;

    private String country;
    private Date formedDate;

    @OneToMany(mappedBy = "band")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<User> bandMembers;

    @OneToMany(mappedBy = "band")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Song> songs;

    private String bandLogo;

    @JsonIgnore
    @OneToMany(mappedBy = "createdBy")
    private List<Event> events;

    @Override
    public String toString() {
        return "Band [id=" + id + "]";
    }
}
