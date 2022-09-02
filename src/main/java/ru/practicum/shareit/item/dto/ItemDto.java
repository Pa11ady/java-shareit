package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    private ItemBookingDto lastBooking; //последнее бронирование
    private ItemBookingDto nextBooking; //следующего бронирования
}
