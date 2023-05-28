package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Entity()
@Table(name = "Users")
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
    private String username;

    /**
     * Телефон пользователя
     */
    private String phone;

    /**
     * аватар пользователя
     */
    private byte[] avatar;


}
