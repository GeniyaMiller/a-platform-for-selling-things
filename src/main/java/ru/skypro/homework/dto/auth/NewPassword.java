package ru.skypro.homework.dto.auth;

import lombok.Data;

@Data
public class NewPassword {
    public String currentPassword;
    public String newPassword;

    public NewPassword(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }


}
