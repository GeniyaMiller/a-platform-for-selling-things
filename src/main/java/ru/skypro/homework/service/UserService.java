package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.Exception.UserNotFoundException;
import ru.skypro.homework.dto.profile.UserDto;

import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public UserService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.passwordEncoder = new BCryptPasswordEncoder();;
    }
    /**
     * Возвращает пользователя по логину
     *
     * @param name логин пользователя {@link String}
     * @return пользователь {@link User}
     */
    public User getUserByName(String name) {
        log.info(" getUserByName " + name);
        // Ищем пользователя в базе данных по имени пользователя
        User foundUser = userRepository.findByUsername(name);

        // Проверяем, что пользователь существует
        if (foundUser != null) {
            return foundUser;
        } else {
            throw new UserNotFoundException();
        }
    }

    /**
     * Преобразует User в UserDTO
     *
     * @param user пользователь {@link User}
     * @return пользователь {@link UserDto}
     */
    public UserDto userToDto(User user){
        String avatar = "/users/me/avatar";
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPhone(), avatar);
    }

    public UserDto updateUser(UserDto userDTO, Authentication authentication) {
        String userName = authentication.getName();
        log.info("updateUser " + userName);
        User user = userRepository.findByUsername(userName);

        if (user == null) {
            throw new UserNotFoundException();
        }
        authService.isAuthorized(user, authentication);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());

        User updateuser = userRepository.save(user);
        return userToDto(updateuser);
    }
}
