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

    //Пользователь смотрит свои заказы
    List<Booking> findAllByBookerId(Long bookerId);

    List<Booking> findAllByBookerIdAndStatus(Long bookerId, BookingStatus status);

    List<Booking> findAllByBookerIdAndEndIsBefore(Long bookerId, LocalDateTime date);

    List<Booking> findAllByBookerIdAndStartIsAfter(Long bookerId, LocalDateTime date);

    @Query("select b from Booking b where  " +
            "b.start < :date and :date < b.end and " +
            "b.booker.id = :bookerId")
    List<Booking> findByBookerIdCurrDate(Long bookerId, LocalDateTime date);

    //Пользователь смотрит свои вещи, которые были заказаны другими.

    @Query("select b from Booking b where  " +
            "b.item.owner.id = :ownerId")
    List<Booking> findAllItemBooking(Long ownerId);

    @Query("select b from Booking b where  " +
            "b.item.owner.id = :ownerId and " +
            "b.end < :date")
    List<Booking> findAllItemBookingEndIsBefore(Long ownerId, LocalDateTime date);

    @Query("select b from Booking b where  " +
            "b.item.owner.id = :ownerId and " +
            "b.start > :date")
    List<Booking> findAllItemBookingAndStartIsAfter(Long ownerId, LocalDateTime date);

    @Query("select b from Booking b where  " +
            "b.start < :date and :date < b.end and " +
            "b.item.owner.id = :ownerId")
    List<Booking> findAllItemBookingCurrDate(Long ownerId, LocalDateTime date);

    @Query("select b from Booking b where  " +
            "b.item.owner.id = :ownerId and b.status = :status")
    List<Booking> findAllItemBookingStatus(Long ownerId, BookingStatus status);
}
