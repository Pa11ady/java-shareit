package ru.practicum.shareit.booking.dto;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.booking.BookingStatus;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDto {
    private Long id;
    private LocalDateTime start;           //дата и время начала бронирования
    private LocalDateTime end;             //дата и время конца бронирования
    private Item item;              //вещь, которую бронируют
    private User booker;            //пользователь, который бронирует
    private BookingStatus status;   //статус бронирования
}
