package com.bandaddict.Entity;

import com.bandaddict.Enum.PostType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Post entity
 */
@Entity
@Data
@Table
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private PostType type;

    private Date createdAt;
    private String picture;

    @Override
    public String toString() {
        return "Post [id=" + id + "]";
    }
}
