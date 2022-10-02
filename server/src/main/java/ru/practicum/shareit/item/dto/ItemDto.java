package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.shareit.item.comment.dto.CommentDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long id;
    private String name;                //краткое название
    private String description;         //развёрнутое описание
    private Boolean available;          //доступна или нет вещь для аренды
    private Long owner;                 //владелец вещи;
    private Long requestId;             //если создано по запросу, то ссылка на запрос
    private ItemBookingDto lastBooking; //последнее бронирование
    private ItemBookingDto nextBooking; //следующего бронирования
    private List<CommentDto> comments;  //комментарий арендатора
}
