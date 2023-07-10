package ru.skypro.homework.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ru.skypro.homework.Exception.UserNotFoundException;
import ru.skypro.homework.dto.auth.NewPassword;
import ru.skypro.homework.dto.profile.CustomUserDetails;
import ru.skypro.homework.dto.profile.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.validator.Validator;

import java.io.IOException;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public UserDto findByUsername(Authentication authentication) {
        return UserMapper.mapToDTO(UserMapper.customUserDetailsToUser((CustomUserDetails) authentication.getPrincipal()));
    }

    public void changePassword(NewPassword newPassword, Authentication authentication) {
        Validator.checkValidateObj(newPassword);
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        if (!encoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            throw new BadCredentialsException("Authentication exception");
        }
        user.setPassword(encoder.encode(newPassword.getNewPassword()));
        userRepository.save(UserMapper.customUserDetailsToUser(user));
    }

    public UserDto update(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(UserNotFoundException::new);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        userRepository.save(user);
        return userDto;
    }

    public boolean updateAvatar(Authentication authentication, MultipartFile avatar) throws IOException {
        User user = UserMapper.customUserDetailsToUser((CustomUserDetails) authentication.getPrincipal());
        user.setImage(Validator.checkValidateObj(avatar).getBytes());
        userRepository.save(user);
        return true;
    }

    public byte[] showAvatarOnId(Integer id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new).getImage();
    }


}
