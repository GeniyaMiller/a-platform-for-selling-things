package ru.skypro.homework.dto.auth;

import lombok.Data;
import ru.skypro.homework.dto.profile.Role;
@Data
public class RegReq {
    private String username;
    private String password;
    private Role role;
}
