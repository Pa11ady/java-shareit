package ru.practicum.shareit.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostBookingDto {
    @FutureOrPresent
    private LocalDateTime start;          //дата и время начала бронирования
    @Future
    private LocalDateTime end;            //дата и время конца бронирования
    @NotNull
    private Long itemId;                  //код вещи, которую бронируют
}
