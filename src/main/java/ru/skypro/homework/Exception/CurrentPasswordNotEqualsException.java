package ru.skypro.homework.Exception;

public class CurrentPasswordNotEqualsException extends RuntimeException {
    public CurrentPasswordNotEqualsException() {
                super("The current password does not match");
    }

}
