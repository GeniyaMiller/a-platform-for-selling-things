package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.dto.ads.Comment;

@Data
public class ResponseWrapperComment {

    private int count;

    private Comment[] results;

    public ResponseWrapperComment(int count, Comment[] results) {
        this.count = count;
        this.results = results;
    }


}
