package ru.practicum.shareit.item.model;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.common.AbstractEntity;
import ru.practicum.shareit.requests.ItemRequest;
import ru.practicum.shareit.user.User;

@Getter
@Setter
public class Item extends AbstractEntity {
    private String name;            //краткое название
    private String description;     //развёрнутое описание
    private boolean available;      //доступна или нет вещь для аренды;
    private User owner;             //владелец вещи;
    private ItemRequest request;    //если создано по запросу, то ссылка на соответствующий запроc
}
