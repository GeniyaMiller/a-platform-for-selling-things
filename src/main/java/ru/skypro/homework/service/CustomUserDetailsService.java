package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.Exception.NotFoundException;
import ru.skypro.homework.dto.profile.CustomUserDetails;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.validator.Validator;
@Service
public class CustomUserDetailsService implements UserDetailsManager {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public CustomUserDetailsService(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserDetails user) {
        User saveUser = UserMapper.customUserDetailsToUser((CustomUserDetails) user);
        saveUser.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(saveUser);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Validator.checkValidateString(username);
        return UserMapper.mapToCustomUserDetails(userRepository.findByUsername(username).orElseThrow(NotFoundException::new));
    }
}
