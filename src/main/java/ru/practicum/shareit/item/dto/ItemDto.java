package ru.practicum.shareit.item.dto;

import lombok.*;
import ru.practicum.shareit.requests.ItemRequest;
import ru.practicum.shareit.user.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long id;
    private String name;            //краткое название
    private String description;     //развёрнутое описание

    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private boolean available;      //доступна или нет вещь для аренды;
    private Long owner;             //владелец вещи;
    private Long request;           //если создано по запросу, то ссылка на запрос

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
