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
import ru.skypro.homework.Exception.CurrentPasswordNotEqualsException;
import ru.skypro.homework.Exception.UserAlreadyCreatedException;
import ru.skypro.homework.Exception.UserNotFoundException;
import ru.skypro.homework.dto.auth.NewPassword;
import ru.skypro.homework.dto.profile.CreateUserDto;
import ru.skypro.homework.dto.profile.ResponseWrapperUserDto;
import ru.skypro.homework.dto.profile.UserDto;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import javax.annotation.security.RolesAllowed;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "User Controller")
@ApiResponses(
        value = {
                @ApiResponse(responseCode = "401", content = @Content()),
                @ApiResponse(responseCode = "403", content = @Content())
        }
)
public class UserController {
    private final UserService userService;
//    private final AuthService;


    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    @Operation(
            summary = "addUser",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "user"),
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content()),
                    @ApiResponse(responseCode = "201", content = @Content()),
                    @ApiResponse(responseCode = "404", content = @Content())
            }
    )
    @RolesAllowed("ADMIN")
    public CreateUserDto addUser(@RequestBody CreateUserDto updatedUserDto) {
        try {
            CreateUserDto userDto = userService.createUser(updatedUserDto);
            if (null == userDto) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            return userDto;
        } catch (UserAlreadyCreatedException exception) {
            throw new ResponseStatusException(HttpStatus.CREATED);
        }
    }

    @GetMapping
    @Operation(
            summary = "getUsers",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content()),
                    @ApiResponse(responseCode = "201", content = @Content()),
                    @ApiResponse(responseCode = "404", content = @Content())
            }
    )
    @RolesAllowed("ADMIN")
    public ResponseWrapperUserDto getUsers() {
        ResponseWrapperUserDto wrapperUserDto = userService.getUsers();
        if (null == wrapperUserDto) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return wrapperUserDto;
    }
    @GetMapping("/me")
    @Operation(
            summary = "getMe",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content()),
                    @ApiResponse(responseCode = "201", content = @Content()),
                    @ApiResponse(responseCode = "404", content = @Content())
            }
    )
    @RolesAllowed("USER")
    public UserDto getMe(Authentication authentication) {
        try {
            return userService.getMeByLogin(authentication.getName());
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @PatchMapping("/me")
    @Operation(
            summary = "updateUser",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "user"),
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content()),
                    @ApiResponse(responseCode = "204", content = @Content()),
                    @ApiResponse(responseCode = "404", content = @Content())
            }
    )
    @RolesAllowed({"USER"})
    public UserDto updateUser(@RequestBody UserDto updatedUser, Authentication authentication) {
        try {
            return userService.updateUser(
                    authentication.getName(),
                    updatedUser
            );
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @PostMapping("/set_password")
    @Operation(
            summary = "setPassword",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "newPassword"),
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content()),
                    @ApiResponse(responseCode = "201", content = @Content()),
                    @ApiResponse(responseCode = "404", content = @Content())
            }
    )
    @RolesAllowed({"USER"})
    public NewPassword setPassword(@RequestBody NewPassword newPasswordDto, Authentication authentication) {
        try {
            return userService.changePassword(
                    authentication.getName(),
                    newPasswordDto
            );
        } catch (CurrentPasswordNotEqualsException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "getUser",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content()),
                    @ApiResponse(responseCode = "404", content = @Content())
            }
    )
    public UserDto getUser( @PathVariable("id") int userId) {
        try {
            return userService.findById(userId);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
