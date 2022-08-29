package ru.practicum.shareit.booking;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.user.User;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookingMapper {
    public static BookingDto mapToBookingDto(Booking booking) {
        return null;
    }

    public static List<BookingDto> mapToBookingDto(Iterable<Booking> bookings) {
        List<BookingDto> result = new ArrayList<>();

        for (var el : bookings) {
            result.add(mapToBookingDto(el));
        }

        return result;
    }

    public static Booking mapToBooking(User booker, BookingDto bookingDto) {
        return null;
    }
}
