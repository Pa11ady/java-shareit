package ru.practicum.shareit.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.booking.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository  extends JpaRepository<Booking, Long> {
    @Query("select b from Booking b where ( " +
            ":start <= b.start and b.start <= :end or " +
            ":start <= b.end and b.end <= :end) and " +
            "b.item.id = :itemId and " +
            "b.status = 'APPROVED'")
    List<Booking> findAllByDateAndId(Long itemId, LocalDateTime start, LocalDateTime end);

    List<Booking> findALLByBookerId(Long bookerId);

    List<Booking> findALLByBookerIdAndStatus(Long bookerId, BookingStatus status);

    List<Booking> findByBookerIdAndEndIsBefore(Long bookerId, LocalDateTime date);

    List<Booking> findByBookerIdAndStartIsAfter(Long bookerId, LocalDateTime date);

    @Query("select b from Booking b where  " +
            "b.start <= :date and :date <= b.end or " +
            "b.booker.id = :bookerId")
    List<Booking> findByBookerIdCurrDate(Long bookerId, LocalDateTime date);
}
