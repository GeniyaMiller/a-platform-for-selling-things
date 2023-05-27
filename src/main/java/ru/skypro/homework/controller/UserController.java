package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

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
}
