package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@Table (name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    @ManyToOne
    private User author;
    @ManyToOne
    private Ads ads;
    private String text;
    private Instant createdAt = Instant.now();

}
