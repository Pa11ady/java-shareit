package ru.practicum.shareit.booking.dto;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.booking.BookingStatus;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class PostBookingDto {
    private LocalDateTime start;          //дата и время начала бронирования
    private LocalDateTime end;            //дата и время конца бронирования
    @NotNull
    private Long itemId;                  //код вещи, которую бронируют
}
