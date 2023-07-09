package ru.skypro.homework.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.service.CustomUserDetailsService;

public class AuthServiceImplBuilder {
    private CustomUserDetailsService manager;
    private PasswordEncoder encoder;

    public AuthServiceImplBuilder setManager(CustomUserDetailsService manager) {
        this.manager = manager;
        return this;
    }

    public AuthServiceImplBuilder setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
        return this;
    }

    public AuthServiceImpl createAuthServiceImpl() {
        return new AuthServiceImpl(manager, encoder);
    }
}