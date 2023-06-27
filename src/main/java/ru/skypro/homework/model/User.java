package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String avatar;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String role = "USER";

    @OneToMany(mappedBy = "authorId")
    private List<Comment> comments;
}
