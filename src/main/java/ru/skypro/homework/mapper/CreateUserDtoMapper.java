package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.profile.CreateUserDto;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface CreateUserDtoMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toModel(CreateUserDto dto);
    CreateUserDto toDto(User entity);
}
