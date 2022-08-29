package ru.practicum.shareit.booking.model;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.booking.BookingStatus;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "bookings", schema = "public")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    @Column(name = "start_date")
    private Instant start;    //дата и время начала бронирования

    @Column(name = "end_date")
    private Instant end;      //дата и время конца бронирования

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;        //вещь, которую бронируют

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User booker;     //пользователь, который бронирует

    @Enumerated(EnumType.STRING)
    private BookingStatus status;   //статус бронирования
}
