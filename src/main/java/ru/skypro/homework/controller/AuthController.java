package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.Exception.UserAlreadyCreatedException;
import ru.skypro.homework.dto.auth.LoginReq;
import ru.skypro.homework.dto.auth.RegisterReq;
import ru.skypro.homework.dto.profile.CreateUserDto;
import ru.skypro.homework.dto.profile.Role;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import static ru.skypro.homework.dto.profile.Role.USER;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq req) {
        if (userService.login(req.getUsername(), req.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register( @RequestBody CreateUserDto createUserDto
    ) {
        try {
            userService.createUser(createUserDto);
            return ResponseEntity.ok()
                    .build();
        } catch (UserAlreadyCreatedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
