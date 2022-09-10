package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.PostBookingDto;

import java.util.List;

public interface BookingService {

    BookingDto create(Long userId, PostBookingDto postBookingDto);

    BookingDto findById(Long userId, Long bookingId);

    List<BookingDto> findUserBooking(Long userId, String stateParam);

    List<BookingDto> findUserBooking(Long userId, String stateParam, Integer from, Integer size);

    List<BookingDto> findItemBooking(Long userId, String stateParam);

    List<BookingDto> findItemBooking(Long userId, String stateParam, Integer from, Integer size);

    BookingDto approveBooking(Long userId, Long bookingId, Boolean approved);

}
