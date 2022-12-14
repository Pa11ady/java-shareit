package ru.practicum.shareit.booking;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository  extends JpaRepository<Booking, Long> {
    @Query("select b from Booking b where ( " +
            ":start <= b.start and b.start <= :end or " +
            ":start <= b.end and b.end <= :end) and " +
            "b.item.id = :itemId and " +
            "b.status = 'APPROVED'")
    List<Booking> findAllByDateAndId(Long itemId, LocalDateTime start, LocalDateTime end);

    List<Booking> findAllByBookerId(Long bookerId, Pageable pageable);

    List<Booking> findAllByBookerIdAndStatus(Long bookerId, BookingStatus status, Pageable pageable);

    List<Booking> findAllByBookerIdAndEndIsBefore(Long bookerId, LocalDateTime date, Pageable pageable);

    List<Booking> findAllByBookerIdAndStartIsAfter(Long bookerId, LocalDateTime date, Pageable pageable);

    @Query("select b from Booking b where  " +
            "b.start < :date and :date < b.end and " +
            "b.booker.id = :bookerId")
    List<Booking> findByBookerIdCurrDate(Long bookerId, LocalDateTime date, Pageable pageable);
    //////////////////////////////////////////

    @Query("select b from Booking b where  " +
            "b.item.owner.id = :ownerId")
    List<Booking> findAllItemBooking(Long ownerId, Pageable pageable);

    @Query("select b from Booking b where  " +
            "b.item.owner.id = :ownerId and " +
            "b.end < :date")
    List<Booking> findAllItemBookingEndIsBefore(Long ownerId, LocalDateTime date, Pageable pageable);

    @Query("select b from Booking b where  " +
            "b.item.owner.id = :ownerId and " +
            "b.start > :date")
    List<Booking> findAllItemBookingAndStartIsAfter(Long ownerId, LocalDateTime date, Pageable pageable);


    @Query("select b from Booking b where  " +
            "b.start < :date and :date < b.end and " +
            "b.item.owner.id = :ownerId")
    List<Booking> findAllItemBookingCurrDate(Long ownerId, LocalDateTime date, Pageable pageable);

    @Query("select b from Booking b where  " +
            "b.item.owner.id = :ownerId and b.status = :status")
    List<Booking> findAllItemBookingStatus(Long ownerId, BookingStatus status, Pageable pageable);
    //////////////////////////////////////////

    //???????? ???????????????????? ????????????????????????
    Optional<Booking> findFirstByItemIdAndEndBeforeOrderByEndDesc(Long itemId, LocalDateTime date);

    //???????? ???????????????????? ????????????????????????
    Optional<Booking> findFirstByItemIdAndStartAfterOrderByStart(Long itemId, LocalDateTime date);

    //???????????????????????? ???????? ???? ?????? ???????????????????? ????????
    Optional<Booking> findFirstByBookerAndItemIdAndEndBefore(User booker, Long itemId, LocalDateTime date);
}
