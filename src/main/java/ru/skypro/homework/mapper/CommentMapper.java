package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.ads.CommentDto;
import ru.skypro.homework.model.Comment;

public class CommentMapper {
    public static Comment mapFromDto(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setPk(commentDto.getPk());
        comment.setText(commentDto.getText());
        return comment;
    }

    public static CommentDto mapToDTO(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setPk(comment.getPk());
        commentDto.setText(comment.getText());
        commentDto.setAuthorImage("/users/me/image/" + comment.getAuthor().getId());
        commentDto.setAuthor(comment.getAuthor().getId());
        commentDto.setCreatedAt(comment.getCreatedAt().toEpochMilli());
        commentDto.setAuthorFirstName(comment.getAuthor().getFirstName());
        return commentDto;
    }
}
