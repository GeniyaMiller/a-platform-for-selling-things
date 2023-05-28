package ru.skypro.homework.dto.auth;

import lombok.Data;


public class NewPassword {
    private String currentPassword;
    private String newPassword;

    public NewPassword(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }


}
