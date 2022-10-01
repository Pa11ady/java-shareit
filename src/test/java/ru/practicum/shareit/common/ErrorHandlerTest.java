package ru.practicum.shareit.common;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.common.exception.StateIsNotSupportException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorHandlerTest {

    @Test
    void handleStateIsNotSupportException() {
        ErrorHandler errorHandler = new  ErrorHandler();
        StateIsNotSupportException e = new StateIsNotSupportException("test");
        assertEquals("test", errorHandler.handleStateIsNotSupportException(e).getError());
    }
}