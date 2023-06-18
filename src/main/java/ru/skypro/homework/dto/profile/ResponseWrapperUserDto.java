package ru.skypro.homework.dto.profile;

import lombok.Data;

@Data

public class ResponseWrapperUserDto {
    private int count;
    private UserDto[] result;
}
