package com.bandaddict.Entity;

import com.bandaddict.Enum.EvenType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@Data
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Date start;
    private Date end;

    @Enumerated(EnumType.STRING)
    private EvenType type;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band createdBy;
}
