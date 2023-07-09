package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.auth.RegisterReq;
import ru.skypro.homework.dto.profile.CustomUserDetails;
import ru.skypro.homework.dto.profile.UserDto;
import ru.skypro.homework.model.User;

public class UserMapper {
    public static UserDto mapToDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setImage("/users/me/image/" + user.getId());
        return userDto;
    }

    public static User mapFromRegister(RegisterReq reg) {
        User user = new User();
        user.setUsername(reg.getUsername());
        user.setPassword(reg.getPassword());
        user.setFirstName(reg.getFirstName());
        user.setLastName(reg.getLastName());
        user.setPhone(reg.getPhone());
        return user;
    }

    public static CustomUserDetails mapToCustomUserDetails(User user) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(user.getId());
        customUserDetails.setRole(user.getRole());
        customUserDetails.setUsername(user.getUsername());
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.setFirstName(user.getFirstName());
        customUserDetails.setLastName(user.getLastName());
        customUserDetails.setPhone(user.getPhone());
        customUserDetails.setImage("/users/me/image/" + user.getId());
        customUserDetails.setEnabled(true);
        return customUserDetails;
    }

    public static User customUserDetailsToUser(CustomUserDetails customUserDetails) {
        User user = new User();
        user.setId(customUserDetails.getId());
        user.setUsername(customUserDetails.getUsername());
        user.setFirstName(customUserDetails.getFirstName());
        user.setLastName(customUserDetails.getLastName());
        user.setPhone(customUserDetails.getPhone());
        user.setPassword(customUserDetails.getPassword());
        user.setEnabled(customUserDetails.isEnabled());
        user.setRole(customUserDetails.getRole());
        return user;
    }
}
