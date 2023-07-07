package ru.skypro.homework.dto.ads;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class ResponseWrapperCommentDto {

    private int count;

    private List<CommentDto> results;

}
