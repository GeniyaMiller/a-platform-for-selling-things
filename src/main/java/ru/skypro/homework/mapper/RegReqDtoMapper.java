package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.auth.RegReq;
import ru.skypro.homework.dto.profile.CreateUserDto;
import ru.skypro.homework.dto.profile.Role;

@Mapper
public interface RegReqDtoMapper {
    @Mapping(target = "role", source = "role")
    RegReq fromCreateUser(CreateUserDto dto, Role role);
}
