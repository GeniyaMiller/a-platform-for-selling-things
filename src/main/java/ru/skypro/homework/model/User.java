package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "Users", schema = "public")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String email;
    /**
     * Имя пользователя
     */
    private String firstName;

    /**
     * Фамилия пользователя
     */
    private String lastName;

    /**
     * логин пользователя
     */
    private String password;

    /**
     * Телефон пользователя
     */
    private String phone;

    /**
     * аватар пользователя
     */
    private String avatar;
    private String role = "USER";
  }
