package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.auth.RegisterReq;
import ru.skypro.homework.dto.profile.Role;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

  private final UserDetailsManager manager;

  private final PasswordEncoder encoder;
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  public AuthServiceImpl(UserDetailsManager manager, PasswordEncoder passwordEncoder) {
    this.manager = manager;
    this.encoder = passwordEncoder;
  }

  @Override
  public boolean login(String userName, String password) {
    if (!manager.userExists(userName)) {
      return false;
    }
    UserDetails userDetails = manager.loadUserByUsername(userName);
    return encoder.matches(password, userDetails.getPassword());
  }

  @Override
  public boolean register(RegisterReq registerReq, Role role) {
    if (manager.userExists(registerReq.getUsername())) {
      return false;
    }
    manager.createUser(
        User.builder()
            .passwordEncoder(this.encoder::encode)
            .password(registerReq.getPassword())
            .username(registerReq.getUsername())
            .roles(role.name())
            .build());
    return true;
  }
  /**
   * Проверка авторизации текущего пользователя
   *
   * @return true если авторизован {@link boolean}
   */
  // дописывать
  @Override
  public boolean isAuthorized(ru.skypro.homework.model.User user, Authentication authentication) {
    return false;
  }
  // дописывать
  @Override
  public boolean isAuthorized(Authentication authentication) {
    return false;
  }

  @Override
  public void changePassword(String oldPassword, String newPassword, Authentication authentication) {
    log.info("changePassword " );
    isAuthorized(authentication);
    manager.changePassword(oldPassword, newPassword);
  }
}
