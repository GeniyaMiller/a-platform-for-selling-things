package ru.skypro.homework.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ads.CommentDto;
import ru.skypro.homework.model.Comment;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", source = "pk")
    @Mapping(target = "authorId", ignore = true)
    @Mapping(target = "adsId", ignore = true)
    @Mapping(target = "createdAt", source = "createdAt")
    Comment commentDtoToComment(CommentDto commentDto);

    @Mapping(target = "author", source = "authorId.id")
    @Mapping(target = "authorImage", ignore = true)
    @Mapping(target = "authorFirstName", ignore = true)
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "pk", source = "id")
    CommentDto commentToCommentDto(Comment comment);

    default LocalDateTime toLocalDateTime(Long longDt) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(longDt), ZoneId.systemDefault());
    }

    default Long toLongDateTime(LocalDateTime locDt) {
        return locDt.toInstant(ZoneOffset.UTC).toEpochMilli();
    }



}
