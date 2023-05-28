package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.auth.RegisterReq;
import ru.skypro.homework.dto.profile.Role;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReq registerReq, Role role);


}
