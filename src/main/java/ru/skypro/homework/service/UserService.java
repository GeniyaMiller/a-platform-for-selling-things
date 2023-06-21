package ru.skypro.homework.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.Exception.CurrentPasswordNotEqualsException;
import ru.skypro.homework.Exception.UserAlreadyCreatedException;
import ru.skypro.homework.Exception.UserNotFoundException;
import ru.skypro.homework.dto.auth.NewPassword;
import ru.skypro.homework.dto.profile.CreateUserDto;
import ru.skypro.homework.dto.profile.ResponseWrapperUserDto;
import ru.skypro.homework.dto.profile.UserDto;

import ru.skypro.homework.mapper.CreateUserDtoMapper;
import ru.skypro.homework.mapper.UserDtoMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final CreateUserDtoMapper createUserDtoMapper;
    private final UserDtoMapper userDtoMapper;
    private final FileService fileService;

    public UserService(UserRepository userRepository,
                       CreateUserDtoMapper createUserDtoMapper,
                       UserDtoMapper userDtoMapper,
                       FileService fileService) {
        this.userRepository = userRepository;
        this.createUserDtoMapper = createUserDtoMapper;
        this.userDtoMapper = userDtoMapper;
        this.fileService = fileService;
    }
    public CreateUserDto createUser(CreateUserDto createUserDto) {

        int countUser = userRepository.countByEmail(createUserDto.getUsername());
        if (countUser > 0) {
            throw new UserAlreadyCreatedException(createUserDto.getUsername());
        }

        User user = createUserDtoMapper.toModel(createUserDto);
        User createdUser = userRepository.save(user);
        return createUserDtoMapper.toDto(createdUser);
    }

    public ResponseWrapperUserDto getUsers() {
        List<User> usersList = userRepository.findAll();
        if (usersList.size() == 0) {
            return null;
        }

        ResponseWrapperUserDto wrapperUserDto = new ResponseWrapperUserDto();
        wrapperUserDto.setCount(usersList.size());
        wrapperUserDto.setResult(userDtoMapper.toUserDtoList(usersList).toArray(new UserDto[0]));
        return wrapperUserDto;
    }
    public UserDto getMeByLogin(String login) {
        User user = getUserByLogin(login);
        return userDtoMapper.toDto(user);
    }
    public User getUserByLogin(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
    }

    public UserDto updateUser(String userLogin,UserDto updatedUser) {
        User user = getUserByLogin(userLogin);
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setPhone(updatedUser.getPhone());
        return userDtoMapper.toDto(userRepository.save(user));
    }
    public NewPassword changePassword(
            String userLogin,
            NewPassword newPasswordDto
    ) {
        User userEntity = getUserByLogin(userLogin);

        if (!userEntity.getPassword()
                .equals(newPasswordDto.getCurrentPassword())
        ) {
            throw new CurrentPasswordNotEqualsException();
        }

        userEntity.setPassword(newPasswordDto.getNewPassword());
        return newPasswordDto;
    }
    public UserDto findById(int userId) {
        return userDtoMapper.toDto(
                userRepository.findById(userId)
                        .orElseThrow(UserNotFoundException::new)
        );
    }
    public boolean login(
            String username,
            String password
    ) {
        User user = userRepository.findByEmailAndPassword(
                username,
                password
        );

        return null != user;
    }
    public boolean updateUserAvatarPath(
            String userLogin,
            String filePath
    ) {
        User user = getUserByLogin(userLogin);
        Optional<String> optionalAvatar = Optional.ofNullable(user.getAvatar());

        optionalAvatar.ifPresent(oldAvatar -> {
                    if (!oldAvatar.isEmpty()) {
                        try {
                            fileService.removeFileByPath(oldAvatar);
                        } catch (IOException ignored) {}
                    }
                }
        );

        user.setAvatar(filePath);

        try {
            userRepository.save(user);
        } catch (DataAccessException e) {
            return false;
        }

        return true;
    }



}
