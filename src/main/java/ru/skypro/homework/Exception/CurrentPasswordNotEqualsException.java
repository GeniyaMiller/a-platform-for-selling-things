package ru.skypro.homework.Exception;

public class CurrentPasswordNotEqualsException extends RuntimeException {
    public CurrentPasswordNotEqualsException() {
        super("Текущий пароль не совпадает");
    }
}
