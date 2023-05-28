package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.auth.RegisterReq;
import ru.skypro.homework.dto.profile.Role;
import ru.skypro.homework.model.User;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReq registerReq, Role role);


    boolean isAuthorized(User user, Authentication authentication);
    boolean isAuthorized( Authentication authentication);

    void changePassword(String oldPassword, String newPassword, Authentication authentication);
}
