package ru.skypro.homework.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table (name = "Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User authorId;
    @ManyToOne
    private Ads adsId;
    private String text;
    private LocalDateTime createdAt;

    public Comment() {
    }

    public Comment(Integer id, User authorId, Ads adsId, String text, LocalDateTime createdAt) {
        this.id = id;
        this.authorId = authorId;
        this.adsId = adsId;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthorId() {
        return authorId;
    }

    public void setAuthorId(User authorId) {
        this.authorId = authorId;
    }

    public Ads getAdsId() {
        return adsId;
    }

    public void setAdsId(Ads adsId) {
        this.adsId = adsId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", adsId=" + adsId +
                ", text='" + text + '\'' +
                '}';
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
