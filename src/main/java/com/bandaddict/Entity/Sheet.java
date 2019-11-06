package com.bandaddict.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Sheet entity
 */
@Data
@Entity
@Table
public class Sheet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String name;
    private String instrument;
    private Date createdAt;
    private String sheet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User createdBy;
}
