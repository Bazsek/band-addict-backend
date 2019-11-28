package com.bandaddict.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Lyrics entity
 */
@Entity
@Table
@Data
public class Lyrics implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private Date createdAt;

    @Column(columnDefinition = "LONGTEXT")
    private String text;

    @OneToOne
    private Song song;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;
}
