package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "Comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Ads ads;
    private int authorId;
    private String authorFirstName;
    private String authorImage;
    private int adsId;
    private String text;

    private String createdAt;

}
