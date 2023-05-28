package ru.skypro.homework.dto.profile;

import lombok.Data;

@Data
public class UserDto {

    private int id;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private String image;



    public UserDto(int id, String email, String firstName, String lastName, String phone, String image) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.image = image;
    }

}
