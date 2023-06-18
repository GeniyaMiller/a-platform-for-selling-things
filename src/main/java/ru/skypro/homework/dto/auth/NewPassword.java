package ru.skypro.homework.dto.auth;

import lombok.Data;

@Data
public class NewPassword {
    public String currentPassword;
    public String newPassword;
    }
