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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Comment[] getResults() {
        return results;
    }

    public void setResults(Comment[] results) {
        this.results = results;
    }
}
