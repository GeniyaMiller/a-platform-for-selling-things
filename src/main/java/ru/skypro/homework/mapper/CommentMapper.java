package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ads.CommentDto;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    @Mapping(source = "dto.pk", target = "id")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "ads", target = "ads")
    Comment toModel(CommentDto dto, User user, Ads ads);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "entity.user.id", target = "author_id")
    @Mapping(source = "entity.user.avatar", target = "author_image")

    CommentDto toDto(Comment entity);

    List<CommentDto> toAdsDtoList(List<Comment> commentList);

}
