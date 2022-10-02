package ru.practicum.shareit.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void getError() {
        ErrorResponse response = new ErrorResponse("Test");
        assertEquals("Test", response.getError());

    }
}