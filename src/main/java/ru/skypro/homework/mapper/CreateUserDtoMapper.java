package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.profile.CreateUserDto;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface CreateUserDtoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "email", source = "username")
    User toModel(CreateUserDto dto);
    @Mapping(target = "username", source = "email")
    CreateUserDto toDto(User entity);
}

