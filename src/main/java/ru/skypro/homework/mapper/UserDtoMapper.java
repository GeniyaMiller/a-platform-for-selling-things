package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.profile.UserDto;
import ru.skypro.homework.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "avatar", source = "image")
    User toModel(UserDto dto);
    @Mapping(target = "image", source = "avatar")
    UserDto toDto(User entity);

    List<UserDto> toUserDtoList(List<User> entityList);

    List<User> toUserEntityList(List<UserDto> dtoList);
}
