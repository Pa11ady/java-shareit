package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.List;

public interface BookingService {

    BookingDto create(Long userId, BookingDto bookingDto);

    BookingDto findById(Long userId, Long bookingId);

    List<BookingDto> findUserBooking(Long userId, String stateParam);

    List<BookingDto> findAllByUserID(Long userId, String stateParam);

    BookingDto approveBooking(Long userId, Long bookingId, Boolean approved);
}
