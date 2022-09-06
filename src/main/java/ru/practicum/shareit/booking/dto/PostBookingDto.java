package ru.practicum.shareit.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostBookingDto {
    private LocalDateTime start;          //дата и время начала бронирования
    private LocalDateTime end;            //дата и время конца бронирования
    @NotNull
    private Long itemId;                  //код вещи, которую бронируют
}
