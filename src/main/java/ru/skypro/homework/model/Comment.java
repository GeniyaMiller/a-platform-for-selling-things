package ru.skypro.homework.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int authorId;
    private String authorFirstName;
    private String authorImage;
    private int adsId;
    private String text;

    private String createdAt;

    public Comment() {
    }

    public Comment(Integer id, int authorId, String authorFirstName, String authorImage, int adsId, String text, String createdAt) {
        this.id = id;
        this.authorId = authorId;
        this.authorFirstName = authorFirstName;
        this.authorImage = authorImage;
        this.adsId = adsId;
        this.text = text;
        this.createdAt = createdAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAdsId() {
        return adsId;
    }

    public void setAdsId(int adsId) {
        this.adsId = adsId;
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
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorImage='" + authorImage + '\'' +
                ", adsId=" + adsId +
                ", text='" + text + '\'' +
                '}';
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
