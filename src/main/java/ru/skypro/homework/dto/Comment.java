package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Comment {
    private int author;

    private String authorImage;

    private String authorFirstName;

    private int createdAt;

    private int pk;

    private String text;

    public Comment(int author, String authorImage, String authorFirstName, int createdAt, int pk, String text) {
        this.author = author;
        this.authorImage = authorImage;
        this.authorFirstName = authorFirstName;
        this.createdAt = createdAt;
        this.pk = pk;
        this.text = text;
    }


}
