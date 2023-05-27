package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ResponseWrapperComment {

    private int count;

    private Comment[] results;

    public ResponseWrapperComment(int count, Comment[] results) {
        this.count = count;
        this.results = results;
    }


}
