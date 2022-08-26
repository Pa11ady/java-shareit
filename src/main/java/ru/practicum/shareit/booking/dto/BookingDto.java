package ru.practicum.shareit.booking.dto;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDto {
    private Long id;
    private LocalDateTime start;    //дата и время начала бронирования
    private LocalDateTime end;      //дата и время конца бронирования
    private Item item;              //вещь, которую бронируют
    private User booker;            //пользователь, который бронирует
    private Status status;          //статус бронирования

    public enum Status {
        WAITING,        //новое бронирование, ожидает одобрения
        APPROVED,       //бронирование подтверждено владельцем
        REJECTED,       //бронирование отклонено владельцем
        CANCELED        //бронирование отменено создателем.
    }
}
