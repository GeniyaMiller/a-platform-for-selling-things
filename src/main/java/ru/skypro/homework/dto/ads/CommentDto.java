package ru.skypro.homework.dto.ads;

import lombok.Data;

@Data
public class CommentDto {
    private int author;

    private String authorImage;

    private String authorFirstName;

    private long createdAt;

    private int pk;

    private String text;

    public CommentDto(int author, String authorImage, String authorFirstName, long createdAt, int pk, String text) {
        this.author = author;
        this.authorImage = authorImage;
        this.authorFirstName = authorFirstName;
        this.createdAt = createdAt;
        this.pk = pk;
        this.text = text;
    }


}