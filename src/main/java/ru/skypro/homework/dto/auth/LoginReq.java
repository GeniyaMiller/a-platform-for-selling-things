package ru.skypro.homework.dto.auth;

import lombok.Data;

@Data
public class LoginReq {
    private String password;
    private String username;

    public LoginReq(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }


}
