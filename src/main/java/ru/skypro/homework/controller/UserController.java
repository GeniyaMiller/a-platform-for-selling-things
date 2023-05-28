package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;


import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.profile.UserDto;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import javax.annotation.security.RolesAllowed;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "User Controller")

public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    /**
     * Метод возвращает данные о пользователе
     *
     * @return данные о пользователе в виде дто-объекта {@link UserDto}
     */
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        log.info("getUser");
        User user = userService.getUserByName(authentication.getName());
        return ResponseEntity.ok(userService.userToDTO(user));
    }


}
