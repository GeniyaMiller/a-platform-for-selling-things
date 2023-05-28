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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;


import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.auth.NewPassword;
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
        return ResponseEntity.ok(userService.userToDto(user));
    }
    /**
     * Метод обновляет данные пользователя
     *
     * @param userDto     дто-объект, содержащий данные для обновления пользователя {@link UserDto}
     * @return данные о пользователе в виде дто-объекта {@link UserDto}
     */
    @PatchMapping ("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, Authentication authentication) {
        log.info("updateUser");
        log.info("updateUser userDTO userName " + userDto.getEmail() + " " + userDto.getFirstName());
        log.info("updateUser authentication.getName " + authentication.getName());
        UserDto updatedDto = userService.updateUser(userDto, authentication);
        return ResponseEntity.ok(updatedDto);
    }
    /**
     * Метод возвращает аватар текущего пользователя
     * @return бинарные данные аватара пользователя
     */
    // дописать в AuthService методы регистрации
    @GetMapping(value ="/me/avatar", produces = {MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getAvatar(Authentication authentication) {
        log.info("getAvatar " );
        return ResponseEntity.ok(userService.getAvatar(authentication.getName()));
    }
    /**
     * Метод возвращает аватар пользователя
     *
     * @param id первичный ключ пользователя
     * @return бинарные данные аватара пользователя
     */
    @GetMapping(value ="/{id}/avatar", produces = {MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getAvatarById(@PathVariable int id) {
        log.info("getAvatarById " + id);
        return ResponseEntity.ok(userService.getAvatar(id));
    }
    /**
     * Метод обновляет аватар пользователя
     *
     * @param file       аватар пользователя в виде {@link MultipartFile}
     * @return код 200 - при удачном добавлении пользователя
     */
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity updateUserAvatar(@RequestPart(value = "image") MultipartFile file, Authentication authentication) {
        log.info("updateUserAvatar");
        if (file.getSize() > 1024 * 1024)
            return ResponseEntity.badRequest().body("File is too big");

        String contentType = file.getContentType();
//        System.out.println("upload avatar contentType " + contentType);
        if (contentType == null || !contentType.contains("image"))
            return ResponseEntity.badRequest().body("Only images can be uploaded");

        userService.setAvatar(file, authentication);
        return ResponseEntity.ok().build();
    }
    /**
     * Метод для смены пароля текущего пользователя
     *
     * @param newPassword дто-объект, содержащий старый и новый пароли {@link NewPassword}
     * @return код 200 - при удачном изменении пароля, код 403 - при введении неверного текущего пароля
     */
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword, Authentication authentication) {
        log.info("set_password " );
        log.info("set_password authentication getName " + authentication.getName() );
        log.info("set_password currentPassword  " + newPassword.currentPassword );
        log.info("set_password newPassword " + newPassword.newPassword );
        authService.changePassword(
                newPassword.getCurrentPassword(),
                newPassword.getNewPassword(), authentication);
        return ResponseEntity.ok().build();
    }
}
