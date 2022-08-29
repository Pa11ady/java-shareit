package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookingServiceImp implements BookingService {
    @Override
    public BookingDto create(BookingDto bookingDto) {
        return null;
    }

    @Override
    public BookingDto findById(Long userId, Long bookingId) {
        return null;
    }

    @Override
    public List<BookingDto> findUserBooking(Long userId, String stateParam) {
        return null;
    }

    @Override
    public List<BookingDto> findAllByUserID(Long userId, String stateParam) {
        return null;
    }

    @Override
    public BookingDto approveBooking(Long userId, Long bookingId, Boolean approved) {
        return null;
    }
}
