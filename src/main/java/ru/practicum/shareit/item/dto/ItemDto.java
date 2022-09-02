package ru.practicum.shareit.item.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long id;
    @NotBlank
    private String name;                //краткое название
    @NotBlank
    private String description;         //развёрнутое описание
    @NotNull
    private Boolean available;          //доступна или нет вещь для аренды
    private Long owner;                 //владелец вещи;
    private Long request;               //если создано по запросу, то ссылка на запрос
    private LocalDateTime lastBooking;  //дата последнего бронирования
    private LocalDateTime nextBooking;  //даты последнего следующего бронирования
}
