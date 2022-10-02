package ru.practicum.shareit.item.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class ItemBookingDtoTest {
    private JacksonTester<ItemBookingDto> json;
    private ItemBookingDto bookingDto;
    private Validator validator;

    public ItemBookingDtoTest(@Autowired JacksonTester<ItemBookingDto> json) {
        this.json = json;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUp() {
        bookingDto = new ItemBookingDto(
                1L,
                2L,
                LocalDateTime.of(2027, 10, 11, 12, 0),
                LocalDateTime.of(2027, 10, 20, 12, 0)
        );
    }

    @Test
    void testJsonItemBookingDto() throws Exception {
        JsonContent<ItemBookingDto> result = json.write(bookingDto);
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathNumberValue("$.bookerId").isEqualTo(2);
        assertThat(result).extractingJsonPathStringValue("$.start").isEqualTo("2027-10-11T12:00:00");
        assertThat(result).extractingJsonPathStringValue("$.end").isEqualTo("2027-10-20T12:00:00");
    }
}