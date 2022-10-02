package ru.practicum.shareit.common.exception;

public class StateIsNotSupportException extends RuntimeException {
    public StateIsNotSupportException(String message) {
        super(message);
    }
}