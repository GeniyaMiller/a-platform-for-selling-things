package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.profile.Role;

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
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private byte[] image;
    private String password;
    private Boolean enabled;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "author", cascade = {CascadeType.REMOVE})
    private List<Ads> ads;
    @OneToMany(mappedBy = "author", cascade = {CascadeType.REMOVE})
    private List<Comment> comments;
}
