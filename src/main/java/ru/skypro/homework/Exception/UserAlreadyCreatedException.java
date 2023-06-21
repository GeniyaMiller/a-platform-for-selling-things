package ru.skypro.homework.Exception;

public class UserAlreadyCreatedException extends RuntimeException {
    public UserAlreadyCreatedException(String login) {
        super(String.format("Пользователь с логином уже создан", login));
    }
}
