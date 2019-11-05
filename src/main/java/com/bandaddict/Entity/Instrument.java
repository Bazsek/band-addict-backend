package com.bandaddict.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Instrument entity
 */
@Data
@Entity
@Table
public class Instrument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String description;
    private String type;

    @ManyToMany(mappedBy = "instruments")
    @JsonIgnore
    @Transient
    private List<User> users;
}
