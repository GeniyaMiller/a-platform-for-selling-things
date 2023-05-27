package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class User {

    private int id;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private String image;

    public User(int id, String email, String firstName, String lastName, String phone, String image) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.image = image;
    }

}
